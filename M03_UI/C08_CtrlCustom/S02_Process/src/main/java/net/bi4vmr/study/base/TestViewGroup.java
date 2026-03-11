package net.bi4vmr.study.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

/**
 * TODO 添加简述。
 * <p>
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestViewGroup extends FrameLayout {

    public TestViewGroup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.i("1", "DispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
