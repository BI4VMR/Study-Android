package net.bi4vmr.study.viewtype

import android.util.Log
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
class MyAdapterKT(

    /**
     * 数据源。
     */
    private val mDataSource: MutableList<ListItemKT>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * RecyclerView创建ViewHolder的回调方法。
     *
     * 当RecyclerView创建新的ViewHolder时，将会回调此方法。我们应当在此处创建表项对应的View，并封装进ViewHolder返回给RecyclerView。
     *
     * @param[parent]   当前表项将要被放入的视图容器。
     * @param[viewType] 待创建的View类型。
     * @return ViewHolder实例。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        val vh: RecyclerView.ViewHolder
        // 根据ViewType参数创建对应的视图实例与ViewHolder
        when (viewType) {
            1 -> {
                val view: View = inflater.inflate(R.layout.list_item_type1, parent, false)
                vh = Type1VH(view)
            }
            2 -> {
                val view: View = inflater.inflate(R.layout.list_item_type2, parent, false)
                vh = Type1VH(view)
            }
            else -> {
                throw IllegalArgumentException("Unknown view type [$viewType]!")
            }
        }
        return vh
    }

    /**
     * RecyclerView将数据与ViewHolder绑定的回调方法。
     *
     * 当RecyclerView将View显示到屏幕上之前，将会回调此方法。我们需要从数据源中根据位置索引找到对应的数据项，然后通过ViewHolder设置各个
     * 控件，实现View与数据的同步。
     *
     * @param[holder]   ViewHolder实例。
     * @param[position] 表项在列表中的位置索引。
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Type1VH -> {
                holder.bindData()
            }
            is Type2VH -> {
                holder.bindData()
            }
            else -> {
                Log.w("Test", "Unknown ViewHolder!")
            }
        }
    }

    /**
     * RecyclerView获取表项总数的回调方法。
     *
     * @return 表项总数。
     */
    override fun getItemCount(): Int {
        return mDataSource.size
    }

    /**
     * RecyclerView获取表项类型的回调方法。
     *
     * @param [position]表项在列表中的位置。
     */
    override fun getItemViewType(position: Int): Int {
        // 我们约定所有列表项都实现ListItem接口，因此可以调用其中的方法获取ViewType。
        return mDataSource[position].getItemType()
    }

    /**
     * 第一种表项的ViewHolder。
     */
    inner class Type1VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvTitle: TextView? = null
        private var tvInfo: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvInfo = itemView.findViewById(R.id.ivIcon)
        }

        fun bindData() {
            val vo: Type1VOKT = mDataSource[adapterPosition] as Type1VOKT
            tvTitle?.text = vo.title
            tvInfo?.text = vo.info
        }
    }

    /**
     * 第二种表项的ViewHolder。
     */
    inner class Type2VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvInfo: TextView? = null

        init {
            tvInfo = itemView.findViewById(R.id.ivIcon)
        }

        fun bindData() {
            val vo: Type2VOKT = mDataSource[adapterPosition] as Type2VOKT
            tvInfo?.text = vo.info
        }
    }
}
