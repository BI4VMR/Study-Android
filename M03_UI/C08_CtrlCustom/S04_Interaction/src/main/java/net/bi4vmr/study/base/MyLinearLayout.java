package net.bi4vmr.study.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * ViewGroup：触摸事件测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyLinearLayout extends LinearLayout {

    private static final String TAG = "ViewGroup";

    public MyLinearLayout(android.content.Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "DispatchTouchEvent. Type:[" + ev.getAction() + "]");
        boolean r = super.dispatchTouchEvent(ev);
        Log.i(TAG, "DispatchTouchEvent end, return:[" + r + "]");
        return r;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "OnInterceptTouchEvent. Type:[" + ev.getAction() + "]");
        boolean r = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "OnInterceptTouchEvent end, return:[" + r + "]");
        return r;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "OnTouchEvent. Type:[" + event.getAction() + "]");
        boolean r = super.onTouchEvent(event);
        Log.i(TAG, "OnTouchEvent end, return:[" + r + "]");
        return r;
    }
}
