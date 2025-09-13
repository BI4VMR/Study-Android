package net.bi4vmr.study.base

import android.view.LayoutInflater
import android.view.ViewGroup
import net.bi4vmr.study.databinding.ListItemContentBinding
import net.bi4vmr.study.databinding.ListItemTitleBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem
import net.bi4vmr.tool.android.ui.baservadapter.binding.BindingAdapter
import net.bi4vmr.tool.android.ui.baservadapter.binding.BindingViewHolder

/**
 * 测试Adapter。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestAdapter
@JvmOverloads constructor(

    /**
     * 初始数据源。
     */
    dataSource: List<ListItem> = listOf()
) : BindingAdapter<ListItem>(dataSource) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<*, ListItem> {
        val vh = when (viewType) {
            1 -> {
                val binding = ListItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TitleVH(binding) as BindingViewHolder<*, ListItem>
            }

            2 -> {
                val binding = ListItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ContentVH(binding) as BindingViewHolder<*, ListItem>
            }

            else -> throw IllegalArgumentException()
        }
        return vh
    }

    class TitleVH(binding: ListItemTitleBinding) : BindingViewHolder<ListItemTitleBinding, TitleVO>(binding) {

        override fun bindData(item: TitleVO) {
            with(binding) {
                tvTitle.text = item.title
            }
        }
    }

    class ContentVH(binding: ListItemContentBinding) : BindingViewHolder<ListItemContentBinding, ContentVO>(binding) {

        override fun bindData(item: ContentVO) {
            with(binding) {
                tvTitle.text = item.title
                tvInfo.text = item.info
                ivIcon.setImageResource(item.iconRes)
            }
        }
    }
}
