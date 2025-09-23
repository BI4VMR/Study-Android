package net.bi4vmr.study.simple

import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * ViewObject：内容。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class ContentVO(
    val title: String,
    val info: String,
    val iconRes: Int
) : ListItem
