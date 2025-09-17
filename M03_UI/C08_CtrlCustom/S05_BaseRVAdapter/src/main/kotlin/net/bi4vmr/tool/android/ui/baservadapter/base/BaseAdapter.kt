package net.bi4vmr.tool.android.ui.baservadapter.base

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

/**
 * RecyclerView适配器的通用封装。
 *
 * 参考 [AsyncListDiffer] 实现了异步更新功能，并且也支持同步更新指定的表项。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseAdapter<T : ListItem, VH : BaseViewHolder<T>>
@JvmOverloads constructor(

    /**
     * 内部数据源。
     */
    private val mDataSource: MutableList<T> = CopyOnWriteArrayList(),

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

    /**
     * 日志Tag。
     */
    protected open val tag: String = javaClass.simpleName

    /**
     * ViewType映射表。
     *
     * 配置ViewType、Layout资源ID与ViewHolder的映射关系，以便 [onCreateViewHolder] 方法自动创建ViewHolder实例。
     *
     * 如果调用者不希望使用本工具内置的映射方案，也可以自行重写 [onCreateViewHolder] 方法。
     */
    private val viewTypeMapper: MutableMap<Int, Pair<Int, Class<*>>> = ConcurrentHashMap()

    fun setViewTypeMappers(mappers: Map<Int, Pair<Int, Class<*>>>) {
        synchronized(viewTypeMapper) {
            viewTypeMapper.clear()
            viewTypeMapper.putAll(mappers)
        }
    }

    fun clearViewTypeMappers() {
        viewTypeMapper.clear()
    }

    fun addViewTypeMapper(viewType: Int, @LayoutRes layoutID: Int, viewHolderClass: Class<*>) {
        synchronized(viewTypeMapper) {
            viewTypeMapper.clear()
            viewTypeMapper[viewType] = layoutID to viewHolderClass
        }
    }

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
    private var mDiffCallback: BaseDiffer<T> = DefaultDiffer()

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val value = viewTypeMapper[viewType]
            ?: throw IllegalArgumentException("ViewType [$viewType] is unknown! Did you forget to register it?")

        val (layoutID, vhClass) = value

        val itemView = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        Log.d(tag, "itemView: $itemView")

        val constructor = vhClass.getConstructor(View::class.java)
        vhClass.declaredConstructors.forEach {
            Log.d(tag, "constructor: $it")
        }
        return constructor.newInstance(itemView) as VH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item: T = mDataSource[position]
        holder.bindData(item)
    }

    /**
     * 默认的数据绑定实现（局部刷新）。
     *
     * 使用本工具内置的Payload机制刷新表项，如果子类希望使用自定义Payload机制，可以覆写该方法。
     *
     * 本工具使用多个数值组合而成的标志位作为Payload。
     *
     * @param[holder] 待绑定的ViewHolder实例。
     */
    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            // 默认选择最新的Payload，忽略旧的Payload。
            val payload = payloads.last()
            if (payload !is Int) {
                Log.w(tag, "Payload type is not a number with flags, ignored!")
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

    fun getDataSource(): List<T> = mDataSource

    /**
     * 获取数据源的一份副本。
     *
     * 因为数据源的更改会影响表项，有些操作不能直接在数据源上进行，可以在其副本上进行操作。
     *
     * @return 内含ItemVO的列表，是当前数据源的副本。
     */
    // fun getCopyOfDataSource(): List<T> {
    //     // return mDataSource.map { it. }
    // }

    /**
     * 更新指定的表项。
     *
     * 更新指定的表项，不影响列表中的其它表项。
     *
     * @param[position] 待更新的位置。
     * @param[data]     新的表项。
     */
    fun updateItem(position: Int, data: T) {
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
    fun addItem(position: Int, data: T) {
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
     * 更新所有表项。
     *
     * @param[newDatas] 新的数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    fun reloadItems(newDatas: List<T>) {
        mUpdateTaskSequence++

        mDataSource.clear()
        mDataSource.addAll(newDatas)
        notifyDataSetChanged()
    }

    /**
     * 使用DiffUtil异步更新表项。
     *
     * @param[newData] 新的列表。
     * @param[detectMoves] 表项移动检测功能开关，默认为开启。DiffUtil的算法检测表项是否被移动需要额外消耗性能，如果新旧表项排序规则一致，只是增删表
     * 项，可以关闭此功能以提升性能。
     * @param[actionAfterUpdate] 更新完毕后需要执行的动作。
     */
    @JvmOverloads
    fun submit(
        newData: List<T>,
        detectMoves: Boolean = true,
        actionAfterUpdate: (() -> Unit)? = null
    ) {
        val taskSequence = ++mUpdateTaskSequence

        if (newData == mDataSource) {
            Log.i(tag, "Submit. New list is same as old, nothing to do.")
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
            }, detectMoves)

            if (taskSequence == mUpdateTaskSequence) {
                uiScope.launch {
                    mDataSource.clear()
                    mDataSource.addAll(newData)
                    diffResult.dispatchUpdatesTo(this@BaseAdapter)
                    // 更新完毕后执行其他任务
                    mRecyclerView?.post {
                        actionAfterUpdate?.invoke()
                    }
                }
            }
        }
    }

    fun setDiffCallback(callback: BaseDiffer<T>) {
        mDiffCallback = callback
    }

    fun resetDiffCallback() {
        mDiffCallback = DefaultDiffer()
    }
}
