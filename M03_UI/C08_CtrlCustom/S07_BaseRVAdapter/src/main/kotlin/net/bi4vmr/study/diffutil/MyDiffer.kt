package net.bi4vmr.study.diffutil

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

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        // 比较内容是否相同
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
            payload = addFlag(payload, FLAG_NAME)
        }

        return payload
    }

    private fun getContentPayload(oldItem: ContentVO, newItem: ContentVO): Int {
        var payload = 0

        // 如果标题不同，则通过标志位指明需要刷新标题。
        if (oldItem.title != newItem.title) {
            // 内置标志位含义与实际一致，可以直接使用。
            payload = addFlag(payload, FLAG_TITLE)
        }
        if (oldItem.info != newItem.info) {
            // 内置标志位不能满足要求，使用自定义标志位。
            payload = addFlag(payload, FLAG_INFO)
        }
        if (oldItem.iconRes != newItem.iconRes) {
            // 内置标志位含义与实际一致，可以直接使用。
            payload = addFlag(payload, FLAG_ICON)
        }

        return payload
    }
}
