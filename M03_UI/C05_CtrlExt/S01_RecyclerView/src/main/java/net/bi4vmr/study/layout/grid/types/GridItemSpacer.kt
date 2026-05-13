package net.bi4vmr.study.layout.grid.types

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class GridItemSpacer(
    private val spaceH: Int = 0,
    private val spaceV: Int = 0,
    private val noTypes: MutableList<Class<*>> = mutableListOf(),
) : RecyclerView.ItemDecoration() {

    // 网格两边的间距
    private val edgeH: Int = 100

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        if (parent.getChildViewHolder(view).javaClass in noTypes){
            Log.w("GridItemSpacer", "ignore this type ${parent.getChildViewHolder(view).javaClass.simpleName}")
            super.getItemOffsets(outRect, view, parent, state)
            return
        }

        val lm: RecyclerView.LayoutManager? = parent.layoutManager
        lm?.let {
            if (it is GridLayoutManager) {
                // TODO 具有多种type时，应当根据type控制，而不是position
                val itemIndex: Int = parent.getChildLayoutPosition(view)
                val columnCount: Int = it.spanCount

                // 获取第几列
                val column = itemIndex % columnCount
                // 第几行
                val row: Int = itemIndex / columnCount
                // if (row != 0) { // 设置top
                outRect.top = spaceV
                // }

                // p为每个Item都需要减去的间距
                val p = (2 * edgeH + (columnCount - 1) * spaceH) * 1.0F / columnCount
                val left = edgeH + column * (spaceH - p)
                val right = p - left

                outRect.left = left.roundToInt()
                outRect.right = right.roundToInt()
            }
        }
    }
}