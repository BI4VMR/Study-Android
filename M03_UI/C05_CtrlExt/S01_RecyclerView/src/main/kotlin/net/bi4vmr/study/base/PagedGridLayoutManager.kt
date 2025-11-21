package net.bi4vmr.study.base

import android.graphics.PointF
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 * 一个分页式的 Grid LayoutManager：每一页固定 rows * columns 个格子（最后一页不足时仍占满页面空间）。
 * 支持水平或垂直方向滚动。核心目标：让最后一页可以完整对齐页首，无需补齐占位或额外的 SnapHelper 特殊逻辑。
 *
 * 设计要点：
 * - 每页尺寸等于 RecyclerView 可用主轴尺寸（去除 padding）。
 * - 子项在各页内均匀分布，宽高通过整除计算（不考虑 spanSize 动态变化；需要动态 span 的场景不适用）。
 * - 滚动偏移用 mScrollOffset（X 或 Y）记录；布局时用子项全局坐标减去偏移判断是否需要渲染。
 * - 提供 scrollToPage / smoothScrollToPage API 方便外部控制；普通拖动后可结合自定义 SnapHelper 或监听 idle 状态进行对齐。
 */
class PagedGridLayoutManager(
    private val rows: Int,
    private val columns: Int,
    private val orientation: Int = RecyclerView.HORIZONTAL,
    private var pagePaddingStartPx: Int = 0,
    private var pagePaddingEndPx: Int = 0
) : RecyclerView.LayoutManager(), RecyclerView.SmoothScroller.ScrollVectorProvider {
    // 每页内部主轴方向内边距（横向：左/右；纵向：上/下），包含在页面视口内，不扩展 scroll 范围


    init {
        require(rows > 0 && columns > 0) { "rows & columns must > 0" }
        require(orientation == RecyclerView.HORIZONTAL || orientation == RecyclerView.VERTICAL)
    }

    private var mScrollOffset: Int = 0 // 当前主轴滚动偏移（X 或 Y）

    val pageSize: Int get() = rows * columns
    val pageCount: Int get() = if (itemCount == 0) 0 else ceil(itemCount / pageSize.toFloat()).toInt()

    override fun canScrollHorizontally(): Boolean = orientation == RecyclerView.HORIZONTAL
    override fun canScrollVertically(): Boolean = orientation == RecyclerView.VERTICAL

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        detachAndScrapAttachedViews(recycler)
        if (itemCount == 0 || state.isPreLayout) {
            mScrollOffset = 0
            return
        }
        clampScrollOffset()
        fill(recycler)
    }

    private fun clampScrollOffset() {
        val maxOffset = max(0, pageCount * pageSizeDimension() - pageSizeDimension()) // (pages-1)*pageWidth
        mScrollOffset = mScrollOffset.coerceIn(0, maxOffset)
    }

    private fun pageSizeDimension(): Int =
        if (orientation == RecyclerView.HORIZONTAL) width - paddingLeft - paddingRight else height - paddingTop - paddingBottom

    private fun contentMainAxisSize(): Int {
        val full = pageSizeDimension()
        val used = (pagePaddingStartPx + pagePaddingEndPx).coerceAtMost(full)
        return full - used
    }

    private fun itemWidth(): Int = if (orientation == RecyclerView.HORIZONTAL) {
        // 横向主轴为宽度，扣除页内左右 padding
        val content = contentMainAxisSize()
        (content / columns).coerceAtLeast(0)
    } else {
        // 纵向主轴为高度，宽度不扣 padding
        (width - paddingLeft - paddingRight) / columns
    }

    private fun itemHeight(): Int = if (orientation == RecyclerView.HORIZONTAL) {
        // 横向主轴为宽度，高度不扣 padding
        (height - paddingTop - paddingBottom) / rows
    } else {
        // 纵向主轴为高度，扣除页内上下 padding
        val content = contentMainAxisSize()
        (content / rows).coerceAtLeast(0)
    }

    private fun fill(recycler: RecyclerView.Recycler) {
        if (itemCount == 0) return
        val pageDimension = pageSizeDimension()
        val firstVisiblePage = mScrollOffset / pageDimension
        val lastVisiblePage = (mScrollOffset + pageDimension - 1) / pageDimension

        val startIndex = firstVisiblePage * pageSize
        val endIndexExclusive = min((lastVisiblePage + 1) * pageSize, itemCount)

        val childWidth = itemWidth()
        val childHeight = itemHeight()

        for (i in startIndex until endIndexExclusive) {
            val pageIndex = i / pageSize
            val indexInPage = i % pageSize
            val row = indexInPage / columns
            val col = indexInPage % columns
            val pageBase = pageIndex * pageDimension // 页视口起点（不含页内 padding）
            val leftBase = if (orientation == RecyclerView.HORIZONTAL)
                pageBase + pagePaddingStartPx + col * childWidth
            else
                col * childWidth
            val topBase = if (orientation == RecyclerView.HORIZONTAL)
                row * childHeight
            else
                pageBase + pagePaddingStartPx + row * childHeight

            val left = leftBase - if (orientation == RecyclerView.HORIZONTAL) mScrollOffset else 0
            val top = topBase - if (orientation == RecyclerView.VERTICAL) mScrollOffset else 0
            val right = left + childWidth
            val bottom = top + childHeight

            val view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithExactSize(view, childWidth, childHeight)
            layoutDecorated(view, left, top, right, bottom)
        }

        // 回收不可见 view
        for (j in childCount - 1 downTo 0) {
            val child = getChildAt(j) ?: continue
            if (!isChildVisible(child)) detachAndScrapView(child, recycler)
        }
    }

    private fun isChildVisible(child: View): Boolean {
        val pageDimension = pageSizeDimension()
        return if (orientation == RecyclerView.HORIZONTAL) {
            val left = getDecoratedLeft(child)
            val right = getDecoratedRight(child)
            right > -pageDimension && left < width + pageDimension
        } else {
            val top = getDecoratedTop(child)
            val bottom = getDecoratedBottom(child)
            bottom > -pageDimension && top < height + pageDimension
        }
    }

    private fun measureChildWithExactSize(child: View, w: Int, h: Int) {
        val lp = child.layoutParams as RecyclerView.LayoutParams
        val widthSpec = View.MeasureSpec.makeMeasureSpec(w - lp.leftMargin - lp.rightMargin, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(h - lp.topMargin - lp.bottomMargin, View.MeasureSpec.EXACTLY)
        child.measure(widthSpec, heightSpec)
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        if (!canScrollHorizontally() || childCount == 0 || dx == 0) return 0
        val pageDimension = pageSizeDimension()
        val maxOffset = max(0, pageCount * pageDimension - pageDimension)
        val newOffset = (mScrollOffset + dx).coerceIn(0, maxOffset)
        val consumed = newOffset - mScrollOffset
        if (consumed == 0) return 0
        mScrollOffset = newOffset
        detachAndScrapAttachedViews(recycler)
        fill(recycler)
        return consumed
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        if (!canScrollVertically() || childCount == 0 || dy == 0) return 0
        val pageDimension = pageSizeDimension()
        val maxOffset = max(0, pageCount * pageDimension - pageDimension)
        val newOffset = (mScrollOffset + dy).coerceIn(0, maxOffset)
        val consumed = newOffset - mScrollOffset
        if (consumed == 0) return 0
        mScrollOffset = newOffset
        detachAndScrapAttachedViews(recycler)
        fill(recycler)
        return consumed
    }

    fun getCurrentPage(): Int {
        val pageDimension = pageSizeDimension()
        return mScrollOffset / pageDimension
    }

    fun scrollToPage(page: Int) {
        val target = page.coerceIn(0, max(0, pageCount - 1))
        val pageDimension = pageSizeDimension()
        mScrollOffset = target * pageDimension
        requestLayout()
    }

    fun smoothScrollToPage(recyclerView: RecyclerView, page: Int) {
        val target = page.coerceIn(0, max(0, pageCount - 1))
        val position = target * pageSize
        recyclerView.smoothScrollToPosition(position) // 复用默认的 SmoothScroller（简化）；onLayoutChildren 会根据 mScrollOffset 定位
    }

    override fun computeHorizontalScrollRange(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.HORIZONTAL) pageCount * pageSizeDimension() else super.computeHorizontalScrollRange(state)
    }

    override fun computeHorizontalScrollOffset(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.HORIZONTAL) mScrollOffset else super.computeHorizontalScrollOffset(state)
    }

    override fun computeVerticalScrollRange(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.VERTICAL) pageCount * pageSizeDimension() else super.computeVerticalScrollRange(state)
    }

    override fun computeVerticalScrollOffset(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.VERTICAL) mScrollOffset else super.computeVerticalScrollOffset(state)
    }

    override fun computeHorizontalScrollExtent(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.HORIZONTAL) pageSizeDimension() else super.computeHorizontalScrollExtent(state)
    }

    override fun computeVerticalScrollExtent(state: RecyclerView.State): Int {
        return if (orientation == RecyclerView.VERTICAL) pageSizeDimension() else super.computeVerticalScrollExtent(state)
    }

    override fun scrollToPosition(position: Int) {
        val page = (position / pageSize).coerceIn(0, max(0, pageCount - 1))
        scrollToPage(page)
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State, position: Int) {
        // 简易实现：直接跳页，不做中间像素平滑；如需真正像素平滑可自定义 LinearSmoothScroller
        scrollToPosition(position)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return if (childCount == 0) null else ParcelableState(mScrollOffset)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is ParcelableState) {
            mScrollOffset = state.offset
            requestLayout()
        }
    }

    // 提供给 SmoothScroller.ScrollVectorProvider
    override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
        val targetPage = (targetPosition / pageSize)
        val currentPage = getCurrentPage()
        val direction = targetPage - currentPage
        if (direction == 0) return null
        return if (orientation == RecyclerView.HORIZONTAL) PointF(direction.toFloat(), 0f) else PointF(0f, direction.toFloat())
    }

    class ParcelableState(val offset: Int) : Parcelable {
        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(offset)
        }

        override fun describeContents(): Int = 0

        companion object CREATOR : Parcelable.Creator<ParcelableState> {
            override fun createFromParcel(parcel: Parcel): ParcelableState = ParcelableState(parcel.readInt())
            override fun newArray(size: Int): Array<ParcelableState?> = arrayOfNulls(size)
        }
    }

    fun getPageDimension(): Int = pageSizeDimension()
    fun getScrollOffset(): Int = mScrollOffset

    fun setPagePadding(startPx: Int, endPx: Int) {
        val s = startPx.coerceAtLeast(0)
        val e = endPx.coerceAtLeast(0)
        if (s == pagePaddingStartPx && e == pagePaddingEndPx) return
        pagePaddingStartPx = s
        pagePaddingEndPx = e
        // 不改变单页总尺寸，只影响内部布局；offset 可能仍有效，无需扩展范围
        requestLayout()
    }

    fun getPagePaddingStart(): Int = pagePaddingStartPx
    fun getPagePaddingEnd(): Int = pagePaddingEndPx
}
