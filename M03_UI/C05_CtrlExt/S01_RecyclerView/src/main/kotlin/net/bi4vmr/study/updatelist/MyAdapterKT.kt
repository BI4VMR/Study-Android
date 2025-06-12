package net.bi4vmr.study.updatelist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.study.R
import java.util.Collections

/**
 * RecyclerView的适配器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyAdapterKT(

    /**
     * 数据源。
     */
    private val mDataSource: MutableList<SimpleVOKT>
) : RecyclerView.Adapter<MyAdapterKT.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("TestApp", "OnCreateViewHolder. ViewType:[$viewType]")
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.list_item_simple, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TestApp", "OnBindViewHolder. Position:[$position]")
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return mDataSource.size
    }

    /**
     * 更新指定的表项。
     *
     * 更新指定的表项，不影响列表中的其它表项。
     *
     * @param[position] 待更新的位置。
     * @param[data]     新的表项。
     */
    fun updateItem(position: Int, data: SimpleVOKT) {
        // 更新数据源
        mDataSource[position] = data
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemChanged(position)
    }

    /**
     * 向指定位置插入表项。
     *
     * 将新的表项插入到指定位置，若该位置已存在表项，则将其本身以及后继表项都后移一位。
     *
     * @param[position] 待插入的位置。
     * @param[data]     新的表项。
     */
    fun addItem(position: Int, data: SimpleVOKT) {
        // 更新数据源
        mDataSource.add(position, data)
        // 通知RecyclerView新的表项被插入，刷新控件显示。
        notifyItemInserted(position)
    }

    /**
     * 移除指定的表项。
     *
     * 移除指定的表项，若该位置之后存在表项，则将这些表项都前移一位。
     *
     * @param[position] 待移除的位置。
     */
    fun removeItem(position: Int) {
        // 更新数据源
        mDataSource.removeAt(position)
        // 通知RecyclerView指定的表项被移除，刷新控件显示。
        notifyItemRemoved(position)
    }

    /**
     * 将指定的表项移动至新位置。
     *
     * @param[srcPosition] 源位置。
     * @param[dstPosition] 目标位置。
     */
    fun moveItem(srcPosition: Int, dstPosition: Int) {
        // 如果源位置与目标位置相同，则无需移动。
        if (srcPosition == dstPosition) {
            return
        }

        // 更新数据源
        Collections.swap(mDataSource, srcPosition, dstPosition)
        // 通知RecyclerView表项被移动，刷新控件显示。
        notifyItemMoved(srcPosition, dstPosition)
    }

    /**
     * 更新RecyclerView中的所有表项。
     *
     * @param[newDatas] 新的数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    fun reloadItems(newDatas: List<SimpleVOKT>) {
        // 清空数据源
        mDataSource.clear()
        // 重新填充数据源
        mDataSource.addAll(newDatas)
        // 通知RecyclerView数据源改变
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 保存控件的引用，以便后续绑定数据。
        private var tvTitle: TextView? = null
        private var ivIcon: ImageView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            ivIcon = itemView.findViewById(R.id.ivIcon)
        }

        /**
         * 绑定数据。
         *
         * 取出数据源集合中与当前表项位置对应的数据项，并更新View中的控件。
         */
        fun bindData() {
            // 获取当前表项位置对应的数据项
            val vo: SimpleVOKT = mDataSource[adapterPosition]
            // 将数据设置到视图中
            tvTitle?.text = vo.title
        }
    }
}
