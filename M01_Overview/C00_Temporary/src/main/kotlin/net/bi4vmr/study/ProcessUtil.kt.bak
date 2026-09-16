package net.bi4vmr.study

import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * 进程相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
object ProcessUtil {

    fun getMainPID(context: Context): Int {
        var mainPID = -1
        val am: ActivityManager = context.getSystemService<ActivityManager>()
        // 遍历当前应用的所有进程，查找不带后缀的进程。
        for (processInfo in am.runningAppProcesses) {
            if (processInfo.processName == context.packageName) {
                mainPID = processInfo.pid
                break
            }
        }

        return mainPID
    }

    fun getPID(): Int = Process.myPid()
}
