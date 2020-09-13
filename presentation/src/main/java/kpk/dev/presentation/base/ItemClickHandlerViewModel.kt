package kpk.dev.presentation.base

abstract class ItemClickHandlerViewModel<T> : BaseViewModel() {
    abstract fun onItemSelected(item: T, actionType: Int)
}