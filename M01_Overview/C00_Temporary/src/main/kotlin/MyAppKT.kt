package net.bi4vmr.study

import android.app.Application
import android.util.Log

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author 詹屹罡。
 */
class MyAppKT : Application() {

    companion object {
        private val TAG = "TestApp-${MyAppKT::class.java.simpleName}"
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "OnCreate.")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.i(TAG, "OnTerminate.")
    }
}
