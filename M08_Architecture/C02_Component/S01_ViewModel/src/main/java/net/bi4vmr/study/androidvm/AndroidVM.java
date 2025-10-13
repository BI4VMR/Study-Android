package net.bi4vmr.study.androidvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Name        : AndroidVM
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-12-21 23:18
 * <p>
 * Description : ViewModel：AndroidViewModel测试。
 */
public class AndroidVM extends AndroidViewModel {

    private static final String TAG = "TestApp-" + AndroidVM.class.getSimpleName();

    public AndroidVM(@NonNull Application application) {
        super(application);

        // 获取应用级的Context对象
        Context context = application.getApplicationContext();
        Log.i(TAG, "Get APPContext:" + context.toString());
    }
}
