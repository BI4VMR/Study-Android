package net.bi4vmr.tool.android.ui.baservadapter.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView ViewHolder的通用封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseViewHolder<T : ListItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 将表项的所有View与ViewObject绑定
    abstract fun bindData(item: T)

    // 根据Payload指明的内容局部刷新View
    open fun bindData(item: T, payloads: Int) {
        // 默认为空实现，如果不需要使用局部刷新功能，子类不必覆写本方法。
    }
}
