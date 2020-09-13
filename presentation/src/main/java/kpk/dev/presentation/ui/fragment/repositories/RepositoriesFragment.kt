package kpk.dev.presentation.ui.fragment.repositories

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kpk.dev.presentation.R
import kpk.dev.presentation.base.BaseFragment
import kpk.dev.presentation.databinding.FragmentRepositoriesBinding
import kpk.dev.presentation.navigation.NavigationDirs

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_repositories

    override fun initBinding(binding: FragmentRepositoriesBinding) {
        binding.viewModel = viewModel
        viewModel.subscribe(Observer {
            when (it.navDirections) {
                NavigationDirs.COMMITS -> {
                    /*findNavController()
                        .navigate(HomeFragmentDirections.navigateToDetailsAction(data as SupportDeskUIModel))*/
                }
            }
        }, this)
    }

    override fun getViewModelClass(): Class<RepositoriesViewModel> = RepositoriesViewModel::class.java
}