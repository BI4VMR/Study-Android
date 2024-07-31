package net.bi4vmr.study

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Author        : iot_xc
 * Date          : 2022/02/18 15:31
 * Email         : chaoxu@pateo.com.cn
 * Description   : 描写描述
 */
abstract class WeUIBaseAdapter<T>(
    protected val context: Context,
    protected val list: MutableList<T>,
    protected vararg val layoutIds: Int
) : RecyclerView.Adapter<BaseHolder>(), IWeUIRVHelper<T> {

    private val mConvertViews = SparseArray<View>()
    protected var mLInflater: LayoutInflater = LayoutInflater.from(context)
    var weuiItemClick: IWeUIItemClick<T>? = null

    protected abstract fun onBindData(viewHolder: BaseHolder, position: Int, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        if (viewType < 0 || viewType > layoutIds.size)
            throw ArrayIndexOutOfBoundsException("layoutIndex")

        if (layoutIds.isEmpty())
            throw IllegalArgumentException("not layoutId")

        val layoutId = layoutIds[viewType]
        val view: View = mConvertViews.get(layoutId) ?: mLInflater.inflate(layoutId, parent, false)

        var viewHolder: BaseHolder?
        view.let {
            viewHolder = if (view.tag == null) {
                null
            } else {
                view.tag as BaseHolder
            }
        }
        if (viewHolder == null || viewHolder?.getLayoutId() != layoutId) {
            viewHolder = BaseHolder(context, layoutId, view)
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val item = list[position]
        holder.itemView.setOnClickListener {
            weuiItemClick?.onItemClick(it, position, item)
        }
        onBindData(holder, position, item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIndex(position, list[position])
    }

    open fun getLayoutIndex(position: Int, item: T) = 0

    override fun addAll(list: List<T>): Boolean {
        val result = this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
        return result
    }

    override fun addAll(position: Int, list: List<T>): Boolean {
        val result = this.list.addAll(position, list)
        notifyItemRangeInserted(0, this.list.size)
        return result
    }

    override fun add(data: T) {
        list.add(data)
        notifyItemInserted(list.indexOf(data))
    }

    override fun add(position: Int, data: T) {
        list.add(position, data)
        notifyItemInserted(position)
    }

    override fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun contains(data: T): Boolean {
        return list.contains(data)
    }

    override fun getData(index: Int): T {
        return list[index]
    }

    override fun getData(): List<T> {
        return list
    }

    override fun modify(oldData: T, newData: T) {
        modify(list.indexOf(oldData), newData)
    }

    override fun modify(index: Int, newData: T) {
        list[index] = newData
        notifyItemChanged(index)
    }

    override fun remove(data: T): Boolean {
        return remove(list.indexOf(data))
    }

    override fun remove(index: Int): Boolean {
        val result = list.removeAt(index)
        notifyItemRemoved(index)
        return result != null
    }

}