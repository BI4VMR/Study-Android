package net.bi4vmr.study.diffutilasync

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
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
    dataSource: MutableList<ItemVOKT>
) : RecyclerView.Adapter<MyAdapterKT.MyViewHolder>() {

    private val differ: AsyncListDiffer<ItemVOKT> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<ItemVOKT>() {

        override fun areItemsTheSame(oldItem: ItemVOKT, newItem: ItemVOKT): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemVOKT, newItem: ItemVOKT): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(oldItem: ItemVOKT, newItem: ItemVOKT): Any {
            var flags = 0
            if (oldItem.title != newItem.title) {
                flags = flags or UpdateFlagsKT.FLAG_TITLE
            }
            if (oldItem.info != newItem.info) {
                flags = flags or UpdateFlagsKT.FLAG_INFO
            }

            return flags
        }
    })

    init {
        differ.submitList(dataSource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("TestApp", "OnCreateViewHolder. ViewType:[$viewType]")
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.list_item_type1, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TestApp", "OnBindViewHolder. Position:[$position]")
        val vo: ItemVOKT = differ.currentList[position]
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
        val vo: ItemVOKT = differ.currentList[holder.adapterPosition]
        if (flags and UpdateFlagsKT.FLAG_TITLE != 0) {
            holder.updateTitle(vo)
        }
        if ((flags and UpdateFlagsKT.FLAG_INFO) != 0) {
            holder.updateInfo(vo)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    /**
     * 获取数据源的一份副本。
     *
     * 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemVO的列表，是当前数据源的副本。
     */
    fun getCopyOfDataSource(): MutableList<ItemVOKT> {
        val newList: MutableList<ItemVOKT> = ArrayList()
        for (i in differ.currentList.indices) {
            // 创建新的对象，并复制原对象的属性。
            val item: ItemVOKT = differ.currentList[i]
            val title = item.title
            val comment = item.info
            newList.add(ItemVOKT(title, comment))
        }
        return newList
    }

    /**
     * 使用DiffUtil更新列表。
     *
     * @param[newDatas] 数据源。
     */
    fun submitList(newDatas: List<ItemVOKT>) {
        differ.submitList(newDatas)
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
