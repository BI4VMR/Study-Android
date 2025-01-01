package net.bi4vmr.study.androidvm;

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel

/**
 * 自定义ViewModel：AndroidViewModel。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class AndroidVMKT(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG: String = "TestApp-${AndroidVMKT::class.java.simpleName}"
    }

    init {
        // 获取应用级的Context对象
        val context: Context = application.applicationContext
        Log.i(TAG, "Get APPContext: $context")
    }
}
