package kpk.dev.presentation.ui.fragment.repositories

import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.ResponseModel
import kpk.dev.domain.usecase.IGitHubBrowserUseCase
import kpk.dev.presentation.base.ItemClickHandlerViewModel
import kpk.dev.presentation.mappers.map
import kpk.dev.presentation.model.GitHubRepoUiModel
import kpk.dev.presentation.ui.ViewState
import kpk.dev.presentation.utils.USER_QUERY_KEY

class RepositoriesViewModel @ViewModelInject constructor(
    private val gitHubBrowserUseCase: IGitHubBrowserUseCase,
    private val sharedPreferences: SharedPreferences
) : ItemClickHandlerViewModel<GitHubRepoUiModel>() {

    val viewStateData = MutableLiveData<ViewState>()

    val gitHubReposObservableList = ObservableArrayList<GitHubRepoUiModel>()
    private val handler = Handler(Looper.getMainLooper())

    override fun onStart() {
        val userQuery = sharedPreferences.getString(USER_QUERY_KEY, "")
        if (!userQuery.isNullOrEmpty()) {
            getRepoData(userQuery, true)
        } else {
            viewStateData.postValue(ViewState.Empty())
        }
    }

    fun getRepoData(user: String, initialLoad: Boolean = false) {
        viewModelScope.launch {
            viewStateData.postValue(ViewState.Loading())
            if (!initialLoad) {
                gitHubBrowserUseCase.clearDb()
            }
            when (val result = gitHubBrowserUseCase.getRepositories(user, initialLoad)) {
                is ResponseModel.Success -> {
                    clearObservableList()
                    gitHubReposObservableList.addAll(result.responseData?.map {
                        val uiRepo = it.map()
                        GlobalScope.launch {
                            Log.d("Repo", "loading commits ")
                            //This delay is just for fun, to see how the list updates when the commits are ready
                            delay(1000)
                            handleCommitsResult(
                                gitHubBrowserUseCase.getCommits(user, uiRepo.name),
                                uiRepo
                            )
                            Log.d("Repo", "loaded commits " + uiRepo.name)
                        }
                        Log.d("Repo", "repo loaded " + uiRepo.name)
                        uiRepo
                    } ?: emptyList())
                    if (gitHubReposObservableList.isEmpty()) {
                        viewStateData.postValue(ViewState.Empty())
                    } else {
                        viewStateData.postValue(ViewState.Success())
                    }
                }
                is ResponseModel.Failure -> viewStateData.postValue(ViewState.Fail(result.errorMessage))
            }
        }
    }

    private fun clearObservableList() {
        if (!gitHubReposObservableList.isEmpty()) {
            gitHubReposObservableList.clear()
        }
    }

    private fun handleCommitsResult(
        responseModel: ResponseModel<List<CommitItem>>,
        repo: GitHubRepoUiModel
    ) {
        when (responseModel) {
            is ResponseModel.Success -> {
                responseModel.responseData?.let { list ->
                    if (list.isNotEmpty()) {
                        var repoIndex = -1
                        var repoUiModel: GitHubRepoUiModel? = null
                        gitHubReposObservableList.forEachIndexed { index, element ->
                            run {
                                if (element.name == list[0].repoName) {
                                    repoIndex = index
                                    repoUiModel = element
                                    return@forEachIndexed
                                }
                            }
                        }
                        repoUiModel?.lastCommit = list.first().map(repo.name)

                        handler.post { gitHubReposObservableList[repoIndex] = repoUiModel }
                    }
                }
            }
            is ResponseModel.Failure -> {
            }
        }
    }

    /**
     * Here we can navigate to a list of commits for example
     */

    override fun onItemSelected(item: GitHubRepoUiModel) {
        //publish(NavEvent(NavigationDirs.COMMITS, item))
    }
}