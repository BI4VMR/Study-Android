package net.bi4vmr.study.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.util.Log
import net.bi4vmr.aidl.IDownloadService

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class DownloadServiceKT : Service() {

    companion object {
        private val TAG: String = "TestApp-Server-${DownloadServiceKT::class.simpleName}"
    }

    override fun onBind(intent: Intent): IBinder {
        return DownloadImpl()
    }

    /**
     * AIDL接口的实现类。
     */
    private inner class DownloadImpl : IDownloadService.Stub() {

        // 任务集合
        private val tasks: MutableList<String> = mutableListOf()

        /**
         * 获取服务端进程ID。
         *
         * @return 服务端的进程ID
         */
        override fun getPID(): Int {
            val pid = Process.myPid()
            Log.d(TAG, "GetPID. PID:[$pid]")
            return pid
        }

        /**
         * 添加下载任务。
         *
         * @param url 目标地址。
         */
        override fun addTask(url: String) {
            Log.d(TAG, "AddTask. URL:[$url]")
            // 在此处实现下载业务，此处省略。
            tasks.add(url)
        }

        /**
         * 查询下载任务。
         *
         * @return 任务列表。
         */
        override fun getTasks(): List<String> {
            Log.d(TAG, "GetTasks.")
            return tasks
        }
    }
}
