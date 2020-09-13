package kpk.dev.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VDB : ViewDataBinding, V : ViewModel> : Fragment() {

    private lateinit var dataBinding: VDB

    /**
     * THis variable is used to determine whether we're in tablet mode or not. It's set in the bools.xml
     */

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected val viewModel: V by lazy {
        ViewModelProvider(this).get(getViewModelClass())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel as LifecycleObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        dataBinding.lifecycleOwner = this
        initBinding(dataBinding)
        return dataBinding.root
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel as LifecycleObserver)
        super.onDestroy()
    }

    protected abstract fun initBinding(binding: VDB)

    protected abstract fun getViewModelClass(): Class<V>
}