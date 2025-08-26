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
         * 子View是否从边框内部开始排列（默认值：否）。
         */
        const val CHILD_INSIDE_BORDER_DEFAULT: Boolean = false

        /**
         * 边框是否可见（默认值：可见）。
         */
        const val BORDER_VISIBLE_DEFAULT: Boolean = true

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

        /**
         * 边框动画周期（默认值：3秒）。
         */
        const val ANIM_DURATION_DEFAULT: Long = 3000L
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

    @get:JvmName("isChildInsideBorder")
    @set:JvmName("setChildInsideBorder")
    var mChildInsideBorder: Boolean = CHILD_INSIDE_BORDER_DEFAULT
        set(value) {
            field = value
            updateClipPath()
            requestLayout()
            invalidate()
        }

    @get:JvmName("isBorderVisible")
    @set:JvmName("setBorderVisible")
    var mBorderVisible: Boolean = BORDER_VISIBLE_DEFAULT
        set(value) {
            field = value
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

    // 渐变颜色数组
    @ColorInt
    private var mGradientColors: IntArray = BorderSchema.COLORFUL_HALF.colors

    // 渐变点位数组
    private var mGradientPositions: FloatArray = BorderSchema.COLORFUL_HALF.positions

    // 边框绘制区域
    private val borderRect: RectF = RectF()

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
        SweepGradient(0.0F, 0.0F, intArrayOf(0, 0), floatArrayOf(0.0F, 1.0F))

    // 着色器矩阵，配合ObjectAnimator实现旋转动画效果。
    private val matrix = Matrix()

    // 旋转动画
    private var mAnimator: ObjectAnimator? = null

    // 动画周期
    var mDuration: Long = ANIM_DURATION_DEFAULT
        set(value) {
            if (mDuration < 0) {
                Log.w(TAG, "Duration must be >= 0!")
                return
            }

            field = value
            mAnimator?.duration = value
        }

    // 当前旋转角度
    private var mDegree: Float = 0.0F
        set(value) {
            field = value
            invalidate()
        }

    // 裁切区域
    private val clipRect: RectF = RectF()

    // 裁切路径
    private val clipPath: Path = Path()

    init {
        if (attrs != null) {
            parseXMLAttrs(attrs, defStyleAttr, defStyleRes)
        }
    }

    // 解析XML属性
    private fun parseXMLAttrs(attrs: AttributeSet, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        mContext.obtainStyledAttributes(attrs, R.styleable.FluidBorderLayout, defStyleAttr, defStyleRes).use {
            mCornerRadius = it.getDimension(R.styleable.FluidBorderLayout_cornerRadius, CORNER_RADIUS_DEFAULT)
            mBorderVisible = it.getBoolean(R.styleable.FluidBorderLayout_borderVisible, BORDER_VISIBLE_DEFAULT)
            mBorderWidth = it.getDimension(R.styleable.FluidBorderLayout_borderWidth, BORDER_WIDTH_DEFAULT)
            mBorderBackgroundColor =
                it.getColor(R.styleable.FluidBorderLayout_borderBackgroundColor, BORDER_BACKGROUND_COLOR_DEFAULT)
            mDuration =
                it.getInt(R.styleable.FluidBorderLayout_animDuration, ANIM_DURATION_DEFAULT.toInt()).toLong()

            // 解析预设配色方案
            val schemaCode: Int = it.getInt(R.styleable.FluidBorderLayout_borderSchema, -1)
            val schema: BorderSchema = BorderSchema.parseByOrder(schemaCode)
            mGradientColors = schema.colors
            mGradientPositions = schema.positions
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
        mShader = SweepGradient(borderRect.centerX(), borderRect.centerY(), mGradientColors, mGradientPositions)
        borderPaint.shader = mShader

        // 裁切边框周围的区域
        clipRect.set(0.0F, 0.0F, w.toFloat(), h.toFloat())
        updateClipPath()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d(TAG, "OnLayout. Changed:[$changed] L:[$left] R:[$right] T:[$top] B:[$bottom]")
        // 如果要求子View从边框内侧开始绘制，则调整子View的布局区域。
        if (mChildInsideBorder) {
            val leftInner: Float = 0 + mBorderWidth
            val topInner: Float = 0 + mBorderWidth
            val rightInner: Float = (right - left) - mBorderWidth
            val bottomInner: Float = (bottom - top) - mBorderWidth
            super.onLayout(changed, leftInner.toInt(), topInner.toInt(), rightInner.toInt(), bottomInner.toInt())
        } else {
            super.onLayout(changed, left, top, right, bottom)
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        // 裁切画布为圆角矩形
        canvas.clipPath(clipPath)

        // 绘制子View
        super.dispatchDraw(canvas)

        // 绘制边框
        if (mBorderVisible) {
            drawBorder(canvas)
        }
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
        initAnim()
        startAnim()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelAnim()
    }

    /**
     * 使用预设配色方案。
     *
     * @param[schema] 配色方案。
     */
    fun usePresetSchema(schema: BorderSchema) {
        mGradientColors = schema.colors
        mGradientPositions = schema.positions
        mShader = SweepGradient(borderRect.centerX(), borderRect.centerY(), schema.colors, schema.positions)
        borderPaint.shader = mShader
        invalidate()
    }

    /**
     * 使用自定义配色方案。
     *
     * 内置渐变效果为 [SweepGradient] ，参数对应颜色与其在圆型中的位置。
     *
     * @param[colors] 颜色数组。
     * @param[positions] 点位数组。
     */
    fun useCustomSchema(@ColorInt colors: IntArray, positions: FloatArray) {
        mGradientColors = colors
        mGradientPositions = positions
        mShader = SweepGradient(borderRect.centerX(), borderRect.centerY(), colors, positions)
        borderPaint.shader = mShader
        invalidate()
    }

    // 更新裁切路径
    private fun updateClipPath() {
        clipPath.reset()
        clipPath.addRoundRect(clipRect, mCornerRadius, mCornerRadius, Path.Direction.CW)
    }


    /*
     * ----- 边框动画相关方法 -----
     */

    // 初始化动画
    private fun initAnim() {
        mAnimator?.cancel()
        mAnimator = ObjectAnimator.ofFloat(this, "mDegree", 0.0F, 360.0F).apply {
            duration = mDuration
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
        }
    }

    fun startAnim() {
        mAnimator?.start()
    }

    fun pauseAnim() {
        mAnimator?.pause()
    }

    fun resumeAnim() {
        mAnimator?.resume()
    }

    fun stopAnim() {
        mAnimator?.end()
    }

    private fun cancelAnim() {
        mAnimator?.cancel()
        mAnimator = null
    }
}
