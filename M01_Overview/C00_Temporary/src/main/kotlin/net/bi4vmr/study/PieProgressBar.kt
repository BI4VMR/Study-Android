package net.bi4vmr.study

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.UiContext

/**
 * 饼状进度条。
 *
 * 参考工程：
 *
 * - [EasyView](https://github.com/lilongweidev/EasyView) : PieProgressBar
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
class PieProgressBar @JvmOverloads constructor(
    @UiContext
    private val mContext: Context,
    attrs: AttributeSet? = null
) : View(mContext, attrs) {

    companion object {

        private const val TAG: String = "PieProgressBar"

        /**
         * 最小进度：0。
         */
        const val PROGRESS_MIN: Int = 0

        /**
         * 初始进度（默认值：50）。
         */
        const val PROGRESS_DEFAULT: Int = 50

        /**
         * 最大进度（默认值：100）。
         */
        const val PROGRESS_MAX_DEFAULT: Int = 100

        /**
         * 起始绘制角度（默认值：12点位置）。
         */
        const val START_DEGREE_DEFAULT: Float = 270.0F

        /**
         * 饼图与圆环颜色（默认值：绿色）。
         */
        const val COLOR_DEFAULT: Int = Color.GREEN

        /**
         * 圆环宽度（默认值：4 px）。
         */
        const val RING_WIDTH_DEFAULT: Int = 4

        /**
         * 是否显示圆环（默认值：显示）。
         */
        const val RING_DISPLAY_DEFAULT: Boolean = true
    }

    /**
     * 当前进度。
     */
    @get:JvmName("getProgressColor")
    @get:ColorInt
    @set:JvmName("setProgressColor")
    var mProgress: Int = COLOR_DEFAULT
        set(@ColorInt value) {
            field = value
            invalidate()
        }

    /**
     * 最大进度。
     */
    @get:JvmName("getProgressColor")
    @get:ColorInt
    @set:JvmName("setProgressColor")
    var mMaxProgress: Int = COLOR_DEFAULT
        set(@ColorInt value) {
            field = value
            invalidate()
        }

    /**
     * 起始绘制角度。
     */
    @get:JvmName("getStartDegree")
    @set:JvmName("setStartDegree")
    var mStartDegree: Float = START_DEGREE_DEFAULT
        set(value) {
            field = value
            invalidate()
        }

    /**
     * 饼图颜色。
     */
    @get:JvmName("getProgressColor")
    @get:ColorInt
    @set:JvmName("setProgressColor")
    var mPieColor: Int = COLOR_DEFAULT
        set(@ColorInt value) {
            field = value
            invalidate()
        }

    /**
     * 圆环颜色。
     */
    @get:JvmName("getRingColor")
    @get:ColorInt
    @set:JvmName("setRingColor")
    var mRingColor: Int = COLOR_DEFAULT
        set(@ColorInt value) {
            field = value
            invalidate()
        }

    /**
     * 圆环宽度。
     */
    @get:JvmName("getRingWidth")
    @get:Px
    @set:JvmName("setRingWidth")
    var mRingWidth: Int = RING_WIDTH_DEFAULT
        set(@Px value) {
            field = value
            invalidate()
        }

    /**
     * 是否显示圆环。
     */
    @get:JvmName("isRingDisplay")
    @set:JvmName("setRingDisplay")
    var mRingDisplay: Boolean = RING_DISPLAY_DEFAULT
        set(value) {
            field = value
            invalidate()
        }

    private var mStrokeWidth: Int = 8

    // private var mMaxProgress: Float = 100f
    private var mCurrentProgress: Float = 0f
    private var isGradient: Boolean = false
    private var colorArray: IntArray? = null
    private var isCounterClockwise: Boolean = false
    private var mCurrentAngle: Float = 0f
    private val rectF by lazy { RectF() }
    private val strokePaint by lazy {
        Paint().apply {
            color = mPieColor
            style = Paint.Style.STROKE
            strokeWidth = mStrokeWidth.toFloat()
            isAntiAlias = true
        }
    }

    init {
        if (attrs != null) {
            parseXMLAttrs(attrs)
        }
    }

    // 解析XML属性
    private fun parseXMLAttrs(attrs: AttributeSet) {
        mContext.obtainStyledAttributes(attrs, R.styleable.PieProgressBar).use {
            mProgress = it.getInt(R.styleable.PieProgressBar_progress, PROGRESS_DEFAULT)
            mMaxProgress = it.getInt(R.styleable.PieProgressBar_maxProgress, PROGRESS_MAX_DEFAULT)
            mPieColor = it.getColor(R.styleable.PieProgressBar_pieColor, COLOR_DEFAULT)
            mRingColor = it.getColor(R.styleable.PieProgressBar_ringColor, COLOR_DEFAULT)
            mRingWidth = it.getDimensionPixelSize(R.styleable.PieProgressBar_ringWidth, RING_WIDTH_DEFAULT)
            mRingDisplay = it.getBoolean(R.styleable.PieProgressBar_ringDisplay, RING_DISPLAY_DEFAULT)

            // isGradient = getBoolean(R.styleable.PieProgressBar_gradient, false)
            mStartDegree = getInt(R.styleable.PieProgressBar_startDegree, DEFAULT_STARTDEGREE)
            // isCounterClockwise = getBoolean(R.styleable.PieProgressBar_counterClockwise, false)
            val textArray = getTextArray(R.styleable.PieProgressBar_gradientColorArray)
            textArray?.let {
                colorArray = IntArray(it.size).apply {
                    for (i in it.indices) {
                        this[i] = Color.parseColor(it[i].toString())
                    }
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        Log.d("PieProgressBar", "OnDraw. Width:[$width] Height:[$height]")
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        rectF.set(0f, 0f, centerX * 2, centerY * 2)
        // Draw stroke
        // canvas.drawCircle(centerX, centerY, mRadius - (mStrokeWidth / 2).toFloat(), strokePaint)
        // canvas.drawCircle(centerX, centerY, (width / 2).toFloat(), strokePaint)
        canvas.drawCircle(centerX, centerY, ((width / 2) - (mStrokeWidth / 2)).toFloat(), strokePaint)
        // Draw progress
        val paint = Paint().apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            color = mProgressColor
        }
        if (isGradient && colorArray != null) {
            paint.shader = RadialGradient(
                rectF.centerX(),
                rectF.centerY(),
                mRadius.toFloat(),
                colorArray!!,
                null,
                Shader.TileMode.MIRROR
            )
        }

        mCurrentAngle = 360 * (mCurrentProgress / mMaxProgress)

        var startAngle: Float = mStartDegree
        var sweepAngle: Float = mCurrentAngle
        if (isCounterClockwise) {
            startAngle = mStartDegree - 360.0F
            sweepAngle = -mCurrentAngle
        }

        canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
    }

    fun setStartDegree(angle: Int) {
        require(angle in 0..360) { "angle must be between 0 and 360" }
        mStartDegree = angle
        invalidate()
    }

    fun setGradient(gradient: Boolean) {
        isGradient = gradient
        invalidate()
    }

    fun setCounterClockwise(counterClockwise: Boolean) {
        isCounterClockwise = counterClockwise
        invalidate()
    }

    fun setColorArray(colorArr: IntArray?) {
        colorArr ?: return
        colorArray = colorArr
    }

    fun setProgress(progress: Float) {
        var validProgress = progress.coerceAtLeast(0f).coerceAtMost(mMaxProgress)
        mCurrentProgress = validProgress
        mCurrentAngle = 360 * (validProgress / mMaxProgress)
    }
}
