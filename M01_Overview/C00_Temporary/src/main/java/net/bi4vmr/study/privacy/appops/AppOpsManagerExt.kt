package net.bi4vmr.study.privacy.appops

import android.annotation.SuppressLint
import android.app.AppOpsManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.lang.reflect.Method
import java.util.concurrent.Executors

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
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

    private val opsManagerClass = AppOpsManager::class.java
    private val opsManager: AppOpsManager = mContext.getSystemService(opsManagerClass)

    // @RequiresPermission("Manifest.permission.GET_APP_OPS_STATS")
    @RequiresApi(Build.VERSION_CODES.R)
    fun getPackagesForOps(ops: IntArray? = null) {
        try {
            val method: Method = opsManagerClass.getMethod("getPackagesForOps", IntArray::class.java)
            // 获取应用程序的近期操作，返回值类型为List<AppOpsManager.PackageOps>，AppOpsManager.PackageOps是隐藏API。
            val packageOPList: List<*> = method.invoke(opsManager, ops) as List<*>
            packageOPList.forEach { pkgOP ->
                if (pkgOP != null) {
                    Log.i(TAG, "PackageOps ---------------->")
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

            val OPS_LOCATION: Array<String> = arrayOf(
                AppOps.COARSE_LOCATION.name,
                AppOps.FINE_LOCATION.name,
                AppOps.MONITOR_HIGH_POWER_LOCATION.name
            )
            Log.i(TAG, "startWatchingActive.")
            opsManager.startWatchingActive(OPS_LOCATION,Executors.newSingleThreadExecutor()
            ) { op, uid, packageName, active -> Log.i(TAG, "onOpActiveChanged. op:[$op] [$packageName] a:[$active]") }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
