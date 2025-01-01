package net.bi4vmr.study.callbacks

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestViewKT @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private val TAG: String = "TestApp-" + TestViewKT::class.java.simpleName
    }

    // 控件被加入窗口
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "OnAttachedToWindow.")
    }

    // 测量回调
    /**
     * onMeasure方法用于测量View的宽高。在自定义View中，我们需要重写这个方法，根据父View传入的测量规格（MeasureSpec）来计算自己的宽高。
     * 测量完毕后应当调用setMeasuredDimension(width, height);提交测量结果；如果没有调用该方法，则会抛出异常。
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "OnMeasure. widthMeasureSpec=$widthMeasureSpec, heightMeasureSpec=$heightMeasureSpec")
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        Log.d(TAG, "OnMeasure. widthMode=$widthMode, widthSize=$widthSize")
        Log.d(TAG, "OnMeasure. heightMode=$heightMode, heightSize=$heightSize")
    }

    // 尺寸改变回调
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "onSizeChanged. w=$w, h=$h, oldw=$oldw, oldh=$oldh")
    }

    /**
     * onLayout方法用于确定View的位置。在自定义ViewGroup中，我们需要重写这个方法，根据子View的测量宽高来确定它们的位置。
     *
     * View内部没有子控件，因此没有什么可Layout的，该方法默认为空实现。
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout. changed=$changed, left=$left, top=$top, right=$right, bottom=$bottom")
    }

    // 绘制内容
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDetachedFromWindow: ")
    }


    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "OnDetachedFromWindow.")
    }
}
