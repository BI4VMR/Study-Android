package net.bi4vmr.study.diffutil

import androidx.recyclerview.widget.DiffUtil

/**
 * 自定义DiffUtil。
 * <p>
 * 用于设置表项的比较规则。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyDiffCallbackKT(
    private val oldList: List<ItemVOKT>,
    private val newList: List<ItemVOKT>
) : DiffUtil.Callback() {

    /**
     * 获取旧数据源的大小。
     *
     * @return 旧数据源的大小。
     */
    override fun getOldListSize(): Int {
        return oldList.size
    }

    /**
     * 获取新数据源的大小。
     *
     * @return 新数据源的大小。
     */
    override fun getNewListSize(): Int {
        return newList.size
    }

    /**
     * 判断参数所指定的两个位置对应表项类型是否相同。
     *
     * @param[oldItemPosition] 旧数据源中的表项索引。
     * @param[newItemPosition] 新数据源中的表项索引。
     * @return 值为 `false` 时，表示两个表项不同；值为 `true` 时表示两个表项是相同的，但内部数据可能有变化，随后会调用
     * `areContentsTheSame()` 方法判断表项中的数据是否需要更新。
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // 此处只有一种表项类型，视图都可复用，直接返回 `true` 即可。
        return true
    }

    /**
     * 判断参数所指定的两个位置对应表项内容是否相同。
     * <p>
     * 仅当 `areItemsTheSame()` 返回 `true` 时才会调用本方法。
     *
     * @param[oldItemPosition] 旧数据源中的表项索引。
     * @param[newItemPosition] 新数据源中的表项索引。
     * @return 值为 `true` 时表示两个表项内容相同，值为 `false` 表示两个表项内容不同。
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // 比较二者的内容是否相同
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    /**
     * 计算发生变化的属性，使得适配器执行局部刷新。
     * <p>
     * 自定义Callback不覆盖此方法时，系统默认返回 `null` ，此时将刷新整个列表项。
     *
     * @param[oldItemPosition] 旧数据源中的表项索引。
     * @param[newItemPosition] 新数据源中的表项索引。
     * @return 任意类型的对象，会传递给适配器 `onBindViewHolder()` 方法的 `payloads` 参数。
     */
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        var flags = 0
        if (oldItem.title != newItem.title) {
            flags = flags or UpdateFlagsKT.FLAG_TITLE
        }
        if (oldItem.info != newItem.info) {
            flags = flags or UpdateFlagsKT.FLAG_INFO
        }

        return flags
    }
}
