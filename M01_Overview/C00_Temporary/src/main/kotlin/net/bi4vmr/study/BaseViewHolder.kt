package net.bi4vmr.study

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * RecyclerView ViewHolder的封装。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
abstract class BaseViewHolder<T : ListItem>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindData(i: T)
}
