package net.bi4vmr.study.simple

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import net.bi4vmr.study.R
import net.bi4vmr.tool.android.ui.baservadapter.base.BaseViewHolder
import net.bi4vmr.tool.android.ui.baservadapter.base.SimpleAdapter

/**
 * 测试Adapter（单一表项类型）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestSingleTypeAdapter(

    /**
     * 初始数据源。
     */
    dataSource: MutableList<ContentVO> = mutableListOf()
) : SimpleAdapter<ContentVO>(R.layout.list_item_content, ContentVH::class.java, dataSource) {

    init {
        // 开启调试模式，方便查看Adapter的工作过程。
        debugMode = true
    }

    private inner class ContentVH(view: View) : BaseViewHolder<ContentVO>(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvInfo: TextView = view.findViewById(R.id.tv_info)
        private val ivIcon: ImageView = view.findViewById(R.id.iv_icon)

        override fun bindData(item: ContentVO) {
            tvTitle.text = item.title
            tvInfo.text = item.info
            ivIcon.setImageResource(item.iconRes)
        }
    }
}
