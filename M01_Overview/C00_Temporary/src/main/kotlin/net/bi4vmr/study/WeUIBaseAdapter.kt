package net.bi4vmr.study.net.bi4vmr.study

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
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mConvertViews = SparseArray<View>()
    protected var mLInflater: LayoutInflater = LayoutInflater.from(context)

    protected abstract fun onBindData(viewHolder: RecyclerView.ViewHolder, position: Int, item: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType < 0 || viewType > layoutIds.size)
            throw ArrayIndexOutOfBoundsException("layoutIndex")

        if (layoutIds.isEmpty())
            throw IllegalArgumentException("not layoutId")

        val layoutId = layoutIds[viewType]
        val view: View = mConvertViews.get(layoutId) ?: mLInflater.inflate(layoutId, parent, false)

        // if (viewHolder == null || viewHolder?.getLayoutId() != layoutId) {
        //     viewHolder = BaseHolder(context, layoutId, view)
        // }
        return VH(view)
    }

    class VH(view:View):RecyclerView.ViewHolder(view){

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        // holder.itemView.setOnClickListener {
        //     weuiItemClick?.onItemClick(it, position, item)
        // }
        onBindData(holder, position, item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIndex(position, list[position])
    }

    open fun getLayoutIndex(position: Int, item: T) = 0

     fun addAll(list: List<T>): Boolean {
        val result = this.list.addAll(list)
        notifyItemRangeInserted(0, this.list.size)
        return result
    }

     fun addAll(position: Int, list: List<T>): Boolean {
        val result = this.list.addAll(position, list)
        notifyItemRangeInserted(0, this.list.size)
        return result
    }

     fun add(data: T) {
        list.add(data)
        notifyItemInserted(list.indexOf(data))
    }

     fun add(position: Int, data: T) {
        list.add(position, data)
        notifyItemInserted(position)
    }

     fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

     fun contains(data: T): Boolean {
        return list.contains(data)
    }

     fun getData(index: Int): T {
        return list[index]
    }

     fun getData(): List<T> {
        return list
    }

     fun modify(oldData: T, newData: T) {
        modify(list.indexOf(oldData), newData)
    }

     fun modify(index: Int, newData: T) {
        list[index] = newData
        notifyItemChanged(index)
    }

     fun remove(data: T): Boolean {
        return remove(list.indexOf(data))
    }

     fun remove(index: Int): Boolean {
        val result = list.removeAt(index)
        notifyItemRemoved(index)
        return result != null
    }

}