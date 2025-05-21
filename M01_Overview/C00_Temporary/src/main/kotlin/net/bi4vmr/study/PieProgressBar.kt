package net.bi4vmr.study

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author yigangzhan@pateo.com.cn
 * @since 1.0.0
 */
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PieProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        /**
         * 默认起始角度 - 12点位置。
         */
        const val DEFAULT_STARTDEGREE: Int = 270
    }

    private var mRadius: Int = 80
    private var mStrokeWidth: Int = 8
    private var mProgressColor: Int = Color.BLACK
    private var mStartDegree: Int = DEFAULT_STARTDEGREE

    @get:JvmName("getEdf") // 定义Java getter方法名为 getJavaName
    @set:JvmName("setJavaName") // 定义Java setter方法名为 setJavaName
    var mEndAngle: Int = 360
        set(value) {
            field = value
            invalidate()
        }
    private var mMaxProgress: Float = 100f
    private var mCurrentProgress: Float = 0f
    private var isGradient: Boolean = false
    private var colorArray: IntArray? = null
    private var mDuration: Long = 1000
    private var animationEnable: Boolean = false
    private var isCounterClockwise: Boolean = false
    private var mCurrentAngle: Float = 0f
    private val rectF by lazy { RectF() }
    private val strokePaint by lazy {
        Paint().apply {
            color = mProgressColor
            style = Paint.Style.STROKE
            strokeWidth = mStrokeWidth.toFloat()
            isAntiAlias = true
        }
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.PieProgressBar, defStyleAttr, 0).apply {
            mRadius = getDimensionPixelSize(R.styleable.PieProgressBar_radius, 80)
            mStrokeWidth = getDimensionPixelSize(R.styleable.PieProgressBar_strokeWidth, 8)
            mProgressColor = getColor(R.styleable.PieProgressBar_progressbarColor, Color.BLACK)
            mMaxProgress = getInt(R.styleable.PieProgressBar_maxProgress, 100).toFloat()
            mCurrentProgress = getInt(R.styleable.PieProgressBar_progress, 0).toFloat()
            isGradient = getBoolean(R.styleable.PieProgressBar_gradient, false)
            mStartDegree = getInt(R.styleable.PieProgressBar_startDegree, DEFAULT_STARTDEGREE)
            isCounterClockwise = getBoolean(R.styleable.PieProgressBar_counterClockwise, false)
            val textArray = getTextArray(R.styleable.PieProgressBar_gradientColorArray)
            textArray?.let {
                colorArray = IntArray(it.size).apply {
                    for (i in it.indices) {
                        this[i] = Color.parseColor(it[i].toString())
                    }
                }
            }
            recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.AT_MOST -> mRadius * 2
            else -> MeasureSpec.getSize(widthMeasureSpec)
        }
        setMeasuredDimension(width, width)
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = (width / 2).toFloat()
        rectF.set(0f, 0f, centerX * 2, centerX * 2)
        // Draw stroke
        canvas.drawCircle(centerX, centerX, mRadius - (mStrokeWidth / 2).toFloat(), strokePaint)
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
        val currentAngle = if (animationEnable) mCurrentAngle else 360 * (mCurrentProgress / mMaxProgress)
        val (startAngle, sweepAngle) = if (isCounterClockwise) {
            Pair(mStartDegree - 360f, -currentAngle)
        } else {
            Pair(mStartDegree.toFloat(), currentAngle)
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
        setAnimator(mStartDegree.toFloat(), mCurrentAngle)
    }

    fun setAnimationEnable(){
        animationEnable = true
    }

    private fun setAnimator(start: Float, target: Float) {

        ValueAnimator.ofFloat(start, target).apply {
            duration = mDuration
            setTarget(mCurrentAngle)
            addUpdateListener {
                mCurrentAngle = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }
}
