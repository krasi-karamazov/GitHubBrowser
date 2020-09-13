package kpk.dev.presentation.base

abstract class SelectionViewModel : BaseViewModel() {
    abstract fun onItemSelected(item: Any)
}