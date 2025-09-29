package net.bi4vmr.study.diffutil

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import net.bi4vmr.study.R
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseAdapter
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseDiffer
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.base.ListItem

/**
 * 测试Adapter。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestMultiTypeAdapter(

    /**
     * 初始数据源。
     */
    dataSource: MutableList<ListItem> = mutableListOf()
) : BaseAdapter<ListItem>(dataSource) {

    init {
        // 开启调试模式，方便查看Adapter的工作过程。
        debugMode = true

        // 配置ViewType映射关系
        addViewTypeMapper(ViewType.TITLE.typeCode, R.layout.list_item_title, TitleVH::class.java)
        addViewTypeMapper(ViewType.CONTENT.typeCode, R.layout.list_item_content, ContentVH::class.java)

        // 配置自定义Diff比较规则
        setDiffCallback(MyDiffer())
    }

    /**
     * 标题的ViewHolder。
     */
    private inner class TitleVH(view: View) : BaseViewHolder<TitleVO>(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_title)

        // 全量数据绑定
        override fun bindData(item: TitleVO) {
            tvTitle.text = item.title
        }

        // 局部数据绑定
        override fun bindData(item: TitleVO, payload: Int) {
            // 根据各标志位是否置位确定需要更新的控件
            if (hasFlag(payload, BaseDiffer.FLAG_TITLE)) {
                tvTitle.text = item.title
            }
        }
    }

    /**
     * 内容的ViewHolder。
     */
    private inner class ContentVH(view: View) : BaseViewHolder<ContentVO>(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvInfo: TextView = view.findViewById(R.id.tv_info)
        private val ivIcon: ImageView = view.findViewById(R.id.iv_icon)

        // 全量数据绑定
        override fun bindData(item: ContentVO) {
            tvTitle.text = item.title
            tvInfo.text = item.info
            ivIcon.setImageResource(item.iconRes)
        }

        // 局部数据绑定
        override fun bindData(item: ContentVO, payload: Int) {
            // 根据各标志位是否置位确定需要更新的控件
            if (hasFlag(payload, BaseDiffer.FLAG_TITLE)) {
                tvTitle.text = item.title
            }
            if (hasFlag(payload, MyDiffer.FLAG_INFO)) {
                tvInfo.text = item.info
            }
            if (hasFlag(payload, BaseDiffer.FLAG_ICON)) {
                ivIcon.setImageResource(item.iconRes)
            }
        }
    }
}
