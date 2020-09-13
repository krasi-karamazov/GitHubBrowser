package kpk.dev.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kpk.dev.presentation.BR
import kpk.dev.presentation.base.BaseViewModel
import kpk.dev.presentation.base.ItemClickHandlerViewModel

class UniversalRecyclerViewAdapter<T : OnClickAction>(
    private val viewModel: ItemClickHandlerViewModel<T>,
    private val items: ObservableArrayList<T>,
    private val itemLayoutId: Int
) : RecyclerView.Adapter<UniversalRecyclerViewAdapter.RecyclerItemViewHolder<T>>() {

    init {
        items.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
            override fun onChanged(sender: ObservableArrayList<T>?) = notifyDataSetChanged()

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) = notifyItemRangeRemoved(positionStart, itemCount)

            override fun onItemRangeMoved(
                sender: ObservableArrayList<T>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) = notifyItemMoved(fromPosition, toPosition)

            override fun onItemRangeInserted(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) = notifyItemRangeInserted(positionStart, itemCount)

            override fun onItemRangeChanged(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) = notifyItemRangeChanged(positionStart, itemCount)

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder<T> =
        RecyclerItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), itemLayoutId, parent, false
            ), viewModel
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class RecyclerItemViewHolder<T>(
        private val binding: ViewDataBinding,
        private val viewModel: BaseViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size
}