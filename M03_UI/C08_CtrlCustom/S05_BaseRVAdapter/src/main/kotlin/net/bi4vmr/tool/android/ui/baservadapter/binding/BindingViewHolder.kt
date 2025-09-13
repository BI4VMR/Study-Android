package net.bi4vmr.tool.android.ui.baservadapter.binding

import androidx.viewbinding.ViewBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * RecyclerView ViewHolder的通用封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BindingViewHolder<VB : ViewBinding, T : ListItem>(
    protected val binding: VB
) : BaseViewHolder<T>(binding.root)
