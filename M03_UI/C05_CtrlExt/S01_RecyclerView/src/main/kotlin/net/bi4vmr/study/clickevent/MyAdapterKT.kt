package net.bi4vmr.study.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.study.R

/**
 * RecyclerView的适配器。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyAdapterKT : RecyclerView.Adapter<MyAdapterKT.MyViewHolder>() {

    private val mDataSource: MutableList<SimpleVOKT> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // 将布局文件实例化为View对象
        val itemView: View = LayoutInflater.from(parent.context)
            // 此处的第三参数必须为"false"，因为控件将由ViewHolder控制Attach与Detach。
            .inflate(R.layout.list_item_simple, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return mDataSource.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvTitle: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }

        /**
         * 绑定数据。
         *
         * 取出数据源集合中与当前Item位置一致的数据项，并更新到View中的控件。
         */
        fun bindData() {
            // 获取当前Item位置对应的数据项
            val vo: SimpleVOKT = mDataSource[adapterPosition]
            // 将数据设置到视图中
            tvTitle?.text = vo.title
        }
    }
}
