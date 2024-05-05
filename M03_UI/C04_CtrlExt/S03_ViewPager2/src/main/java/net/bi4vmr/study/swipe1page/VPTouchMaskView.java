package net.bi4vmr.study.swipe1page;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ViewPager触摸遮罩控件。
 * <p>
 * 用户可以在ViewPager仍在滑动状态（"onPageScrollStateChanged()"状态不为"SCROLL_STATE_IDLE"）时再次触摸屏幕继续滑动，这在短视频
 * 播放器、复杂转场动效等场景下会导致诸多问题。我们可以通过该控件限制ViewPager每次触摸最多仅能翻一页，禁止连续滑动翻页。
 * <p>
 * 该控件覆盖在ViewPager上层，并保存ViewPager的滑动状态，如果前一次滑动仍未结束，则消耗新到达的触摸事件，实现每次滑动只能翻一页的效果。
 *
 * @author BI4VMR
 */
public class VPTouchMaskView extends View {

    // ViewPager滑动状态
    private boolean vpScrolling = false;

    public VPTouchMaskView(Context context) {
        this(context, null);
    }

    public VPTouchMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (vpScrolling) {
            // ViewPager滑动未结束时，该View消耗新到达的触摸事件，不再分发给下层的ViewPager。
            return true;
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

    /**
     * 更新ViewPager的滑动状态。
     *
     * @param state ViewPager的"SCROLL_STATE"系列常量。
     */
    public void updateVPScrollState(int state) {
        // 当ViewPager滑动状态不为SCROLL_STATE_IDLE(0)时，说明滑动未终止。
        vpScrolling = (state != 0);
    }
}
