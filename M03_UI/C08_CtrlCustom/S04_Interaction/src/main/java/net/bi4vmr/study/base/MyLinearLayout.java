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
        Log.i(TAG, "dispatchTouchEvent: " + ev);
        // if (ev.getAction() == MotionEvent.ACTION_DOWN) {
        //     return true;
        // }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent: " + ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event);
        return super.onTouchEvent(event);
    }
}
