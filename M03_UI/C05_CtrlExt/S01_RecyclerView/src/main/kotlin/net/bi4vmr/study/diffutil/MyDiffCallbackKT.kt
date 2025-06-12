package net.bi4vmr.study.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * 自定义DiffUtil。
 * <p>
 * 用于设置表项的比较规则。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyDiffCallbackKT(
    private val oldList: List<ItemVOKT>,
    private val newList: List<ItemVOKT>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}