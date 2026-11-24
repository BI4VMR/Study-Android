package net.bi4vmr.tool.base.launcher

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

/**
 * 拖拽层（对应 MIUI 的 DragLayer + DragController + DragView，已简化）。
 *
 * 作为界面根布局：长按图标后由本层拦截全部触摸事件，
 * 用一个跟随手指的图标快照（DragView）表示被拖拽的元素，并负责：
 * - 判定当前的放置目标（Dock 或 Workspace 当前页）；
 * - 悬停一段时间后触发目标 CellLayout 的挤位动画；
 * - 拖到屏幕左右边缘时自动翻页；
 * - 松手后把图标落到目标格子，并通知外部保存布局。
 */
class DragLayer(context: Context) : FrameLayout(context) {

    companion object {
        private const val TAG: String = "TestApp-DragLayer"
        private const val REORDER_DELAY: Long = 150L
        private const val EDGE_SCROLL_DELAY: Long = 600L
        private const val DRAG_SCALE: Float = 1.15f
    }

    lateinit var workspace: Workspace
    lateinit var hotseat: CellLayout

    /** 拖拽结束（落位动画完成）回调。 */
    var onDropCompleted: (() -> Unit)? = null

    private val mainHandler = Handler(Looper.getMainLooper())
    private val edgeWidth = (24 * resources.displayMetrics.density).toInt()

    private var lastTouchX = 0f
    private var lastTouchY = 0f

    private var isDragging = false
    private var dragView: ImageView? = null
    private var dragIcon: IconView? = null
    private var sourceLayout: CellLayout? = null
    private var originCell = intArrayOf(0, 0)
    private var touchOffsetX = 0f
    private var touchOffsetY = 0f

    private var targetLayout: CellLayout? = null
    private var pendingCell: IntArray? = null
    private var appliedCell: IntArray? = null
    private var edgeDirection = 0

    private val reorderRunnable = Runnable {
        val layout = targetLayout ?: return@Runnable
        val cell = pendingCell ?: return@Runnable
        if (!cell.contentEquals(appliedCell)) {
            layout.reorderFor(cell[0], cell[1])
            appliedCell = cell
        }
    }

    private val edgeScrollRunnable = object : Runnable {
        override fun run() {
            if (!isDragging || edgeDirection == 0) return
            val next = workspace.currentPage + edgeDirection
            if (next in 0 until workspace.pageCount) {
                workspace.snapToPage(next)
                onDragMove(lastTouchX, lastTouchY)
            }
            mainHandler.postDelayed(this, EDGE_SCROLL_DELAY)
        }
    }

    val dragging: Boolean
        get() = isDragging

    /** 触摸序列被打断（如界面切到后台）时结束拖拽，避免拖拽状态残留。 */
    fun cancelDrag() {
        if (isDragging) onDrop()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        lastTouchX = ev.x
        lastTouchY = ev.y
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean = isDragging

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (!isDragging) return super.onTouchEvent(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_MOVE -> onDragMove(ev.x, ev.y)
            MotionEvent.ACTION_UP -> {
                onDragMove(ev.x, ev.y)
                onDrop()
            }

            MotionEvent.ACTION_CANCEL -> onDrop()
        }
        return true
    }

    /* ----- 拖拽流程 ----- */

    fun startDrag(icon: IconView) {
        if (isDragging) return
        val source = icon.parent as? CellLayout ?: return
        icon.animate().cancel()
        icon.translationX = 0f
        icon.translationY = 0f
        icon.isPressed = false

        val rect = Rect(0, 0, icon.width, icon.height)
        offsetDescendantRectToMyCoords(icon, rect)

        val bitmap = Bitmap.createBitmap(icon.width, icon.height, Bitmap.Config.ARGB_8888)
        icon.draw(Canvas(bitmap))
        val view = ImageView(context).apply {
            setImageBitmap(bitmap)
            x = rect.left.toFloat()
            y = rect.top.toFloat()
        }
        addView(view, LayoutParams(icon.width, icon.height))
        view.animate().scaleX(DRAG_SCALE).scaleY(DRAG_SCALE).alpha(0.9f).setDuration(150).start()

        touchOffsetX = lastTouchX - rect.left
        touchOffsetY = lastTouchY - rect.top
        val lp = icon.layoutParams as CellLayout.LayoutParams
        originCell = intArrayOf(lp.cellX, lp.cellY)

        // 原图标从网格中移除，它的格子随之空出
        source.removeView(icon)

        dragView = view
        dragIcon = icon
        sourceLayout = source
        targetLayout = null
        pendingCell = null
        appliedCell = null
        isDragging = true

        workspace.ensureTrailingEmptyPage()
        performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        onDragMove(lastTouchX, lastTouchY)
        Log.d(TAG, "startDrag: ${icon.item}")
    }

    private fun onDragMove(x: Float, y: Float) {
        val view = dragView ?: return
        view.x = x - touchOffsetX
        view.y = y - touchOffsetY

        val target = findTarget(x, y)
        if (target !== targetLayout) {
            mainHandler.removeCallbacks(reorderRunnable)
            targetLayout?.revertReorder()
            target.beginReorder()
            targetLayout = target
            pendingCell = null
            appliedCell = null
        }

        val cell = cellUnderDragView(target)
        if (!cell.contentEquals(pendingCell)) {
            pendingCell = cell
            mainHandler.removeCallbacks(reorderRunnable)
            mainHandler.postDelayed(reorderRunnable, REORDER_DELAY)
        }

        val direction = when {
            target === hotseat -> 0
            x < edgeWidth -> -1
            x > width - edgeWidth -> 1
            else -> 0
        }
        if (direction != edgeDirection) {
            edgeDirection = direction
            mainHandler.removeCallbacks(edgeScrollRunnable)
            if (direction != 0) mainHandler.postDelayed(edgeScrollRunnable, EDGE_SCROLL_DELAY)
        }
    }

    private fun onDrop() {
        val icon = dragIcon ?: return
        val view = dragView ?: return
        val source = sourceLayout ?: return
        mainHandler.removeCallbacks(reorderRunnable)
        mainHandler.removeCallbacks(edgeScrollRunnable)
        edgeDirection = 0
        isDragging = false

        var dest = targetLayout ?: source
        val cell = cellUnderDragView(dest)
        if (!cell.contentEquals(appliedCell)) dest.reorderFor(cell[0], cell[1])

        var finalCell: IntArray? = if (!dest.isOccupied(cell[0], cell[1])) cell else dest.findNearestVacant(cell[0], cell[1])
        if (finalCell == null) {
            // 目标容器已满：退回原位置
            Log.d(TAG, "drop: target is full, revert to origin")
            dest.revertReorder()
            dest = source
            finalCell = originCell
        }
        dest.commitReorder()
        source.commitReorder()

        icon.item.container = if (dest === hotseat) ItemInfo.CONTAINER_HOTSEAT else ItemInfo.CONTAINER_DESKTOP
        icon.applyContainer()
        icon.visibility = View.INVISIBLE
        dest.addView(icon, CellLayout.LayoutParams(finalCell[0], finalCell[1]))

        val rect = Rect(0, 0, 0, 0)
        offsetDescendantRectToMyCoords(dest, rect)
        if (dest !== hotseat) {
            // 翻页动画可能尚未结束，按最终滚动位置修正
            rect.offset(workspace.scrollX - workspace.currentPage * workspace.width, 0)
        }
        val targetX = rect.left + dest.cellLeft(finalCell[0]) + (dest.cellWidth - view.width) / 2f
        val targetY = rect.top + dest.cellTop(finalCell[1]) + (dest.cellHeight - view.height) / 2f
        view.animate()
            .x(targetX)
            .y(targetY)
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(200)
            .withEndAction {
                icon.visibility = View.VISIBLE
                removeView(view)
                dragView = null
                dragIcon = null
                sourceLayout = null
                targetLayout = null
                onDropCompleted?.invoke()
            }
            .start()
        Log.d(TAG, "drop: ${icon.item.title} -> cell=${finalCell.contentToString()}, hotseat=${dest === hotseat}, touch=($lastTouchX,$lastTouchY)")
    }

    /* ----- 工具方法 ----- */

    private fun findTarget(x: Float, y: Float): CellLayout {
        val rect = Rect(0, 0, hotseat.width, hotseat.height)
        offsetDescendantRectToMyCoords(hotseat, rect)
        return if (rect.contains(x.toInt(), y.toInt())) hotseat else workspace.currentLayout()
    }

    /** 以拖拽图标的中心点判定所在格子。 */
    private fun cellUnderDragView(layout: CellLayout): IntArray {
        val view = dragView ?: return intArrayOf(0, 0)
        val rect = Rect(0, 0, 0, 0)
        offsetDescendantRectToMyCoords(layout, rect)
        val centerX = lastTouchX - touchOffsetX + view.width / 2f
        val centerY = lastTouchY - touchOffsetY + view.height / 2f
        return layout.pointToCell(centerX - rect.left, centerY - rect.top)
    }
}
