package net.bi4vmr.tool.base.launcher

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * 分页容器（对应 MIUI 的 Workspace / ScreenView，已简化）。
 *
 * 子 View 均为 [CellLayout]，水平排列，每页宽度等于本容器宽度；支持手指滑动与惯性翻页。
 */
@SuppressLint("ViewConstructor")
class Workspace(
    context: Context,
    private val countX: Int,
    private val countY: Int,
) : ViewGroup(context) {

    companion object {
        private const val SNAP_DURATION: Int = 300
        private const val FLING_THRESHOLD_DP: Int = 500
    }

    /** 页面数量或当前页变化监听：(pageCount, currentPage, scrollProgress)。 */
    var onPageChangeListener: ((Int, Int, Float) -> Unit)? = null

    var currentPage: Int = 0
        private set

    val pageCount: Int
        get() = childCount

    private val scroller = OverScroller(context)
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private val flingThreshold = FLING_THRESHOLD_DP * resources.displayMetrics.density
    private var velocityTracker: VelocityTracker? = null
    private var downX = 0f
    private var downY = 0f
    private var lastX = 0f
    private var isBeingDragged = false

    fun page(index: Int): CellLayout = getChildAt(index) as CellLayout

    fun currentLayout(): CellLayout = page(currentPage)

    fun addPage(): CellLayout {
        val layout = CellLayout(context, countX, countY)
        val padding = (8 * resources.displayMetrics.density).toInt()
        layout.setPadding(padding, padding, padding, padding)
        addView(layout)
        notifyPageChange()
        return layout
    }

    /** 拖拽开始时在末尾追加空白页，方便把图标拖到新页面。 */
    fun ensureTrailingEmptyPage() {
        if (pageCount == 0 || page(pageCount - 1).childCount > 0) addPage()
    }

    /** 拖拽结束后移除所有空白页（至少保留一页）。 */
    fun stripEmptyPages() {
        for (i in pageCount - 1 downTo 0) {
            if (pageCount > 1 && page(i).childCount == 0) {
                removeViewAt(i)
                if (currentPage > i) currentPage--
            }
        }
        currentPage = currentPage.coerceIn(0, pageCount - 1)
        scroller.forceFinished(true)
        post { scrollTo(currentPage * width, 0) }
        notifyPageChange()
    }

    fun snapToPage(page: Int, duration: Int = SNAP_DURATION) {
        val target = page.coerceIn(0, (pageCount - 1).coerceAtLeast(0))
        currentPage = target
        val dx = target * width - scrollX
        scroller.forceFinished(true)
        scroller.startScroll(scrollX, 0, dx, 0, duration)
        postInvalidateOnAnimation()
        notifyPageChange()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val childW = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        val childH = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        for (i in 0 until childCount) getChildAt(i).measure(childW, childH)
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val w = r - l
        for (i in 0 until childCount) {
            getChildAt(i).layout(i * w, 0, (i + 1) * w, b - t)
        }
        if (changed && scroller.isFinished) scrollTo(currentPage * w, 0)
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            postInvalidateOnAnimation()
        }
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        notifyPageChange()
    }

    private fun notifyPageChange() {
        val progress = if (width > 0) scrollX.toFloat() / width else currentPage.toFloat()
        onPageChangeListener?.invoke(pageCount, currentPage, progress)
    }

    /* ----- 触摸处理 ----- */

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        trackVelocity(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                lastX = ev.x
                // 翻页动画进行中按下：直接接管，可以"接住"页面
                isBeingDragged = !scroller.isFinished
                if (isBeingDragged) scroller.abortAnimation()
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = abs(ev.x - downX)
                val dy = abs(ev.y - downY)
                if (!isBeingDragged && dx > touchSlop && dx > dy) {
                    isBeingDragged = true
                    lastX = ev.x
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isBeingDragged = false
                recycleVelocity()
            }
        }
        return isBeingDragged
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        trackVelocity(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                lastX = ev.x
            }

            MotionEvent.ACTION_MOVE -> {
                if (!isBeingDragged && abs(ev.x - downX) > touchSlop) {
                    isBeingDragged = true
                    lastX = ev.x
                }
                if (isBeingDragged) {
                    val maxScroll = ((pageCount - 1) * width).coerceAtLeast(0)
                    // 边缘阻尼：越界时位移减半
                    var delta = lastX - ev.x
                    val next = scrollX + delta
                    if (next < 0 || next > maxScroll) delta /= 2f
                    scrollBy(delta.roundToInt(), 0)
                    lastX = ev.x
                }
            }

            MotionEvent.ACTION_UP -> {
                if (isBeingDragged) {
                    val tracker = velocityTracker
                    tracker?.computeCurrentVelocity(1000)
                    val vx = tracker?.xVelocity ?: 0f
                    val page = when {
                        vx > flingThreshold -> currentPage - 1
                        vx < -flingThreshold -> currentPage + 1
                        else -> (scrollX.toFloat() / width).roundToInt()
                    }
                    snapToPage(page)
                }
                isBeingDragged = false
                recycleVelocity()
            }

            MotionEvent.ACTION_CANCEL -> {
                if (isBeingDragged) snapToPage(currentPage)
                isBeingDragged = false
                recycleVelocity()
            }
        }
        return true
    }

    private fun trackVelocity(ev: MotionEvent) {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN) recycleVelocity()
        val tracker = velocityTracker ?: VelocityTracker.obtain().also { velocityTracker = it }
        tracker.addMovement(ev)
    }

    private fun recycleVelocity() {
        velocityTracker?.recycle()
        velocityTracker = null
    }
}
