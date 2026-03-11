package net.bi4vmr.study.binding

import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * ViewObject：标题。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
data class TitleVO(
    var title: String
) : ListItem {

    override fun getViewType(): Int {
        return ViewType.TITLE.typeCode
    }

    override fun copy(): ListItem {
        return TitleVO(title)
    }
}
