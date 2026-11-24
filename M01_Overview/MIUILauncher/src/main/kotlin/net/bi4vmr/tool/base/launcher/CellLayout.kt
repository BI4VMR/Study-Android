package net.bi4vmr.tool.base.launcher

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * 网格容器（对应 MIUI / Launcher3 的 CellLayout，已简化）。
 *
 * - 每个子 View 通过 [LayoutParams] 记录格子坐标，占用 1x1 格子。
 * - 拖拽时的"挤位"逻辑：以进入本容器时的布局为快照，悬停在已占用的格子上时，
 *   找到离目标最近的空格，把目标格与空格之间的图标依次平移一格，从而腾出目标格。
 */
@SuppressLint("ViewConstructor")
class CellLayout(
    context: Context,
    val countX: Int,
    val countY: Int,
) : ViewGroup(context) {

    companion object {
        private const val REORDER_ANIM_DURATION: Long = 200L
    }

    class LayoutParams(var cellX: Int, var cellY: Int) :
        ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

    var cellWidth: Int = 0
        private set
    var cellHeight: Int = 0
        private set

    /** 拖拽进入本容器时各图标的原始位置。 */
    private var snapshot: Map<View, Pair<Int, Int>>? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        cellWidth = (width - paddingLeft - paddingRight) / countX
        cellHeight = (height - paddingTop - paddingBottom) / countY
        val childW = MeasureSpec.makeMeasureSpec(cellWidth, MeasureSpec.EXACTLY)
        val childH = MeasureSpec.makeMeasureSpec(cellHeight, MeasureSpec.EXACTLY)
        for (i in 0 until childCount) {
            getChildAt(i).measure(childW, childH)
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            val left = cellLeft(lp.cellX)
            val top = cellTop(lp.cellY)
            child.layout(left, top, left + cellWidth, top + cellHeight)
        }
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean = p is LayoutParams

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams = LayoutParams(0, 0)

    override fun shouldDelayChildPressedState(): Boolean = false

    fun cellLeft(cellX: Int): Int = paddingLeft + cellX * cellWidth

    fun cellTop(cellY: Int): Int = paddingTop + cellY * cellHeight

    /** 本容器坐标系中的点 -> 格子坐标（越界时取边缘格）。 */
    fun pointToCell(x: Float, y: Float): IntArray {
        val cx = ((x - paddingLeft) / cellWidth.coerceAtLeast(1)).toInt().coerceIn(0, countX - 1)
        val cy = ((y - paddingTop) / cellHeight.coerceAtLeast(1)).toInt().coerceIn(0, countY - 1)
        return intArrayOf(cx, cy)
    }

    fun childAt(cellX: Int, cellY: Int): View? {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            if (lp.cellX == cellX && lp.cellY == cellY) return child
        }
        return null
    }

    fun isOccupied(cellX: Int, cellY: Int): Boolean = childAt(cellX, cellY) != null

    fun hasVacant(): Boolean = childCount < countX * countY

    /** 按线性顺序距离查找最近的空格，优先向后查找。 */
    fun findNearestVacant(cellX: Int, cellY: Int): IntArray? {
        val total = countX * countY
        val start = cellY * countX + cellX
        for (d in 0 until total) {
            for (idx in intArrayOf(start + d, start - d)) {
                if (idx in 0 until total && !isOccupied(idx % countX, idx / countX)) {
                    return intArrayOf(idx % countX, idx / countX)
                }
            }
        }
        return null
    }

    /* ----- 拖拽挤位 ----- */

    fun beginReorder() {
        snapshot = (0 until childCount).associate { i ->
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child to (lp.cellX to lp.cellY)
        }
    }

    /**
     * 基于快照计算"在 (cellX, cellY) 腾出空位"后的布局，并以动画移动图标。
     *
     * @return 是否成功腾出空位（容器已满时返回 false，布局恢复为快照）。
     */
    fun reorderFor(cellX: Int, cellY: Int): Boolean {
        val snap = snapshot ?: return !isOccupied(cellX, cellY)
        val total = countX * countY
        val grid = arrayOfNulls<View>(total)
        snap.forEach { (view, pos) -> grid[pos.second * countX + pos.first] = view }

        val target = cellY * countX + cellX
        val result = grid.copyOf()
        var success = true
        if (grid[target] != null) {
            var empty = -1
            for (d in 1 until total) {
                if (target + d < total && grid[target + d] == null) {
                    empty = target + d; break
                }
                if (target - d >= 0 && grid[target - d] == null) {
                    empty = target - d; break
                }
            }
            if (empty == -1) {
                success = false
            } else if (empty > target) {
                for (i in empty downTo target + 1) result[i] = grid[i - 1]
                result[target] = null
            } else {
                for (i in empty until target) result[i] = grid[i + 1]
                result[target] = null
            }
        }
        result.forEachIndexed { i, view -> view?.let { animateChildTo(it, i % countX, i / countX) } }
        return success
    }

    /** 拖拽离开本容器：所有图标回到快照位置。 */
    fun revertReorder() {
        snapshot?.forEach { (view, pos) -> animateChildTo(view, pos.first, pos.second) }
        snapshot = null
    }

    /** 放下图标：保留当前位置。 */
    fun commitReorder() {
        snapshot = null
    }

    private fun animateChildTo(child: View, cellX: Int, cellY: Int) {
        val lp = child.layoutParams as LayoutParams
        if (lp.cellX == cellX && lp.cellY == cellY) return
        val oldX = cellLeft(lp.cellX) + child.translationX
        val oldY = cellTop(lp.cellY) + child.translationY
        lp.cellX = cellX
        lp.cellY = cellY
        child.animate().cancel()
        child.translationX = oldX - cellLeft(cellX)
        child.translationY = oldY - cellTop(cellY)
        child.animate()
            .translationX(0f)
            .translationY(0f)
            .setDuration(REORDER_ANIM_DURATION)
            .start()
        requestLayout()
    }
}
