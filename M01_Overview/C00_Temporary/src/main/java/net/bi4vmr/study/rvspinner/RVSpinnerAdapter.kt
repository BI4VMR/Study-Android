package net.bi4vmr.bookkeeper.ui.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * [RVSpinner] 数据适配器。
 *
 * 控件使用者需要实现此接口中的方法，以便 [RVSpinner] 获取必要的信息，绘制选中项目View与弹出式列表。
 *
 * 泛型 `VH` 表示关联的 [RecyclerView.Adapter] 的ViewHolder类型。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
abstract class RVSpinnerAdapter<VH : RecyclerView.ViewHolder> {

    private var listener: RVSpinnerAdapterListener? = null

    /**
     * 获取用于弹出式窗口的 [RecyclerView] 适配器。
     *
     * @return [RecyclerView.Adapter] 实例。
     */
    abstract fun onLoadRecyclerViewAdapter(): RecyclerView.Adapter<VH>

    /**
     * 获取当前选中项在 [RecyclerView] 中的索引。
     *
     * @return 索引序号。
     */
    abstract fun onLoadSelectedItemPosition(): Int

    /**
     * 获取当前选中项的 [View] 实例。
     *
     * 绘制常态显示的表项，默认实现为创建 [RecyclerView] 对应表项的ViewHolder并绑定数据，然后返回 [View] 。
     *
     * 由于一个View只能显示在一处，因此我们不能直接返回RecyclerView中的表项View，而是应当新建表项View并绑定数据再返回给 [RVSpinner] 。
     *
     * @param[container] [RVSpinner] 控件。
     * @param[position] 当前选中项在 [RecyclerView] 中的索引。
     * @return [View] 实例。
     */
    open fun onLoadSelectedItemView(container: RVSpinner, position: Int): View {
        onLoadRecyclerViewAdapter().let {
            val viewType = it.getItemViewType(position)
            val vh = it.onCreateViewHolder(container, viewType)
            it.onBindViewHolder(vh, position)
            return vh.itemView
        }
    }

    /**
     * 通知 [RVSpinner] 当前选中项已改变。
     *
     * @param[position] 选中项在 [RecyclerView] 中的索引序号。
     */
    fun notifyItemSelected(position: Int) {
        listener?.onItemSelected(position)
    }

    /**
     * [RVSpinner] 向 [RVSpinnerAdapter] 注册事件监听器。
     *
     * 当 [RecyclerView] 通过点击事件等触发表项选中事件时，通过该监听器中的方法回调事件给 [RVSpinnerAdapter] 。
     *
     * @param[l] 监听器实现。
     */
    internal fun setAdapterListener(l: RVSpinnerAdapterListener) {
        listener = l
    }
}
