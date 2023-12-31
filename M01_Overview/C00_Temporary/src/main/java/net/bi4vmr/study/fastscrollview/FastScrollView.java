package net.bi4vmr.study.fastscrollview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Name        : FastScrollView
 * <p>
 * Date        : 2023-08-30 10:22
 * <p>
 * Description : 支持拖拽滚动条进行翻页的ScrollView实现。
 * <p>
 * <a href="https://blog.csdn.net/zzmzzff/article/details/119722144">来源</a>
 */
public class FastScrollView extends ScrollView {

    private final String TAG = FastScrollView.class.getSimpleName();

    private final Rect mBarRect = new Rect();
    private int mScrollExWidth;
    private int mScrollExHeight;
    private boolean mScrollStart;
    private int dx, dy;
    private int mRightPadding;

    public FastScrollView(Context context) {
        super(context);
        init();
    }

    public FastScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FastScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollExWidth = dip2px(30); // 扩展拖动区域宽度，可自行修改
        mScrollExHeight = dip2px(50); // 扩展拖动区域高度，可自行修改
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View childView = getChildView();
        if (childView == null) {
            Log.w(TAG, "Child view is null!");
            return super.dispatchTouchEvent(ev);
        }

        switch (ev.getAction() & ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                // Log.e("fly", "down");
                mScrollStart = false;
                if (canScroll()) {
                    dx = (int) ev.getX();
                    dy = (int) ev.getY();
                    float scrollPos = (float) getHeight() / childView.getHeight();
                    int barHeight = (int) (scrollPos * getHeight());
                    // Log.e("fly", scrollPos+", "+ barHeight);
                    mBarRect.left = getWidth() - mRightPadding - getVerticalScrollbarWidth();
                    mBarRect.right = getWidth() - mRightPadding;
                    mBarRect.top = (int) (getScrollY() * scrollPos);
                    mBarRect.bottom = mBarRect.top + barHeight;
                    if (dx >= mBarRect.left - mScrollExWidth
                            && dx < mBarRect.right + mScrollExWidth
                            && dy >= mBarRect.top - mScrollExHeight
                            && dy < mBarRect.bottom + mScrollExHeight) {
                        // Log.e("fly", "mScrollStart");
                        mScrollStart = true;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mScrollStart) {
                    int offsetY = (int) ev.getY() - dy;
                    // Log.e("fly", "move: " + ev.getY());
                    int top = mBarRect.top + offsetY;
                    float scrollPos = (float) top / getHeight();
                    int scrollY = (int) (scrollPos * childView.getHeight());
                    if (scrollY < 0) {
                        scrollY = 0;
                    }
                    if (scrollY > childView.getHeight() - getHeight()) {
                        scrollY = childView.getHeight() - getHeight();
                    }
                    setScrollY(scrollY);
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean canScroll() {
        View child = getChildView();
        return (child != null) && (child.getHeight() > getHeight());
    }

    private View getChildView() {
        if (getChildCount() > 0) {
            return getChildAt(0);
        } else {
            return null;
        }
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 这里不用view的paddding，用于预留特殊空隙
     */
    public void setRightPadding(int mRightPadding) {
        this.mRightPadding = mRightPadding;
    }
}
