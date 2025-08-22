package com.example.animation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.*

class RotatingLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val cardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeWidth = dpToPx(3f)
        strokeCap = Paint.Cap.ROUND
    }

    private val cardRect = RectF()
    private val cornerRadius = dpToPx(12f)
    private val lineLength = dpToPx(50f)
    private val cardMargin = dpToPx(40f)

    private var currentProgress = 0f
    private var animator: ValueAnimator? = null

    private val linePath = Path()

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cardRect.set(
            cardMargin,
            cardMargin,
            w - cardMargin,
            h - cardMargin
        )
        startAnimation()
    }

    private fun startAnimation() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                currentProgress = animation.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制圆角卡片
        canvas.drawRoundRect(cardRect, cornerRadius, cornerRadius, cardPaint)

        // 计算并绘制线段路径
        calculateAndDrawLine(canvas, currentProgress)
    }

    private fun calculateAndDrawLine(canvas: Canvas, progress: Float) {
        val perimeter = 2 * (cardRect.width() + cardRect.height()) - 8 * cornerRadius + 2 * PI.toFloat() * cornerRadius
        val currentDistance = progress * perimeter

        var remainingDistance = currentDistance
        linePath.reset()

        when {
            // 顶边
            remainingDistance <= cardRect.width() - 2 * cornerRadius -> {
                val centerX = cardRect.left + cornerRadius + remainingDistance
                val centerY = cardRect.top
                drawStraightLine(centerX, centerY, 0f)
            }
            // 右上角圆弧
            remainingDistance <= cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius / 2 -> {
                remainingDistance -= (cardRect.width() - 2 * cornerRadius)
                val arcProgress = remainingDistance / (PI.toFloat() * cornerRadius / 2)
                drawCornerLine(cardRect.right - cornerRadius, cardRect.top + cornerRadius, cornerRadius, -PI.toFloat() / 2, arcProgress)
            }
            // 右边
            remainingDistance <= cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius / 2 + cardRect.height() - 2 * cornerRadius -> {
                remainingDistance -= (cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius / 2)
                val centerX = cardRect.right
                val centerY = cardRect.top + cornerRadius + remainingDistance
                drawStraightLine(centerX, centerY, PI.toFloat() / 2)
            }
            // 右下角圆弧
            remainingDistance <= cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius + cardRect.height() - 2 * cornerRadius -> {
                remainingDistance -= (cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius / 2 + cardRect.height() - 2 * cornerRadius)
                val arcProgress = remainingDistance / (PI.toFloat() * cornerRadius / 2)
                drawCornerLine(cardRect.right - cornerRadius, cardRect.bottom - cornerRadius, cornerRadius, 0f, arcProgress)
            }
            // 底边
            remainingDistance <= 2 * (cardRect.width() - 2 * cornerRadius) + PI.toFloat() * cornerRadius + cardRect.height() - 2 * cornerRadius -> {
                remainingDistance -= (cardRect.width() - 2 * cornerRadius + PI.toFloat() * cornerRadius + cardRect.height() - 2 * cornerRadius)
                val centerX = cardRect.right - cornerRadius - remainingDistance
                val centerY = cardRect.bottom
                drawStraightLine(centerX, centerY, PI.toFloat())
            }
            // 左下角圆弧
            remainingDistance <= 2 * (cardRect.width() - 2 * cornerRadius) + 3 * PI.toFloat() * cornerRadius / 2 + cardRect.height() - 2 * cornerRadius -> {
                remainingDistance -= (2 * (cardRect.width() - 2 * cornerRadius) + PI.toFloat() * cornerRadius + cardRect.height() - 2 * cornerRadius)
                val arcProgress = remainingDistance / (PI.toFloat() * cornerRadius / 2)
                drawCornerLine(cardRect.left + cornerRadius, cardRect.bottom - cornerRadius, cornerRadius, PI.toFloat() / 2, arcProgress)
            }
            // 左边
            remainingDistance <= 2 * (cardRect.width() - 2 * cornerRadius) + 3 * PI.toFloat() * cornerRadius / 2 + 2 * (cardRect.height() - 2 * cornerRadius) -> {
                remainingDistance -= (2 * (cardRect.width() - 2 * cornerRadius) + 3 * PI.toFloat() * cornerRadius / 2 + cardRect.height() - 2 * cornerRadius)
                val centerX = cardRect.left
                val centerY = cardRect.bottom - cornerRadius - remainingDistance
                drawStraightLine(centerX, centerY, 3 * PI.toFloat() / 2)
            }
            // 左上角圆弧
            else -> {
                remainingDistance -= (2 * (cardRect.width() - 2 * cornerRadius) + 3 * PI.toFloat() * cornerRadius / 2 + 2 * (cardRect.height() - 2 * cornerRadius))
                val arcProgress = remainingDistance / (PI.toFloat() * cornerRadius / 2)
                drawCornerLine(cardRect.left + cornerRadius, cardRect.top + cornerRadius, cornerRadius, PI.toFloat(), arcProgress)
            }
        }

        canvas.drawPath(linePath, linePaint)
    }

    private fun drawStraightLine(centerX: Float, centerY: Float, angle: Float) {
        val startX = centerX - lineLength / 2 * cos(angle)
        val startY = centerY - lineLength / 2 * sin(angle)
        val endX = centerX + lineLength / 2 * cos(angle)
        val endY = centerY + lineLength / 2 * sin(angle)

        // 创建线性渐变，两端透明，中间不透明
        val gradient = LinearGradient(
            startX, startY, endX, endY,
            intArrayOf(
                Color.TRANSPARENT,
                Color.BLUE,
                Color.BLUE,
                Color.TRANSPARENT
            ),
            floatArrayOf(0f, 0.2f, 0.8f, 1f),
            Shader.TileMode.CLAMP
        )
        linePaint.shader = gradient

        linePath.moveTo(startX, startY)
        linePath.lineTo(endX, endY)
    }

    private fun drawCornerLine(centerX: Float, centerY: Float, radius: Float, startAngle: Float, progress: Float) {
        val arcAngle = startAngle + progress * PI.toFloat() / 2

        // 计算线段在圆角边缘的弧长对应的角度
        val lineArcLength = lineLength
        val lineAngleSpan = lineArcLength / radius

        // 计算线段在圆弧上的起始和结束角度
        val lineStartAngle = arcAngle - lineAngleSpan / 2
        val lineEndAngle = arcAngle + lineAngleSpan / 2

        // 计算起始点和结束点在圆角边缘上的位置
        val startX = centerX + radius * cos(lineStartAngle)
        val startY = centerY + radius * sin(lineStartAngle)
        val endX = centerX + radius * cos(lineEndAngle)
        val endY = centerY + radius * sin(lineEndAngle)

        // 为圆弧线段创建线性渐变
        val gradient = LinearGradient(
            startX, startY, endX, endY,
            intArrayOf(
                Color.TRANSPARENT,
                Color.BLUE,
                Color.BLUE,
                Color.TRANSPARENT
            ),
            floatArrayOf(0f, 0.2f, 0.8f, 1f),
            Shader.TileMode.CLAMP
        )
        linePaint.shader = gradient

        linePath.moveTo(startX, startY)

        // 绘制沿着圆角边缘的弧线
        val arcRect = RectF(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius
        )

        linePath.arcTo(
            arcRect,
            Math.toDegrees(lineStartAngle.toDouble()).toFloat(),
            Math.toDegrees(lineAngleSpan.toDouble()).toFloat()
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.cancel()
    }
}
