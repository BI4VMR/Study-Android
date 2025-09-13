package net.bi4vmr.study.base

import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * ViewObject：标题。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TitleVO(
    val title: String
) : ListItem {

    override fun getViewType(): Int {
        return 1
    }
}
