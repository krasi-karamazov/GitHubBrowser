package kpk.dev.presentation.ui.fragment.repositories

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import kpk.dev.domain.usecase.IGitHubBrowserUseCase
import kpk.dev.presentation.base.BaseViewModel
import kpk.dev.presentation.ui.ViewState
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(private val gitHubBrowserUseCase: IGitHubBrowserUseCase) : BaseViewModel() {

    val viewStateData = MutableLiveData<ViewState>()

    val gitHubReposObservableList = ObservableArrayList<GitHubRepoItem>()

    fun onItemSelected(repoName: String) {
        //publish(NavEvent(NavigationDirs.COMMITS, item))
    }

    override fun onResume() {
        getRepoData("android", true)
    }

    fun getRepoData(user: String, initialLoad: Boolean = false) {
        viewModelScope.launch {
            viewStateData.postValue(ViewState.Loading())

            when (val result = gitHubBrowserUseCase.getRepositories(user, initialLoad)) {
                is ResponseModel.Success -> {
                    if (!gitHubReposObservableList.isEmpty()) {
                        gitHubReposObservableList.clear()
                    }
                    gitHubReposObservableList.addAll(result.responseData ?: emptyList())
                    viewStateData.postValue(ViewState.Success())
                }
                is ResponseModel.Failure -> viewStateData.postValue(ViewState.Fail(result.errorMessage))
            }
        }
    }
}