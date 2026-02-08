package net.bi4vmr.bookkeeper.ui.common

import androidx.recyclerview.widget.RecyclerView

/**
 * [RVSpinnerAdapter] 内部事件接口。
 *
 * [RVSpinnerAdapter] 的实现类需要通过该接口向 [RVSpinner] 通告选中项变更等事件。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
interface RVSpinnerAdapterListener {

    /**
     * 选中项已变更。
     *
     * @param[position] 选中项在 [RecyclerView] 中的索引。
     */
    fun onItemSelected(position: Int)
}
