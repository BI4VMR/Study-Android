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
 * 泛型 [I] 表示与ViewHolder绑定的表项数据类型，必须是 [ListItem] 的子类。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseAdapter<I : ListItem>
@JvmOverloads constructor(

    /**
     * 内部数据源。
     */
    private val mDataSource: MutableList<I> = CopyOnWriteArrayList(),

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
) : RecyclerView.Adapter<BaseViewHolder<I>>() {

    /**
     * 日志Tag。
     */
    protected open val tag: String = javaClass.simpleName

    /**
     * 调试模式开关。
     *
     * 用于控制是否输出详细日志。
     */
    private var debugMode: Boolean = false

    /**
     * ViewType映射表。
     *
     * 配置ViewType、Layout资源ID与ViewHolder的映射关系，以便 [onCreateViewHolder] 方法自动创建ViewHolder实例。
     *
     * 如果调用者不希望使用本工具内置的映射方案，也可以自行重写 [onCreateViewHolder] 方法。
     */
    private val viewTypeMappers: MutableMap<Int, Pair<Int, Class<out BaseViewHolder<*>>>> = ConcurrentHashMap()

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
    private var mDiffCallback: BaseDiffer<I> = DefaultDiffer()

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
    }

    @CallSuper
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = null
    }

    /**
     * 默认的ViewHolder创建实现。
     *
     * @param[parent] 父容器。
     * @param[viewType] 表项类型代码。
     * @return ViewHolder实例。
     */
    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<I> {
        val (layoutID, vhClass) = viewTypeMappers[viewType]
            ?: throw IllegalArgumentException("ViewType [$viewType] is unknown! Did you forget to register it?")
        // 通过布局文件创建View实例
        val itemView = LayoutInflater.from(parent.context).inflate(layoutID, parent, false)
        // 通过反射调用ViewHolder的构造方法创建实例
        val constructor = vhClass.getConstructor(View::class.java)
        return constructor.newInstance(itemView) as BaseViewHolder<I>
    }

    /**
     * 默认的数据绑定实现。
     *
     * @param[holder] 待绑定的ViewHolder实例。
     * @param[position] 表项索引序号。
     */
    override fun onBindViewHolder(holder: BaseViewHolder<I>, position: Int) {
        val item: I = mDataSource[position]
        holder.bindData(item)
    }

    /**
     * 默认的数据绑定实现（局部刷新）。
     *
     * 使用本工具内置的Payload机制刷新表项，详见 [BaseDiffer] 类的注释。
     *
     * 如果子类希望使用自定义Payload机制，可以重写该方法。
     *
     * @param[holder] 待绑定的ViewHolder实例。
     * @param[position] 表项索引序号。
     * @param[payloads] Payloads。
     * @see BaseDiffer
     */
    override fun onBindViewHolder(holder: BaseViewHolder<I>, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            // 默认选择最新的Payload，忽略旧的Payload。
            val payload = payloads.last()
            if (payload !is Int) {
                Log.w(tag, "Payload type is not a number with flags, ignored!")
                return
            }

            val item: I = mDataSource[position]
            holder.bindData(item, payload)
        }
    }

    override fun getItemCount(): Int {
        return mDataSource.size
    }

    override fun getItemViewType(position: Int): Int {
        val item: I = mDataSource[position]
        return item.getViewType()
    }

    /**
     * 设置是否启用调试模式。
     *
     * @param[enabled] `true`表示启用调试模式，`false`表示关闭调试模式。
     */
    fun setDebugMode(enabled: Boolean) {
        debugMode = enabled
    }

    /**
     * 注册ViewType、布局和ViewHolder的映射关系。
     *
     * @param[viewType] ViewType。
     * @param[layoutID] 布局XML的ID。
     * @param[viewHolderClass] ViewHolder的Class。
     */
    fun addViewTypeMapper(viewType: Int, @LayoutRes layoutID: Int, viewHolderClass: Class<out BaseViewHolder<*>>) {
        viewTypeMappers[viewType] = layoutID to viewHolderClass
    }

    /**
     * 注销ViewType、布局和ViewHolder的映射关系。
     *
     * @param[viewType] ViewType。
     */
    fun removeViewTypeMapper(viewType: Int) {
        viewTypeMappers.remove(viewType)
    }

    /**
     * 获取所有ViewType、布局和ViewHolder的映射关系。
     *
     * @return 映射关系集合。
     */
    fun getViewTypeMappers(): Map<Int, Pair<Int, Class<out BaseViewHolder<*>>>> {
        return viewTypeMappers.toMap()
    }

    fun getDataSource(): List<I> = mDataSource

    /**
     * 获取数据源的副本。
     *
     * 有时我们需要对数据源进行一些修改，例如过滤与统计等操作，但我们又不希望影响到列表显示，此时可以使用本方法获取列表。
     *
     * 该方法依赖列表项的 [ListItem.copy] 方法实现深拷贝，如果列表项并未正确实现此方法，修改数据源仍会影响列表显示。
     *
     * @return 当前数据源的副本。
     */
    @Suppress("UNCHECKED_CAST")
    fun getCopyOfDataSource(): List<I> {
        return mDataSource.map { it.copy() as I }
    }

    /**
     * 更新指定的表项。
     *
     * 更新指定的表项，不影响列表中的其它表项。
     *
     * @param[position] 待更新的位置。
     * @param[data]     新的表项。
     */
    fun updateItem(position: Int, data: I) {
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
    fun addItem(position: Int, data: I) {
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
    fun reloadItems(newDatas: List<I>) {
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
     * @param[actionAfterUpdate] 更新成功后需要执行的动作。
     */
    @JvmOverloads
    fun submit(
        newData: List<I>,
        detectMoves: Boolean = true,
        actionAfterUpdate: (() -> Unit)? = null
    ) {
        val taskSequence = ++mUpdateTaskSequence
        val oldData = mDataSource

        if (debugMode) {
            Log.d(tag, "Submit. Async task Start. TaskID:[$taskSequence]")
            if (newData.isEmpty()) {
                Log.d(tag, "Submit. New data is empty.")
            } else {
                Log.v(tag, "Submit. New data size is [${newData.size}], detail info start:")
                newData.forEachIndexed { i, item ->
                    Log.v(tag, "[$i] -> $item")
                }
                Log.v(tag, "Submit. New data detail info end.")
            }
            if (oldData.isEmpty()) {
                Log.d(tag, "Submit. Old data is empty.")
            } else {
                Log.v(tag, "Submit. Old data size is [${oldData.size}], detail info start:")
                newData.forEachIndexed { i, item ->
                    Log.v(tag, "[$i] -> $item")
                }
                Log.v(tag, "Submit. Old data detail info end.")
            }
        }

        if (newData == oldData) {
            Log.i(tag, "Submit. New list is same as old, nothing to do.")
            mRecyclerView?.post {
                actionAfterUpdate?.invoke()
            }
            return
        }

        // 快速处理某个列表为空的情况，无需执行差异对比。
        if (newData.isEmpty()) {
            val oldSize = oldData.size
            mDataSource.clear()
            notifyItemRangeRemoved(0, oldSize)

            mRecyclerView?.post {
                actionAfterUpdate?.invoke()
            }
            return
        }

        if (oldData.isEmpty()) {
            mDataSource.addAll(newData)
            notifyItemRangeInserted(0, newData.size)

            mRecyclerView?.post {
                actionAfterUpdate?.invoke()
            }
            return
        }

        // 开始计算差异
        bgScope.launch {
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

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
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

    fun setDiffCallback(callback: BaseDiffer<I>) {
        mDiffCallback = callback
    }

    fun resetDiffCallback() {
        mDiffCallback = DefaultDiffer()
    }
}
