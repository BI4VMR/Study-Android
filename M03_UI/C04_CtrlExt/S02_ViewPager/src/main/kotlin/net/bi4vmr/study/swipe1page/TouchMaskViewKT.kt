package net.bi4vmr.study.swipe1page

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager2.widget.ViewPager2

/**
 * Name        : TouchMaskView
 *
 * Author      : 詹屹罡
 *
 * Email       : yigangzhan@pateo.com
 *
 * Date        : 2024-04-22 10:17
 *
 * Description : 自定义控件 - 触摸遮罩。
 *
 * ViewPager2可以在前一次滑动没有结束（"onPageScrollStateChanged()"状态为"0"）时再次触发滑动，目前的OpenGL滑动渐变特效方案中仅在滑
 * 动结束时触发绘制，ViewPager2连续滑动时OpenGL并未触发后续图片的绘制动作，会导致背景为黑色。
 *
 * 该控件覆盖在ViewPager2上层，并保存ViewPager2的滑动状态，如果前一次滑动未结束，则消耗掉新到达的触摸事件，实现每次滑动只能翻一页的效果。
 */
class TouchMaskViewKT : View {

    companion object {
        private val TAG = TouchMaskViewKT::class.java.simpleName
    }

    private var vp2InScrolling: Boolean = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : super(context, attr)

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        return if (vp2InScrolling) {
            // 消耗事件，不再分发给下层的ViewPager2。
            true
        } else {
            super.dispatchTouchEvent(event)
        }
    }

    /**
     * Name        : 更新ViewPager2的滑动状态
     *
     * Description : 更新ViewPager2的滑动状态。
     *
     * @param state ViewPager2的"SCROLL_STATE_X"系列常量。
     */
    fun updateVP2ScrollState(state: Int) {
        // 滑动状态不为IDLE时，说明正在滑动。
        vp2InScrolling = (state != ViewPager2.SCROLL_STATE_IDLE)
    }
}
