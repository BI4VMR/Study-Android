package net.bi4vmr.study

import android.view.LayoutInflater
import android.view.ViewGroup
import net.bi4vmr.study.databinding.ListItemType1Binding

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class MyAdapter : BindingAdapter<VO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*, VO> {
        val binding = ListItemType1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }
}

data class VO(
    val title: String,
    val iconRes: Int
) : ListItem

class VH(binding: ListItemType1Binding) : BindingViewHolder<ListItemType1Binding, VO>(binding) {

    override fun bindData(i: VO) {
        binding.tvTitle.text = i.title
        binding.ivIcon.setImageResource( i.iconRes)
    }
}
