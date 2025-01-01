package net.bi4vmr.study.binding

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
class TestMultiTypeAdapter
@JvmOverloads constructor(

    /**
     * 初始数据源。
     */
    dataSource: MutableList<ListItem> = mutableListOf()
) : BindingAdapter<ListItem>(dataSource) {

    init {
        // 开启调试模式，方便查看Adapter的工作过程。
        debugMode = true

        // 配置ViewType映射关系
        addBindingMapper(ViewType.TITLE.typeCode, ListItemTitleBinding::class.java, TitleVH::class.java)
        addBindingMapper(ViewType.CONTENT.typeCode, ListItemContentBinding::class.java, ContentVH::class.java)
    }

    /**
     * 标题的ViewHolder。
     */
    private inner class TitleVH(binding: ListItemTitleBinding) :
        BindingViewHolder<ListItemTitleBinding, TitleVO>(binding) {

        override fun bindData(item: TitleVO) {
            binding.tvTitle.text = item.title
        }
    }

    /**
     * 内容的ViewHolder。
     */
    private inner class ContentVH(binding: ListItemContentBinding) :
        BindingViewHolder<ListItemContentBinding, ContentVO>(binding) {

        override fun bindData(item: ContentVO) {
            with(binding) {
                tvTitle.text = item.title
                tvInfo.text = item.info
                ivIcon.setImageResource(item.iconRes)
            }
        }
    }
}
