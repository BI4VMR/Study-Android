package net.bi4vmr.tool.android.ui.baservadapter.base

import android.util.Log
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * RecyclerView适配器的通用封装。
 *
 * 参考 [AsyncListDiffer] 实现了异步更新功能，并且也支持同步更新指定的表现。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseAdapter<T : ListItem, VH : BaseViewHolder<T>>
@JvmOverloads constructor(

    /**
     * 内部数据源。
     */
    private var mDataSource: List<T> = listOf(),

    /**
     * 后台任务的协程环境。
     *
     * 用于执行差异对比、异步更新等任务，默认值为 `Default` 调度器构建的作用域。
     */
    private val bgScope: CoroutineScope = CoroutineScope(Dispatchers.Default),

    /**
     * 前台任务的协程环境。
     *
     * 用于更新界面，默认值为 `Main` 调度器构建的作用域。
     *
     * 此参数仅供单元测试场景使用，其他场景下调用者无需自行传入协程环境。
     */
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : RecyclerView.Adapter<VH>() {

    open val tag: String = BaseAdapter::class.simpleName ?: "BaseAdapter"

    /**
     * 当前Adapter所绑定的RecyclerView。
     */
    private var mRecyclerView: RecyclerView? = null

    /**
     * 最新的异步更新任务序号。
     *
     * 每个异步任务开始时将该序号加 `1` 并保存在自身的线程中，差异对比完毕后与全局变量比较：
     *
     * 如果任务序号与全局变量相同，说明任务有效，可以更新列表；否则说明已经有更晚开始的任务更新了列表，当前任务没必要再更新列表。
     */
    private var mUpdateTaskSequence: Int = 0

    /**
     * DiffUtil比较回调。
     */
    private var mDiffCallback: DiffUtil.ItemCallback<T> = DefaultDiffCallback()

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = mDataSource[position]
        holder.bindData(item)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            // 默认选择最新的Payload，忽略旧的Payload。
            val payload = payloads.last()
            if (payload !is Int) {
                // TODO
                return
            }

            val item: T = mDataSource[position]
            holder.bindData(item, payload)
        }
    }

    override fun getItemCount(): Int {
        return mDataSource.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: T = mDataSource[position]
        return item.getViewType()
    }

    @JvmOverloads
    fun submit(newData: List<T>, actionAfterUpdate: (() -> Unit)? = null) {
        val taskSequence = ++mUpdateTaskSequence

        if (newData == mDataSource) {
            Log.i(tag, "New list is same as old.")
            mRecyclerView?.post {
                actionAfterUpdate?.invoke()
            }
            return
        }

        // 快速处理单个表项为空的情况，无需对比差异。
        // todo

        // 开始计算差异
        bgScope.launch {
            val oldData = mDataSource
            val diffCallback = mDiffCallback
            val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

                override fun getOldListSize(): Int = oldData.size

                override fun getNewListSize(): Int = newData.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldData[oldItemPosition]
                    val newItem = newData[newItemPosition]
                    return diffCallback.areItemsTheSame(oldItem, newItem)
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItem = oldData[oldItemPosition]
                    val newItem = newData[newItemPosition]
                    return diffCallback.areContentsTheSame(oldItem, newItem)
                }

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                    val oldItem = oldData[oldItemPosition]
                    val newItem = newData[newItemPosition]
                    return diffCallback.getChangePayload(oldItem, newItem)
                }
            })

            if (taskSequence == mUpdateTaskSequence) {
                uiScope.launch {
                    mDataSource = newData
                    diffResult.dispatchUpdatesTo(this@BaseAdapter)
                    // 更新完毕后执行其他任务
                    mRecyclerView?.post {
                        actionAfterUpdate?.invoke()
                    }
                }
            }
        }
    }

    /**
     * 默认的DiffUtil比较回调实现。
     */
    private inner class DefaultDiffCallback : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            // 首先比较ViewType，如果ViewType不同，则两个表项一定是不同的。
            val viewTypeSame = oldItem.getViewType() == newItem.getViewType()
            if (!viewTypeSame) return false

            // 如果ViewType相同，则调用两个表项的 `equals()` 方法进行比较。
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}
