package net.bi4vmr.study
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CircularProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private var strokeWidth = 40f // 圆环宽度
    private var backgroundColor = 0xFFE0E0E0.toInt() // 圆环背景颜色
    private var progressColor = 0xFF6200EE.toInt() // 进度颜色
    private var currentProgress = 0f // 当前进度

    init {
        backgroundPaint.color = backgroundColor
        backgroundPaint.style = Paint.Style.STROKE
        backgroundPaint.strokeWidth = strokeWidth

        progressPaint.color = progressColor
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = strokeWidth
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val diameter = minOf(w, h) - paddingLeft - paddingRight
        val radius = diameter / 2f
        rectF.set(paddingLeft.toFloat(), paddingTop.toFloat(),
            paddingLeft + diameter.toFloat(), paddingTop + diameter.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawOval(rectF, backgroundPaint) // 绘制背景圆环

        if (currentProgress > 0) {
            val sweepAngle = currentProgress * 360 / 100 // 计算扫过的角度
            canvas?.drawArc(rectF, -90f, sweepAngle, false, progressPaint) // 绘制进度弧线
        }
    }

    fun setProgress(progress: Float) {
        this.currentProgress = progress
        invalidate() // 刷新视图
    }
}



