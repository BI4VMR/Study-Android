package net.bi4vmr.study.viewpagerextend;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * TODO
 *
 * @author BI4VMR
 * @version 1.0
 */
public class ViewPagerNoScroll extends ViewPager {

    private boolean isScroll = false;

    public ViewPagerNoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPagerNoScroll(Context context) {
        super(context);
    }

    /**
     * dispatchTouchEvent()控制是否向子控件传递的事件，一般不进行修改，否则子控件无法接收事件。
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        com.test.Utils
//        Utils
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否拦截事件
     * 拦截后调用自身的onTouchEvent()方法处理事件，且不在向子控件传递；不拦截则传递给子控件。
     * 子控件是ViewGroup容器时有效
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    /**
     * 是否消费事件
     * 消费事件后事件结束，不消费则继续向父控件传递。
     * 子控件不是ViewGroup容器时，需要在此处消费事件，阻断其传递。
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //检测到触摸事件时调用performClick()方法。
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
        }
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void setScrollable(boolean scroll) {
        isScroll = scroll;
    }
}
