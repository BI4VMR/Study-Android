package net.bi4vmr.tool.base.launcher

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * 页面指示器（圆点）。
 */
class PageIndicator(context: Context) : View(context) {

    private val density = resources.displayMetrics.density
    private val radius = 3 * density
    private val gap = 12 * density
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var pageCount = 0
    private var progress = 0f

    fun update(pageCount: Int, progress: Float) {
        if (this.pageCount == pageCount && this.progress == progress) return
        this.pageCount = pageCount
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        if (pageCount <= 0) return
        val totalWidth = (pageCount - 1) * gap
        val startX = (width - totalWidth) / 2f
        val cy = height / 2f
        paint.color = 0x66FFFFFF
        for (i in 0 until pageCount) {
            canvas.drawCircle(startX + i * gap, cy, radius, paint)
        }
        paint.color = Color.WHITE
        val p = progress.coerceIn(0f, (pageCount - 1).toFloat())
        canvas.drawCircle(startX + p * gap, cy, radius * 1.3f, paint)
    }
}
