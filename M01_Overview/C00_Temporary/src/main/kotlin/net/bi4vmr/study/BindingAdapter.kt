package net.bi4vmr.study

/**
 * RecyclerView适配器的封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BindingAdapter<T : ListItem> : BaseAdapter<T,BindingViewHolder<*,T>>()
