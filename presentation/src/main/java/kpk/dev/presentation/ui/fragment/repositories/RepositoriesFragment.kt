package kpk.dev.presentation.ui.fragment.repositories

import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kpk.dev.presentation.R
import kpk.dev.presentation.adapter.UniversalRecyclerViewAdapter
import kpk.dev.presentation.base.BaseFragment
import kpk.dev.presentation.databinding.FragmentRepositoriesBinding
import kpk.dev.presentation.model.GitHubRepoUiModel
import kpk.dev.presentation.navigation.NavigationDirs
import kpk.dev.presentation.ui.ViewState
import kpk.dev.presentation.utils.USER_QUERY_KEY
import javax.inject.Inject

@AndroidEntryPoint
class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val reposAdapter: UniversalRecyclerViewAdapter<GitHubRepoUiModel> by lazy {
        UniversalRecyclerViewAdapter(
            viewModel,
            viewModel.gitHubReposObservableList,
            R.layout.item_repository
        )
    }
    override val layoutId: Int
        get() = R.layout.fragment_repositories

    override fun initBinding(binding: FragmentRepositoriesBinding) {
        viewModel.subscribe(Observer {
            when (it.navDirections) {
                NavigationDirs.COMMITS -> {
                    findNavController()
                        .navigate(RepositoriesFragmentDirections.navigateToCommitsAction((it.data as GitHubRepoUiModel).name))
                }
            }
        }, this)
        binding.lifecycleOwner = this
        binding.rvRepositories.apply {
            this.adapter = reposAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
        binding.etSearchQuery.setText(sharedPreferences.getString(USER_QUERY_KEY, ""))
        viewModel.viewStateData.observe(this,
            Observer {
                when (it) {
                    is ViewState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.etSearchQuery.visibility = View.GONE
                        binding.rvRepositories.visibility = View.GONE
                    }
                    is ViewState.Fail -> {
                        binding.progress.visibility = View.GONE
                        binding.rvRepositories.visibility = View.GONE
                        binding.etSearchQuery.visibility = View.VISIBLE
                        Snackbar.make(
                            binding.mainContainer,
                            it.msg ?: getString(R.string.error),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is ViewState.Success -> {
                        binding.progress.visibility = View.GONE
                        binding.etSearchQuery.visibility = View.VISIBLE
                        binding.rvRepositories.visibility = View.VISIBLE
                        sharedPreferences.edit()
                            .putString(USER_QUERY_KEY, binding.etSearchQuery.text.toString())
                            .apply()
                    }
                    is ViewState.Empty -> {
                        binding.progress.visibility = View.GONE
                        binding.rvRepositories.visibility = View.GONE
                        binding.etSearchQuery.visibility = View.VISIBLE
                        Snackbar.make(
                            binding.mainContainer,
                            getString(R.string.empty_result_prompt),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )

        binding.etSearchQuery.setOnEditorActionListener { view, action, _ ->
            if (action == EditorInfo.IME_ACTION_GO) {
                viewModel.getRepoData(view.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun getViewModelClass(): Class<RepositoriesViewModel> =
        RepositoriesViewModel::class.java
}