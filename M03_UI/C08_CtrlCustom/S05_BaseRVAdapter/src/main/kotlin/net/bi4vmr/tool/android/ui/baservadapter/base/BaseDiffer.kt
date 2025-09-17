package net.bi4vmr.tool.android.ui.baservadapter.base

/**
 * DiffUtil比较回调基本封装。
 *
 * Payload为多个二进制位经过或运算组成的标志位，每个二进制位对应一组UI元素，例如： `0x1` 表示标题、 `0x2` 表示描述，如果标题与描述都需要
 * 更新，则应当在 [getChangePayload] 方法中返回 `0x3` ；并在对应ViewHolder的 [BaseViewHolder.bindData] 方法中实现View更新逻辑。
 *
 * 本类提供了一些常用的标志位定义，如果无法满足实际需要，可以基于 [CUSTOM] 定义新的标志位。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
abstract class BaseDiffer<T : ListItem> {

    companion object {

        const val TITLE: Int = 0x1
        const val INFO: Int = TITLE shl 1
        const val CUSTOM: Int = INFO

        fun addFlag(flags: Int, flag: Int): Int {
            return flags or flag
        }
    }

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun getChangePayload(oldItem: T, newItem: T): Int
}
