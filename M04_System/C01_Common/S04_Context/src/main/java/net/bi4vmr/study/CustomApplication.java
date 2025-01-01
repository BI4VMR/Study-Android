package net.bi4vmr.study;

import android.app.Application;
import android.util.Log;

/**
 * Name        : CustomApplication
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-06-29 15:21
 * <p>
 * Description : 自定义Application。
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("myapp", "OnCreate.");
    }
}
