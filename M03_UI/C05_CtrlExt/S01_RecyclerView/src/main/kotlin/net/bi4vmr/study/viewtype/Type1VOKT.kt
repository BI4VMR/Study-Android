package net.bi4vmr.study.viewtype

/**
 * 列表项的实体类（类型I）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
data class Type1VOKT(
    val title: String,
    val info: String = "-"
) : ListItemKT {

    override fun getItemType(): Int {
        return 1
    }
}
