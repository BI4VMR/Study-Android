package net.bi4vmr.study.privacy.util

import android.app.AppOpsManager
import java.lang.reflect.Method

/**
 * AppOpsManager功能扩展。
 *
 * @author bi4vmr@outlook.com
 */
object AppOpsManagerExt {

    @JvmStatic
    fun getPackagesForOps(ops: Array<String>?) {
        try {
            val method: Method = AppOpsManager::class.java.getMethod("getPackagesForOps")
            method.isAccessible = true
            // val  method.invoke(null, ops) as? List<*>
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun s(){
        val c = AppOpsManager::class.java.declaringClass.simpleName
        val PackageOpsClass = Class.forName("android.app.AppOpsManager$PackageOps")
    }
}
