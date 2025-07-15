package net.bi4vmr.study

import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView适配器的封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseAdapter<T : ListItem, VH : BaseViewHolder<T>>(

    /**
     * 数据源。
     */
    protected val dataSource: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<VH>() {

    private var generation: Int = 0

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = dataSource[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: T = dataSource[position]
        return item.getViewType()
    }

    fun submit(newData: List<T>) {
        dataSource.clear()
        dataSource.addAll(newData)
        notifyDataSetChanged()
    }
}
