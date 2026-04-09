package net.bi4vmr.study.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

/**
 * View：触摸事件测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyButton extends MaterialButton {

    private static final String TAG = "View";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
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
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "OnTouchEvent. Type:[" + event.getAction() + "]");
        boolean r = super.onTouchEvent(event);
        Log.i(TAG, "OnTouchEvent end, return:[" + r + "]");
        return r;
    }
}
