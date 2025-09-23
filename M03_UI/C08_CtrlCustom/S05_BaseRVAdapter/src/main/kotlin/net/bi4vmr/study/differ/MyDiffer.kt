package net.bi4vmr.study.differ

import android.annotation.SuppressLint
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseDiffer
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 自定义DiffUtil比较回调实现。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyDiffer : BaseDiffer<ListItem>() {

    companion object {

        // 自定义标志位从FLAG_CUSTOM左移一位开始，避免与内置标志位冲突。
        const val FLAG_INFO = FLAG_CUSTOM shl 1
    }

    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        // 首先比较ViewType，如果ViewType不同，则两个表项一定是不同的。
        val viewTypeSame = oldItem.getViewType() == newItem.getViewType()
        if (!viewTypeSame) return false

        // 如果ViewType相同，则调用两个表项的 `equals()` 方法进行比较。
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Int {
        return if (oldItem is TitleVO && newItem is TitleVO) {
            getTitlePayload(oldItem, newItem)
        } else if (oldItem is ContentVO && newItem is ContentVO) {
            getContentPayload(oldItem, newItem)
        } else {
            0
        }
    }

    private fun getTitlePayload(oldItem: TitleVO, newItem: TitleVO): Int {
        var payload = 0

        // 如果标题不同，则通过标志位指明需要刷新标题。
        if (oldItem.title != newItem.title) {
            // 内置标志位含义与实际一致，可以直接使用。
            payload = FLAG_TITLE
        }

        return payload
    }

    private fun getContentPayload(oldItem: ContentVO, newItem: ContentVO): Int {
        var payload = 0

        // 如果标题不同，则通过标志位指明需要刷新标题。
        if (oldItem.title != newItem.title) {
            // 内置标志位含义与实际一致，可以直接使用。
            payload = FLAG_TITLE
        }
        if (oldItem.info != newItem.info) {
            // 内置标志位不能满足要求，使用自定义标志位。
            payload = payload or FLAG_INFO
        }
        if (oldItem.iconRes != newItem.iconRes) {
            // 内置标志位含义与实际一致，可以直接使用。
            payload = payload or FLAG_ICON
        }

        return payload
    }
}
