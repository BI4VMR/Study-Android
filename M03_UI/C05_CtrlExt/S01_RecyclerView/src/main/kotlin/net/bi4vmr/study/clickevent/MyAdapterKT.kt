package net.bi4vmr.study.clickevent

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
    private val mDataSource: MutableList<SimpleVOKT>
) : RecyclerView.Adapter<MyAdapterKT.MyViewHolder>() {

    /**
     * 表项点击监听器实现。
     */
    private var listener: ItemClickListener? = null

    /**
     * RecyclerView创建ViewHolder的回调方法。
     *
     * 当RecyclerView创建新的ViewHolder时，将会回调此方法。我们应当在此处创建表项对应的View，并封装进ViewHolder返回给RecyclerView。
     *
     * @param[parent]   当前表项将要被放入的视图容器。
     * @param[viewType] 待创建的View类型。
     * @return ViewHolder实例。
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("TestApp", "OnCreateViewHolder. ViewType:[$viewType]")
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        /*
         * 将布局文件实例化为View对象。
         *
         * 此处的第三参数必须为"false"，因为控件将由ViewHolder控制Attach与Detach。
         */
        val itemView: View = inflater.inflate(R.layout.list_item_simple, parent, false)

        // 创建ViewHolder实例，并将View对象保存在其中。
        return MyViewHolder(itemView)
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
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TestApp", "OnBindViewHolder. Position:[$position]")
        holder.bindData()
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
     * 表项被点击事件的监听器。
     */
    fun interface ItemClickListener {

        /**
         * 表项被点击事件。
         *
         * 表项被点击时，该方法将被回调。
         *
         * @param[index] 表项位置索引。
         * @param[data]  表项对应的数据项。
         */
        fun onClick(index: Int, data: SimpleVOKT)
    }

    /**
     * 设置表项点击事件监听器。
     *
     * @param[listener] 监听器实现。
     */
    fun setItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    /**
     * ViewHolder
     *
     * 自定义ViewHolder类，内部保存了View实例，便于复用。
     */
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

            // 当根布局被点击时，触发监听器。
            itemView.setOnClickListener {
                listener?.onClick(adapterPosition, vo)
            }
        }
    }
}
