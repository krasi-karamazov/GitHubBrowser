package kpk.dev.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kpk.dev.domain.entity.DomainEntity
import kpk.dev.presentation.base.SelectionViewModel
import kpk.dev.presentation.adapter.UniversalRecyclerViewAdapter

@BindingAdapter("viewModel", "items", "itemLayout", "orientation", requireAll = false)
fun bindRecyclerView(
    view: RecyclerView,
    viewModel: SelectionViewModel,
    items: ObservableArrayList<DomainEntity>,
    itemLayout: Int,
    orientation: Int?

) {
    if (view.layoutManager == null) {
        view.layoutManager =
            LinearLayoutManager(view.context, orientation ?: RecyclerView.VERTICAL, false)
    }

    if (view.adapter == null) {
        view.adapter = UniversalRecyclerViewAdapter(
            viewModel,
            items,
            itemLayout,
            viewModel::onItemSelected
        )
    }
}