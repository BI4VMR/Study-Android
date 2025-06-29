package net.bi4vmr.study.types

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import net.bi4vmr.aidl.IDownloadService2KT

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class DownloadService2KT : Service() {

    companion object {
        private val TAG: String = "TestApp-Server-${DownloadService2KT::class.simpleName}"
    }

    override fun onBind(intent: Intent): IBinder {
        return DownloadImpl()
    }

    /**
     * AIDL接口的实现类。
     */
    private inner class DownloadImpl : IDownloadService2KT.Stub() {

        // 任务集合
        private val tasks: MutableList<DownloadItemKT> = mutableListOf()

        /**
         * 添加下载任务。
         *
         * @param[item] 下载项实体类。
         */
        override fun addTask(item: DownloadItemKT) {
            Log.d(TAG, "AddTask. Item:$item")
            // 在此处实现下载业务，此处省略。
            tasks.add(item)
        }

        /**
         * 查询下载任务。
         *
         * @return 任务列表。
         */
        override fun getTasks(): List<DownloadItemKT> {
            Log.d(TAG, "GetTasks.")
            return tasks
        }
    }
}
