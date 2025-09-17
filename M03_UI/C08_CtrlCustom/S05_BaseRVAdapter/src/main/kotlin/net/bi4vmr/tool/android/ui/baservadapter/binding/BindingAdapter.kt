package net.bi4vmr.tool.android.ui.baservadapter.binding

import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseAdapter
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem
import java.util.concurrent.ConcurrentHashMap

/**
 * RecyclerView适配器的通用封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BindingAdapter<T : ListItem>
@JvmOverloads constructor(

    /**
     * 内部数据源。
     */
    dataSource: MutableList<T> = mutableListOf(),

    /**
     * 后台任务的协程环境。
     *
     * 用于执行差异对比、异步更新等任务，默认值为 `Default` 调度器构建的作用域。
     */
    bgScope: CoroutineScope = CoroutineScope(Dispatchers.Default),

    /**
     * 前台任务的协程环境。
     *
     * 用于更新界面，默认值为 `Main` 调度器构建的作用域。
     *
     * 此参数仅供单元测试场景使用，其他场景下调用者无需自行传入协程环境。
     */
    uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) : BaseAdapter<T, BindingViewHolder<*, T>>(dataSource, bgScope, uiScope) {

    /**
     * ViewType映射表。
     *
     * 配置ViewType、ViewBinding与ViewHolder的映射关系，以便 [onCreateViewHolder] 方法自动创建ViewHolder实例。
     *
     * 如果调用者不希望使用本工具内置的映射方案，也可以自行重写 [onCreateViewHolder] 方法。
     */
    private val bindingMapper: MutableMap<Int, Pair<Class<ViewBinding>, Class<BaseViewHolder<*>>>> = ConcurrentHashMap()
}
