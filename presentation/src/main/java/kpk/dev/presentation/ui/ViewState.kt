package kpk.dev.presentation.ui

/**
 * Simple class which is used to notify the view of basic changes.
 * Currently we don't use it in the Fragments, but the viewmodels do send it, and is used in Unit tests
 */
sealed class ViewState {
    class Loading : ViewState()
    class Fail(val msg: String?) : ViewState()
    class Success : ViewState()
    class Empty : ViewState()
}