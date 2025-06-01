package net.bi4vmr.study

import androidx.viewbinding.ViewBinding

/**
 * RecyclerView ViewHolder的封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BindingViewHolder<VB : ViewBinding, T : ListItem>(
    protected val binding: VB
) : BaseViewHolder<T>(binding.root)
