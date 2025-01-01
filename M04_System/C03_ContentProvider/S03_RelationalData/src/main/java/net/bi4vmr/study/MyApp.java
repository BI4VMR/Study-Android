package net.bi4vmr.study;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Name        : App
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-12-13 00:01
 * <p>
 * Description : 自定义Application。
 */
public class MyApp extends Application {

    private static final String TAG = "TestApp-" + MyApp.class.getSimpleName();

    // 通过静态全局变量为其他组件提供实例
    public static MyApp instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.i(TAG, "AttachBaseContext.");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Create.");
        instance = this;
    }
}
