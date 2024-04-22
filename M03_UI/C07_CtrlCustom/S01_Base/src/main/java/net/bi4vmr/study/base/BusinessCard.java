package net.bi4vmr.study.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Name        : BusinessCard
 * <p>
 * Author      : 詹屹罡
 * <p>
 * Email       : yigangzhan@pateo.com
 * <p>
 * Date        : 2024-04-22 17:22
 * <p>
 * Description : TODO 添加描述
 */
public class BusinessCard extends FrameLayout {

    private final String TAG = BusinessCard.class.getSimpleName();

    /**
     * Name        : 构造方法1
     * <p>
     * Description : 该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param context 上下文环境。
     */
    public BusinessCard(@NonNull Context context) {
        super(context);
        Log.d(TAG, "1");
    }

    /**
     * Name        : 构造方法1
     * <p>
     * Description : 如果我们需要在布局文件中引用控件。该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param context 上下文环境。
     */
    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "2");
    }

    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "3");
    }

    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "4");
    }
}
