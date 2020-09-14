package kpk.dev.presentation.ui.fragment.repositories

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
import kpk.dev.presentation.navigation.NavEvent
import kpk.dev.presentation.navigation.NavigationDirs
import kpk.dev.presentation.ui.ViewState

class RepositoriesViewModel @ViewModelInject constructor(private val gitHubBrowserUseCase: IGitHubBrowserUseCase) : ItemClickHandlerViewModel<GitHubRepoUiModel>() {

    val viewStateData = MutableLiveData<ViewState>()

    val gitHubReposObservableList = ObservableArrayList<GitHubRepoUiModel>()

    override fun onResume() {
        getRepoData("android", false)
    }

    fun getRepoData(user: String, initialLoad: Boolean = false) {
        viewModelScope.launch {
            viewStateData.postValue(ViewState.Loading())

            when (val result = gitHubBrowserUseCase.getRepositories(user, initialLoad)) {
                is ResponseModel.Success -> {
                    if (!gitHubReposObservableList.isEmpty()) {
                        gitHubReposObservableList.clear()
                    }
                    gitHubReposObservableList.addAll(result.responseData?.map {
                        val uiRepo = it.map()
                        GlobalScope.launch {
                            Log.d("Repo", "loading commits " + uiRepo.name)
                            delay(1000)
                            handleCommitsResult(gitHubBrowserUseCase.getCommits(user, uiRepo.name), uiRepo)
                            Log.d("Repo", "loaded commits " + uiRepo.name)
                        }
                        Log.d("Repo", "repo loaded " + uiRepo.name)
                        uiRepo
                    } ?: emptyList())
                    viewStateData.postValue(ViewState.Success())
                }
                is ResponseModel.Failure -> viewStateData.postValue(ViewState.Fail(result.errorMessage))
            }
        }
    }

    private fun handleCommitsResult(responseModel: ResponseModel<List<CommitItem>>, repo: GitHubRepoUiModel) {
        when(responseModel) {
            is ResponseModel.Success -> {
                responseModel.responseData?.let { list->
                    repo.commits = list.map { commit -> commit.map(repo.name) }
                }
            }
            is ResponseModel.Failure -> {

            }
        }
    }

    override fun onItemSelected(item: GitHubRepoUiModel) {
        publish(NavEvent(NavigationDirs.COMMITS, item))
    }
}