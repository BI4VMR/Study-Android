package net.bi4vmr.study.binding_simple

import net.bi4vmr.study.databinding.ListItemContentBinding
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem
import net.bi4vmr.tool.android.ui.baservadapter.binding.BindingViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.binding.SimpleBindingAdapter

/**
 * 测试Adapter。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestSingleTypeAdapter
@JvmOverloads constructor(

    /**
     * 初始数据源。
     */
    dataSource: MutableList<ListItem> = mutableListOf()
) : SimpleBindingAdapter<ListItem>(
    viewBindingClass = ListItemContentBinding::class.java,
    viewHolderClass = ContentVH::class.java,
    dataSource
) {

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
