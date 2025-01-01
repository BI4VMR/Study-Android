package net.bi4vmr.study.binding

import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * ViewObject：内容。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
data class ContentVO(
    var title: String,
    var info: String,
    var iconRes: Int
) : ListItem {

    override fun getViewType(): Int {
        return ViewType.CONTENT.typeCode
    }

    override fun copy(): ListItem {
        return ContentVO(
            title,
            info,
            iconRes
        )
    }
}
