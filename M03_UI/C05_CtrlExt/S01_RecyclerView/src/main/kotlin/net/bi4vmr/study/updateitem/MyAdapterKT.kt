package net.bi4vmr.study.updateitem

import android.annotation.SuppressLint
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
    private val mDataSource: MutableList<ItemVOKT>
) : RecyclerView.Adapter<MyAdapterKT.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("TestApp", "OnCreateViewHolder. ViewType:[$viewType]")
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.list_item_type1, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TestApp", "OnBindViewHolder. Position:[$position]")
        val vo: ItemVOKT = mDataSource[position]
        holder.bindData(vo)
    }

    /**
     * RecyclerView将数据与ViewHolder绑定的回调方法。
     *
     * 当Adapter的 `notifyItemChanged(int position, Object payload)` 方法被调用时，该方法将被触发，第三参数 `payloads` 对应调用
     * 者传入的 `payload`。
     *
     * @param[holder]   ViewHolder实例。
     * @param[position] 表项在列表中的位置索引。
     * @param[payloads] Payload列表，其中的元素类型可以根据需要自行定义。
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        Log.i("TestApp", "OnBindViewHolder. Position:[" + position + "] PayloadsNum:[" + payloads.size + "]")
        // 如果Payload列表内容为空，则执行普通的 `onBindViewHolder()` 方法。
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        // 短时间内多次更新同一表项时，Payload列表中可能有多个项，可以根据需要选择其中的一项。
        val flags: Any = payloads.last()
        // 如果Payload不能被解析为Flags，则忽略。
        if (flags !is Int) {
            Log.d("TestApp", "Payload type is unknown.")
            return
        }

        Log.d("TestApp", "Payload flags:[$flags]")
        val vo: ItemVOKT = mDataSource[holder.adapterPosition]
        if (flags and UpdateFlagsKT.FLAG_TITLE != 0) {
            holder.updateTitle(vo)
        }
        if ((flags and UpdateFlagsKT.FLAG_INFO) != 0) {
            holder.updateInfo(vo)
        }
    }

    override fun getItemCount(): Int {
        return mDataSource.size
    }

    // 获取数据源
    fun getDataSource(): List<ItemVOKT> = mDataSource

    /**
     * 更新指定的表项。
     *
     * 更新指定的表项，不影响列表中的其它表项。
     *
     * @param[position] 待更新的位置。
     * @param[data]     新的表项。
     */
    fun updateItem(position: Int, data: ItemVOKT, flags: Int) {
        // 更新数据源
        mDataSource[position] = data
        // 通知RecyclerView指定表项被更改，刷新控件显示。
        notifyItemChanged(position, flags)
    }

    /**
     * 更新RecyclerView中的所有表项。
     *
     * @param[newDatas] 新的数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    fun reloadItems(newDatas: List<ItemVOKT>) {
        // 清空数据源
        mDataSource.clear()
        // 重新填充数据源
        mDataSource.addAll(newDatas)
        // 通知RecyclerView数据源改变
        notifyDataSetChanged()
    }

    /* 表项的ViewHolder类 */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvTitle: TextView? = null
        private var tvInfo: TextView? = null

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvInfo = itemView.findViewById(R.id.tvInfo)
        }

        fun bindData(vo: ItemVOKT) {
            updateTitle(vo)
            updateInfo(vo)
        }

        fun updateTitle(vo: ItemVOKT) {
            tvTitle?.text = vo.title
        }

        fun updateInfo(vo: ItemVOKT) {
            tvInfo?.text = vo.info
        }
    }
}
