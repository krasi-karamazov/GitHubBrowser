package kpk.dev.presentation.ui.fragment.commits

import kpk.dev.presentation.R
import kpk.dev.presentation.base.BaseFragment
import kpk.dev.presentation.databinding.FragmentCommitsBinding

class CommitsFragment : BaseFragment<FragmentCommitsBinding, CommitsViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_commits

    override fun initBinding(binding: FragmentCommitsBinding) {
        binding.viewModel = viewModel
    }

    override fun getViewModelClass(): Class<CommitsViewModel> = CommitsViewModel::class.java
}