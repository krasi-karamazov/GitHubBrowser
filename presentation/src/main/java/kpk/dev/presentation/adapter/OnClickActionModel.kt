package kpk.dev.presentation.adapter

abstract class OnClickActionModel : OnClickAction {
    val actionOpen = 0
    override var onClick: (action: Int) -> Unit = {}
}