package net.bi4vmr.study.androidvm;

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel

/**
 * Name        : AndroidVMKT
 *
 * Author      : BI4VMR
 *
 * Email       : bi4vmr@outlook.com
 *
 * Date        : 2023-05-11 21:10
 *
 * Description : ViewModel：AndroidViewModel测试。
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
