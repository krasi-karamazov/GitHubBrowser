package kpk.dev.presentation.ui.fragment.repositories

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kpk.dev.presentation.R
import kpk.dev.presentation.adapter.UniversalRecyclerViewAdapter
import kpk.dev.presentation.base.BaseFragment
import kpk.dev.presentation.databinding.FragmentRepositoriesBinding
import kpk.dev.presentation.model.GitHubRepoUiModel
import kpk.dev.presentation.navigation.NavigationDirs

@AndroidEntryPoint
class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>() {
    private val reposAdapter: UniversalRecyclerViewAdapter<GitHubRepoUiModel> by lazy {
        UniversalRecyclerViewAdapter(viewModel, viewModel.gitHubReposObservableList, R.layout.item_repository)
    }
    override val layoutId: Int
        get() = R.layout.fragment_repositories

    override fun initBinding(binding: FragmentRepositoriesBinding) {
        viewModel.subscribe(Observer {
            when (it.navDirections) {
                NavigationDirs.COMMITS -> {
                    /*findNavController()
                        .navigate(HomeFragmentDirections.navigateToDetailsAction(data as SupportDeskUIModel))*/
                }
            }
        }, this)
        binding.lifecycleOwner = this
        binding.rvRepositories.apply {
            this.adapter = reposAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun getViewModelClass(): Class<RepositoriesViewModel> = RepositoriesViewModel::class.java
}