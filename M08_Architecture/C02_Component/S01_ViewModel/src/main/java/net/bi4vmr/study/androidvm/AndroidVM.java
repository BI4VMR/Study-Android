package net.bi4vmr.study.androidvm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * 自定义ViewModel：AndroidViewModel。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class AndroidVM extends AndroidViewModel {

    private static final String TAG = "TestApp-" + AndroidVM.class.getSimpleName();

    public AndroidVM(@NonNull Application application) {
        super(application);

        // 获取应用级的Context对象
        Context context = application.getApplicationContext();
        Log.i(TAG, "Get APPContext: " + context.toString());
    }
}
