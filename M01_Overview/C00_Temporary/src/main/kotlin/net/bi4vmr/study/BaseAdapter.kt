package net.bi4vmr.study

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * RecyclerView适配器的封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseAdapter<T : ListItem, VH : BaseViewHolder<T>>(


) : RecyclerView.Adapter<VH>() {

    /**
     * 当前Adapter所绑定的RecyclerView。
     */
    private var mRecyclerView: RecyclerView? = null

    /**
     * 内部数据源。
     */
    private val dataSource: MutableList<T> = mutableListOf()

    /**
     * 只读数据源。
     *
     * 对外曝露的数据源，只读。
     */
    private var mReadOnlyDataSource: List<T> = listOf()

    /**
     * 最新的异步更新任务序号。
     *
     * 每个异步任务开始时将该序号加 `1` 并保存在自身的线程中，差异对比完毕后与全局变量比较，如果任务序号与全局变量相同，说明任务有效，可以更
     * 新列表；否则说明已经有更晚开始的任务更新了列表，当前任务没必要再更新列表。
     */
    private var mUpdateTaskSequence: Int = 0

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
    }

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

    @JvmOverloads
    fun submit(newData: List<T>, actionAfterUpdate: (() -> Unit)? = null) {
        val taskSequence = ++mUpdateTaskSequence

        dataSource.clear()
        dataSource.addAll(newData)

        CoroutineScope(Dispatchers.Main).launch {
            if (taskSequence == mUpdateTaskSequence) {
                notifyDataSetChanged()
                mRecyclerView?.post {
                    actionAfterUpdate?.invoke()
                }
            }
        }
    }

    private inner class A : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.getViewType() == newItem.getViewType()
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return false
        }
    }
}
