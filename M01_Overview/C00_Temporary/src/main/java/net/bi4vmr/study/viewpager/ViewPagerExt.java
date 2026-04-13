package net.bi4vmr.study.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * ViewPager扩展控件。
 *
 * @author BI4VMR
 * @version 1.0
 */
public class ViewPagerExt extends ViewPager {

    private boolean scrollable = true;

    public ViewPagerExt(Context context) {
        this(context, null);
    }

    public ViewPagerExt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (scrollable) {
            // 允许滚动时，执行原有的逻辑。
            return super.onInterceptTouchEvent(ev);
        } else {
            // 禁止滚动时，不再拦截事件，透传给子控件，确保它们能够被正常点击。
            return false;
        }
    }

    // ViewPager无需处理点击事件，因此可以忽略警告。
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollable) {
            // 允许滚动时，执行原有的逻辑。
            return super.onTouchEvent(ev);
        } else {
            // 禁止滚动时，什么都不做。
            return false;
        }
    }

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean enable) {
        this.scrollable = enable;
    }
}
