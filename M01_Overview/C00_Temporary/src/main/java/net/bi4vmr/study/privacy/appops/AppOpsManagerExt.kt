package net.bi4vmr.study.privacy.appops

import android.annotation.SuppressLint
import android.app.AppOpsManager
import android.content.Context
import android.util.Log
import java.lang.reflect.Method

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author 詹屹罡。
 */
class AppOpsManagerExt private constructor(private val mContext: Context) {

    companion object {
        // 实例的生命周期跟随整个进程，不会导致内存泄露，可以忽略警告。
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AppOpsManagerExt? = null

        @JvmStatic
        fun getInstance(context: Context): AppOpsManagerExt {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = AppOpsManagerExt(context.applicationContext)
                    }
                }
            }
            return instance!!
        }

        private val TAG = "TestApp-${AppOpsManagerExt::class.java.simpleName}"
    }

    private val mOpsManagerClass = AppOpsManager::class.java
    private val mOpsManager: AppOpsManager = mContext.getSystemService(mOpsManagerClass)

    // @RequiresPermission("Manifest.permission.GET_APP_OPS_STATS")
    // fun getPackagesForOps(ops: IntArray?) {
    //     val OPS_MIC_CAMERA: IntArray = intArrayOf(
    //         AppOps.CAMERA.code,
    //         AppOps.PHONE_CALL_CAMERA.code,
    //         AppOps.RECORD_AUDIO.code,
    //         AppOps.PHONE_CALL_MICROPHONE.code,
    //         AppOps.RECEIVE_AMBIENT_TRIGGER_AUDIO.code,
    //         AppOps.RECEIVE_SANDBOX_TRIGGER_AUDIO.code,
    //         AppOps.RECEIVE_EXPLICIT_USER_INTERACTION_AUDIO.code
    //     )
    //     val OPS_LOCATION: IntArray = intArrayOf(
    //         AppOps.COARSE_LOCATION.code,
    //         AppOps.FINE_LOCATION.code,
    //         AppOps.MONITOR_HIGH_POWER_LOCATION.code
    //     )
    //
    //     val pOps: List<AppOpsManager.PackageOps> = mOpsManager.getPackagesForOps(OPS_MIC_CAMERA)
    //     for (pOp in pOps) {
    //         // 获取当前记录对应的包名
    //         val pkgName: String = pOp.getPackageName()
    //         Log.i(TAG, "pkgName:[$pkgName]")
    //         val entrys: List<AppOpsManager.OpEntry> = pOp.getOps()
    //
    //         // 遍历OpEntry集合
    //         entrys.forEach {
    //             val opCode: Int = it.getOp()
    //             val isRunning: Boolean = it.isRunning()
    //             Log.i(TAG, "isRunning:[$isRunning] opCode:[$opCode] op:[${AppOps.valueOf(opCode)}]")
    //         }
    //     }
    // }

    fun getPackagesForOps(ops: IntArray? = null) {
        try {
            val method: Method = mOpsManagerClass.getMethod("getPackagesForOps", IntArray::class.java)
            // 获取应用程序的近期操作，返回值类型为List<AppOpsManager.PackageOps>，AppOpsManager.PackageOps是隐藏API。
            val packageOPList: List<*> = method.invoke(mOpsManager, ops) as List<*>
            packageOPList.forEach { pkgOP ->
                if (pkgOP != null) {
                    Log.i(TAG, "PackageOps ---------------->")
                    // val methods = pkgOP.javaClass.methods
                    // methods.forEach {
                    //     Log.i(TAG, "pkgOP func:[${it!!.name}]")
                    // }
                    // 获取包名
                    val methodGetPkgName = pkgOP.javaClass.getMethod("getPackageName")
                    val packageName: String = methodGetPkgName.invoke(pkgOP) as String
                    // 获取UID
                    val methodGetUID = pkgOP.javaClass.getMethod("getUid")
                    val uid: Int = methodGetUID.invoke(pkgOP) as Int
                    Log.i(TAG, "Package:[$packageName] UID:[$uid]")
                    // 获取OP列表
                    val methodGetOps = pkgOP.javaClass.getMethod("getOps")
                    val opList: List<*> = methodGetOps.invoke(pkgOP) as List<*>
                    opList.forEach { entry ->
                        if (entry != null) {
                            Log.i(TAG, "OPEntry ---------------->")
                            // val methods = entry.javaClass.methods
                            // methods.forEach {
                            //     Log.i(TAG, "opentries func:[${it!!.name}]")
                            // }
                            // 获取OP代码
                            val methodGetOP = entry.javaClass.getMethod("getOp")
                            val opCode: Int = methodGetOP.invoke(entry) as Int
                            // 获取当前是否正在运行
                            val methodIsRunning = entry.javaClass.getMethod("isRunning")
                            val running: Boolean = methodIsRunning.invoke(entry) as Boolean
                            Log.i(TAG, "OPCode:[$opCode] OP:[${AppOps.valueOf(opCode)}] Running:[$running]")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
