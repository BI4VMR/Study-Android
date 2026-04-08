package net.bi4vmr.study.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

/**
 * View：触摸事件测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyButton extends AppCompatButton {

    private static final String TAG = "View";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: " + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: " + event);
        return false;
    }
}
