package kpk.dev.presentation.ui.fragment.repositories

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import kpk.dev.domain.usecase.IGitHubBrowserUseCase
import kpk.dev.presentation.base.BaseViewModel
import kpk.dev.presentation.base.ItemClickHandlerViewModel
import kpk.dev.presentation.mappers.map
import kpk.dev.presentation.model.GitHubRepoUiModel
import kpk.dev.presentation.ui.ViewState
import javax.inject.Inject

class RepositoriesViewModel @ViewModelInject constructor(private val gitHubBrowserUseCase: IGitHubBrowserUseCase) : ItemClickHandlerViewModel<GitHubRepoUiModel>() {

    val viewStateData = MutableLiveData<ViewState>()

    val gitHubReposObservableList = ObservableArrayList<GitHubRepoUiModel>()

    fun onItemSelected(repoName: String) {
        //publish(NavEvent(NavigationDirs.COMMITS, item))
    }

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
                    gitHubReposObservableList.addAll(result.responseData?.map { it.map() } ?: emptyList())
                    viewStateData.postValue(ViewState.Success())
                }
                is ResponseModel.Failure -> viewStateData.postValue(ViewState.Fail(result.errorMessage))
            }
        }
    }

    override fun onItemSelected(item: GitHubRepoUiModel, actionType: Int) {
        Log.d("Hello", item.name)
    }
}