package net.bi4vmr.study.threads

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import net.bi4vmr.aidl.IDownloadService3KT
import net.bi4vmr.aidl.callback.TaskCallbackKT
import net.bi4vmr.study.types.DownloadItemKT
import kotlin.concurrent.thread

/**
 * 示例服务：文件下载服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
class DownloadService3KT : Service() {

    companion object {
        private val TAG: String = "TestApp-Server-${DownloadService3KT::class.simpleName}"
    }

    override fun onBind(intent: Intent): IBinder {
        return DownloadImpl()
    }

    /**
     * AIDL接口的实现类。
     */
    private inner class DownloadImpl : IDownloadService3KT.Stub() {

        // 回调接口实现，用于向客户端反馈结果。
        private var callback: TaskCallbackKT? = null

        /**
         * 客户端注册回调接口。
         *
         * @param[cb] 回调接口实现。
         */
        override fun setTaskCallback(cb: TaskCallbackKT) {
            Log.d(TAG, "SetTaskCallback.")
            callback = cb
        }

        /**
         * 添加下载任务。
         *
         * 此方法为AIDL同步方法，执行时会阻塞客户端的调用线程，因此我们创建新线程进行耗时操作。
         *
         * @param[item] 下载任务。
         */
        override fun addTask(item: DownloadItemKT) {
            Log.d(TAG, "AddTask.")

            // 创建新线程模拟下载过程
            thread {
                val total = 100.0F
                var length = 0
                try {
                    Log.i(TAG, "开始下载:" + item.url)
                    while (length < total) {
                        length += 10
                        // 更新进度
                        item.percent = (length / total) * 100
                        // 并通过回调通知客户端
                        callback?.onStateChanged(item)
                        // 休眠1秒，模拟耗时操作。
                        Thread.sleep(1000)
                    }
                    Log.i(TAG, "下载完成。")
                } catch (e: InterruptedException) {
                    Log.w(TAG, "任务终止。")
                } catch (e: RemoteException) {
                    Log.e(TAG, "发生远程调用错误！")
                }
            }
        }

        /**
         * 添加下载任务。
         *
         * 此方法为AIDL异步方法，Binder将会自动分配线程执行，不会阻塞客户端的调用线程，因此可以直接进行耗时操作。
         *
         * @param[item] 下载任务。
         */
        override fun addTaskOneway(item: DownloadItemKT) {
            Log.d(TAG, "AddTaskOneway.")

            val total = 100.0F
            var length = 0
            try {
                Log.d(TAG, "开始下载:" + item.url)
                while (length < total) {
                    length += 10
                    // 更新进度
                    item.percent = (length / total) * 100
                    // 并通过回调通知客户端
                    callback?.onStateChanged(item)
                    // 休眠1秒，模拟耗时操作。
                    Thread.sleep(1000)
                }
                Log.d(TAG, "下载完成。")
            } catch (e: InterruptedException) {
                Log.w(TAG, "任务终止。")
            } catch (e: RemoteException) {
                Log.e(TAG, "发生远程调用错误！")
            }
        }
    }
}
