package net.bi4vmr.study.base

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale
import kotlin.math.abs

/**
 * 针对 PagedGridLayoutManager 的简单整页吸附 Helper：
 * - 基于 LayoutManager 的 getScrollOffset 与 getPageDimension 计算当前页与进度
 * - 快速 fling 直接前后翻页；慢速根据进度阈值(0.5)决定留在当前页还是翻页
 */
class PagedSnapHelper(
    private val flingThreshold: Int = 800,
    private val forwardThreshold: Float = 0.2f, // 正向翻页阈值（相对起始页偏移 >= 该比例）
    private val backwardThreshold: Float = 0.2f // 反向翻页阈值（相对起始页偏移 <= -该比例）
) : PagerSnapHelper() {

    init {
        require(forwardThreshold in 0f..1f) { "forwardThreshold must be between 0 and 1" }
        require(backwardThreshold in 0f..1f) { "backwardThreshold must be between 0 and 1" }
    }

    private var recyclerView: RecyclerView? = null
    // 手势开始时锁定的页码与偏移
    private var startPage: Int = 0
    private var startOffset: Int = 0
    private var dragging: Boolean = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
            val lm = rv.layoutManager as? PagedGridLayoutManager ?: return
            when (newState) {
                RecyclerView.SCROLL_STATE_DRAGGING -> {
                    if (!dragging) {
                        dragging = true
                        val pageSizePx = lm.getPageDimension() // 包含间距的整页跨度
                        val offset = lm.getScrollOffset()
                        startPage = (offset / pageSizePx).coerceIn(0, lm.pageCount - 1)
                        startOffset = startPage * pageSizePx
                        Log.i(
                            "PagedSnapHelper",
                            "dragStart: offset=$offset pageSize=$pageSizePx startPage=$startPage startOffset=$startOffset"
                        )
                    }
                }
                RecyclerView.SCROLL_STATE_IDLE -> {
                    if (dragging) {
                        Log.i("PagedSnapHelper", "dragEnd: finalOffset=${lm.getScrollOffset()} startPage=$startPage")
                    }
                    dragging = false
                }
            }
        }
    }

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        super.attachToRecyclerView(recyclerView)
        this.recyclerView?.removeOnScrollListener(scrollListener)
        this.recyclerView = recyclerView
        recyclerView?.addOnScrollListener(scrollListener)
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        // 不依赖具体子 view，对齐逻辑在 calculateDistanceToFinalSnap 完成，这里返回第一个 child 即可
        return layoutManager.getChildAt(0)
    }

    // 使用起始页作为基准计算需要的对齐距离
    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray {
        val out = IntArray(2)
        val lm = layoutManager as? PagedGridLayoutManager ?: return out
        val pageSizePx = lm.getPageDimension() // 包含间距的整页跨度
        if (pageSizePx <= 0) return out

        val currentOffset = lm.getScrollOffset()
        // 若未处于拖动阶段（比如初始化或惯性滚动完成），刷新 startPage 防止数据变化
        if (!dragging) {
            startPage = (currentOffset / pageSizePx).coerceIn(0, lm.pageCount - 1)
            startOffset = startPage * pageSizePx
        }

        val deltaFromStart = (currentOffset - startOffset).toFloat() // 正值：向前（下一页方向），负值：向后
        val progress = deltaFromStart / pageSizePx // 可正可负

        val maxPage = lm.pageCount - 1
        val targetPage = when {
            progress >= forwardThreshold && startPage < maxPage -> startPage + 1
            progress <= -backwardThreshold && startPage > 0 -> startPage - 1
            else -> startPage
        }
        val desiredOffset = targetPage * pageSizePx
        val deltaPixels = desiredOffset - currentOffset
        if (lm.canScrollHorizontally()) out[0] = deltaPixels else out[1] = deltaPixels

        Log.i(
            "PagedSnapHelper",
            "calculateDistance: dragging=$dragging startPage=$startPage currentOffset=$currentOffset deltaFromStart=${String.format(Locale.US,"%.1f",deltaFromStart)} progress=${String.format(
                Locale.US,"%.3f",progress)} targetPage=$targetPage forwardTh=$forwardThreshold backwardTh=$backwardThreshold deltaPixels=$deltaPixels"
        )
        return out
    }

    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        val lm = layoutManager as? PagedGridLayoutManager ?: return RecyclerView.NO_POSITION
        val pageSizePx = lm.getPageDimension() // 包含间距的整页跨度
        if (pageSizePx <= 0) return RecyclerView.NO_POSITION
        val currentOffset = lm.getScrollOffset()

        // 若未拖动（纯 fling 或程序触发），将当前页设为起始页
        if (!dragging) {
            startPage = (currentOffset / pageSizePx).coerceIn(0, lm.pageCount - 1)
            startOffset = startPage * pageSizePx
        }

        val deltaFromStart = (currentOffset - startOffset).toFloat()
        val progress = deltaFromStart / pageSizePx
        val velocity = if (lm.canScrollHorizontally()) velocityX else velocityY
        val maxPage = lm.pageCount - 1

        // 根据手势初始页与偏移决定方向；优先处理高速 fling
        val targetPage = when {
            abs(velocity) > flingThreshold && velocity > 0 && startPage < maxPage -> startPage + 1
            abs(velocity) > flingThreshold && velocity < 0 && startPage > 0 -> startPage - 1
            velocity > 0 && progress >= forwardThreshold && startPage < maxPage -> startPage + 1
            velocity < 0 && progress <= -backwardThreshold && startPage > 0 -> startPage - 1
            // 无显著速度：仅根据偏移决定
            velocity == 0 && progress >= forwardThreshold && startPage < maxPage -> startPage + 1
            velocity == 0 && progress <= -backwardThreshold && startPage > 0 -> startPage - 1
            else -> startPage
        }

        val finalPage = targetPage.coerceIn(0, maxPage)
        val targetPos = finalPage * lm.pageSize
        Log.i(
            "PagedSnapHelper",
            "findTargetSnapPosition: dragging=$dragging startPage=$startPage currentOffset=$currentOffset deltaFromStart=${String.format(Locale.US,"%.1f",deltaFromStart)} progress=${String.format(Locale.US,"%.3f",progress)} velocity=$velocity absV=${abs(velocity)} targetPage=$targetPage finalPage=$finalPage forwardTh=$forwardThreshold backwardTh=$backwardThreshold flingTh=$flingThreshold"
        )
        return targetPos
    }
}
