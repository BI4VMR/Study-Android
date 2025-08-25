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
 * 流动边框布局 v2。
 *
 * 新增功能：
 * - 支持子View填充模式控制
 * - 多种渐变参数选择
 *
 * @author bi4vmr@outlook.com
 * @version 2.0.0
 */
class FluidBorderLayout2 @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(mContext, attrs, defStyleAttr, defStyleRes) {

    companion object {

        private const val TAG: String = "FluidBorderLayout2"

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

        /**
         * 子View填充模式（默认值：true，完全填充）。
         */
        const val CHILD_FULL_DEFAULT: Boolean = true

        // 原始渐变参数（注释保留）
        // val COLORS_DEFAULT = intArrayOf(
        //     Color.TRANSPARENT,
        //     Color.TRANSPARENT,
        //     Color.WHITE,
        //     Color.WHITE,
        //     Color.TRANSPARENT
        // )

        // val POSITIONS_DEFAULT = floatArrayOf(
        //     0.0F,
        //     0.5F,
        //     0.6666667F,
        //     0.8333333F,
        //     1.0F
        // )

        // 前1/2光谱过渡，后1/2透明的参数（注释保留）
        // @ColorInt
        // val COLORS_DEFAULT = intArrayOf(
        //     Color.TRANSPARENT,           // 起始透明
        //     Color.RED,                   // 红色
        //     Color.rgb(255, 165, 0),     // 橙色
        //     Color.YELLOW,                // 黄色
        //     Color.GREEN,                 // 绿色
        //     Color.CYAN,                  // 青色
        //     Color.BLUE,                  // 蓝色
        //     Color.MAGENTA,               // 紫红色
        //     Color.TRANSPARENT,           // 中间点透明
        //     Color.TRANSPARENT            // 结束透明
        // )

        // val POSITIONS_DEFAULT = floatArrayOf(
        //     0.0F,                        // 起始点
        //     0.05F,                       // 红色开始
        //     0.15F,                       // 橙色
        //     0.25F,                       // 黄色
        //     0.35F,                       // 绿色
        //     0.42F,                       // 青色
        //     0.47F,                       // 蓝色
        //     0.5F,                        // 紫红色结束
        //     0.5F,                        // 透明区域开始
        //     1.0F                         // 结束点
        // )

        // 对角光谱渐变参数（注释保留）
        // @ColorInt
        // val COLORS_DEFAULT = intArrayOf(
        //     Color.TRANSPARENT,           // 起始透明
        //     Color.RED,                   // 第一组光谱开始：红色
        //     Color.rgb(255, 165, 0),     // 橙色
        //     Color.YELLOW,                // 黄色
        //     Color.GREEN,                 // 绿色
        //     Color.TRANSPARENT,           // 第一组结束，透明区域开始
        //     Color.TRANSPARENT,           // 透明区域
        //     Color.CYAN,                  // 第二组光谱开始：青色
        //     Color.BLUE,                  // 蓝色
        //     Color.MAGENTA,               // 紫红色
        //     Color.rgb(128, 0, 128),     // 紫色
        //     Color.TRANSPARENT,           // 第二组结束
        //     Color.TRANSPARENT            // 最终透明
        // )

        // val POSITIONS_DEFAULT = floatArrayOf(
        //     0.0F,                        // 起始点
        //     0.02F,                       // 第一组光谱开始
        //     0.08F,                       // 橙色
        //     0.15F,                       // 黄色
        //     0.22F,                       // 绿色
        //     0.25F,                       // 第一组结束（1/4圆）
        //     0.5F,                        // 透明区域中点
        //     0.52F,                       // 第二组光谱开始
        //     0.58F,                       // 蓝色
        //     0.65F,                       // 紫红色
        //     0.72F,                       // 紫色
        //     0.75F,                       // 第二组结束（3/4圆）
        //     1.0F                         // 结束点
        // )

        // 新的渐变参数：完整光谱圆周渐变，无透明间隙
        @ColorInt
        val COLORS_DEFAULT = intArrayOf(
            Color.RED,                   // 红色
            Color.rgb(255, 165, 0),     // 橙色
            Color.YELLOW,                // 黄色
            Color.rgb(154, 205, 50),    // 黄绿色
            Color.GREEN,                 // 绿色
            Color.rgb(0, 255, 127),     // 春绿色
            Color.CYAN,                  // 青色
            Color.rgb(0, 191, 255),     // 深天蓝
            Color.BLUE,                  // 蓝色
            Color.rgb(138, 43, 226),    // 蓝紫色
            Color.MAGENTA,               // 紫红色
            Color.rgb(255, 20, 147),    // 深粉红
            Color.RED                    // 回到红色，形成闭环
        )

        val POSITIONS_DEFAULT = floatArrayOf(
            0.0F,                        // 红色
            0.083F,                      // 橙色 (1/12)
            0.167F,                      // 黄色 (2/12)
            0.25F,                       // 黄绿色 (3/12)
            0.333F,                      // 绿色 (4/12)
            0.417F,                      // 春绿色 (5/12)
            0.5F,                        // 青色 (6/12)
            0.583F,                      // 深天蓝 (7/12)
            0.667F,                      // 蓝色 (8/12)
            0.75F,                       // 蓝紫色 (9/12)
            0.833F,                      // 紫红色 (10/12)
            0.917F,                      // 深粉红 (11/12)
            1.0F                         // 回到红色 (12/12)
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
            updateChildClipPath()
            requestLayout()
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
            borderBackgroundPaint.color = value
            invalidate()
        }

    /**
     * 子View填充模式。
     *
     * true: 子View完全填充布局，边框会绘制在子View前部（可能遮盖边缘）
     * false: 子View绘制范围缩小到边框内侧，避免被边框遮盖
     */
    @get:JvmName("getChildFull")
    @set:JvmName("setChildFull")
    var mChildFull: Boolean = CHILD_FULL_DEFAULT
        set(value) {
            field = value
            updateChildClipPath()
            requestLayout()
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

    // 子View裁切路径
    private val childClipPath = Path()

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
            // TODO: 添加 childFull 属性到 attrs.xml
            // mChildFull = it.getBoolean(R.styleable.FluidBorderLayout_childFull, CHILD_FULL_DEFAULT)
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

        // 更新子View裁切路径
        updateChildClipPath()
    }

    private fun updateChildClipPath() {
        childClipPath.reset()
        if (mChildFull) {
            // 完全填充模式：子View使用完整区域
            childClipPath.addRoundRect(borderRect, mCornerRadius, mCornerRadius, Path.Direction.CW)
        } else {
            // 内部填充模式：子View缩小到边框内侧
            val childLeft = borderRect.left + mBorderWidth
            val childTop = borderRect.top + mBorderWidth
            val childRight = borderRect.right - mBorderWidth
            val childBottom = borderRect.bottom - mBorderWidth
            val childRadius = maxOf(0f, mCornerRadius - mBorderWidth)

            childClipPath.addRoundRect(
                childLeft, childTop, childRight, childBottom,
                childRadius, childRadius, Path.Direction.CW
            )
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (!mChildFull) {
            // 内部填充模式：调整子View布局
            val borderWidthInt = mBorderWidth.toInt()
            val childLeft = borderWidthInt
            val childTop = borderWidthInt
            val childRight = width - borderWidthInt
            val childBottom = height - borderWidthInt

            for (i in 0 until childCount) {
                val child = getChildAt(i)
                child.layout(childLeft, childTop, childRight, childBottom)
            }
        } else {
            // 完全填充模式：使用默认布局
            super.onLayout(changed, left, top, right, bottom)
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        // 裁切子View绘制区域
        canvas.save()
        canvas.clipPath(childClipPath)

        // 绘制子View
        super.dispatchDraw(canvas)

        canvas.restore()

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

