package net.bi4vmr.bookkeeper.ui.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.bookkeeper.databinding.AccountSpinnerItemBinding
import net.bi4vmr.bookkeeper.ui.account.AccountVO
import net.bi4vmr.tool.android.ui.baservadapter.binding.BindingViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.binding.SimpleBindingAdapter

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestAdatper : SimpleBindingAdapter<AccountVO>(AccountSpinnerItemBinding::class.java, VH::class.java) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    private inner class VH(binding: AccountSpinnerItemBinding) :
        BindingViewHolder<AccountSpinnerItemBinding, AccountVO>(binding) {

        override fun bindData(item: AccountVO) {
            binding.tvName.text = item.data.name
        }
    }
}
