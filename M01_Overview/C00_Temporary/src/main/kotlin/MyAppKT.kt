package net.bi4vmr.study

import android.app.Application
import android.util.Log
import net.bi4vmr.study.privacy.appops.AppOps
import net.bi4vmr.study.privacy.appops.AppOpsManagerExt

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

        val OPS_LOCATION: IntArray = intArrayOf(
            AppOps.COARSE_LOCATION.code,
            AppOps.FINE_LOCATION.code,
            AppOps.MONITOR_HIGH_POWER_LOCATION.code
        )
        val OPS_MIC: IntArray = intArrayOf(
            AppOps.RECORD_AUDIO.code,
            AppOps.PHONE_CALL_MICROPHONE.code,
            AppOps.RECEIVE_AMBIENT_TRIGGER_AUDIO.code,
            AppOps.RECEIVE_SANDBOX_TRIGGER_AUDIO.code,
            AppOps.RECEIVE_EXPLICIT_USER_INTERACTION_AUDIO.code
        )
        val OPS_CAMERA: IntArray = intArrayOf(
            AppOps.CAMERA.code,
            AppOps.PHONE_CALL_CAMERA.code
        )
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "OnCreate.")

        AppOpsManagerExt.getInstance(applicationContext)
            .getPackagesForOps(OPS_CAMERA)
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.i(TAG, "OnTerminate.")
    }
}
