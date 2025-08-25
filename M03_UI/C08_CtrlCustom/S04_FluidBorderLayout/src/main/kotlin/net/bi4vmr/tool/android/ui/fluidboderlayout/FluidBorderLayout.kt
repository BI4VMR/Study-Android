package net.bi4vmr.tool.android.ui.fluidboderlayout

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.Px
import net.bi4vmr.study.R

/**
 * 流动边框布局。
 *
 * 参考资料：
 *
 * - [BlogCodes - FluidColorfulFrame](https://github.com/jhwsx/BlogCodes/tree/master/FluidColorfulFrame)
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class FluidBorderLayout @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(mContext, attrs, defStyleAttr, defStyleRes) {

    companion object {

        private const val TAG: String = "FluidBorderLayout"

        /**
         * 起始绘制角度（默认值：3点位置）。
         */
        const val START_DEGREE_DEFAULT: Float = 0.0F

        /**
         * 边框宽度（默认值：4 px）。
         */
        @Px
        const val BORDER_WIDTH_DEFAULT: Float = 4.0F

        /**
         * 边框圆角半径（默认值：16 px）。
         */
        @Px
        const val CORNER_RADIUS_DEFAULT: Float = 16.0F

        /**
         * 边框底色（默认值：透明）。
         */
        @ColorInt
        const val BORDER_BACKGROUND_COLOR_DEFAULT: Int = Color.TRANSPARENT

        val COLORS_DEFAULT = intArrayOf(
            Color.TRANSPARENT,
            Color.TRANSPARENT,
            Color.WHITE,
            Color.WHITE,
            Color.TRANSPARENT
        )

        val POSITIONS_DEFAULT = floatArrayOf(
            0.0F,
            0.5F,
            0.6666667F,
            0.8333333F,
            1.0F
        )
    }

    /**
     * 圆角半径（像素）。
     *
     * 将会影响边框与内容，布局内部的View边缘会被裁切。
     */
    @get:JvmName("getCornerRadius")
    @get:Px
    @set:JvmName("setCornerRadius")
    var mCornerRadius: Float = CORNER_RADIUS_DEFAULT
        set(@Px value) {
            field = value
            updateClipPath()
            invalidate()
        }

    /**
     * 边框宽度（像素）。
     */
    @get:JvmName("getBorderWidth")
    @get:Px
    @set:JvmName("setBorderWidth")
    var mBorderWidth: Float = BORDER_WIDTH_DEFAULT
        set(@Px value) {
            field = value
            // 同步更改边框画笔的粗细
            borderPaint.strokeWidth = value
            borderBackgroundPaint.strokeWidth = value
            invalidate()
        }

    /**
     * 边框底色。
     */
    @get:JvmName("getBorderBackgroundColor")
    @get:ColorInt
    @set:JvmName("setBorderBackgroundColor")
    var mBorderBackgroundColor: Int = BORDER_BACKGROUND_COLOR_DEFAULT
        set(@ColorInt value) {
            field = value
            // 同步更改边框底色画笔的颜色
            borderBackgroundPaint.color = value
            invalidate()
        }

    // 边框绘制区域
    private val borderRect = RectF()

    // 边框底色画笔
    private val borderBackgroundPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = mBorderBackgroundColor
        style = Paint.Style.STROKE
        strokeWidth = mBorderWidth
    }

    // 边框画笔
    private val borderPaint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = mBorderWidth
    }

    // 渐变着色器
    private var mShader: SweepGradient =
        // 此处只是确保变量不为空的占位符，实际的Shader需要在 `onSizeChanged()` 回调方法中创建。
        SweepGradient(0.0F, 0.0F, COLORS_DEFAULT, POSITIONS_DEFAULT)

    // 着色器矩阵，配合 `fluidAnim` 实现旋转动画效果。
    private val matrix = Matrix()

    // 旋转动画
    private var fluidAnim: ObjectAnimator? = null

    // 当前旋转角度
    private var mDegree: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    // 裁切路径
    private val clipPath = Path()

    init {
        if (attrs != null) {
            parseXMLAttrs(attrs, defStyleAttr, defStyleRes)
        }
    }

    // 解析XML属性
    private fun parseXMLAttrs(attrs: AttributeSet, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        mContext.obtainStyledAttributes(attrs, R.styleable.FluidBorderLayout, defStyleAttr, defStyleRes).use {
            mCornerRadius = it.getDimension(R.styleable.FluidBorderLayout_cornerRadius, CORNER_RADIUS_DEFAULT)
            mBorderWidth = it.getDimension(R.styleable.FluidBorderLayout_borderWidth, BORDER_WIDTH_DEFAULT)
            mBorderBackgroundColor =
                it.getColor(R.styleable.FluidBorderLayout_borderBackgroundColor, BORDER_BACKGROUND_COLOR_DEFAULT)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d(TAG, "OnSizeChanged. Width:[$w] Height:[$h]")
        // 边框绘制区域向内侧偏移1/2个边框宽度，避免外侧的一半边框无法显示。
        val offset: Float = mBorderWidth / 2
        val left: Float = 0 + offset
        val top: Float = 0 + offset
        val right: Float = w - offset
        val bottom: Float = h - offset
        borderRect.set(left, top, right, bottom)

        // 以控件中心点为圆心，创建扇形渐变着色器。
        mShader = SweepGradient(borderRect.centerX(), borderRect.centerY(), COLORS_DEFAULT, POSITIONS_DEFAULT)
        borderPaint.shader = mShader

        updateClipPath()
    }

    private fun updateClipPath() {
        clipPath.reset()
        clipPath.addRoundRect(borderRect, mCornerRadius, mCornerRadius, Path.Direction.CW)
    }

    override fun dispatchDraw(canvas: Canvas) {
        // 先裁切画布为圆角矩形
        // canvas.clipPath(clipPath)

        // 绘制子View
        super.dispatchDraw(canvas)

        // 绘制边框
        drawBorder(canvas)
    }

    private fun drawBorder(canvas: Canvas) {
        // 绘制边框底色
        if (borderBackgroundPaint.color != Color.TRANSPARENT) {
            canvas.drawRoundRect(borderRect, mCornerRadius, mCornerRadius, borderBackgroundPaint)
        }

        // 渐变区域应用当前的旋转角度
        matrix.reset()
        matrix.setRotate(mDegree, borderRect.centerX(), borderRect.centerY())
        mShader.setLocalMatrix(matrix)
        // 绘制圆角矩形边框
        canvas.drawRoundRect(borderRect, mCornerRadius, mCornerRadius, borderPaint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startFluid()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelFluid()
    }

    fun startFluid() {
        // 如果动画已经在运行，先取消
        fluidAnim?.cancel()

        fluidAnim = ObjectAnimator.ofFloat(this, "mDegree", 0.0F, 360.0F).apply {
            duration = 8000L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
        }
        fluidAnim?.start()
    }

    fun cancelFluid() {
        fluidAnim?.cancel()
        fluidAnim = null
    }
}
