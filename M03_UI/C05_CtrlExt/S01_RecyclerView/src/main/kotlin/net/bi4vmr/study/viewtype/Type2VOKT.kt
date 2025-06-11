package net.bi4vmr.study.viewtype

/**
 * 列表项的实体类（类型II）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
data class Type2VOKT(
    val info: String
) : ListItemKT {

    override fun getItemType(): Int {
        return 2
    }
}
