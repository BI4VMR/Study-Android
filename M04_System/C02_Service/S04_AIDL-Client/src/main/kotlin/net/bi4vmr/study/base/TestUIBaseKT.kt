package net.bi4vmr.study.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.aidl.IDownloadService
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-Client-${TestUIBaseKT::class.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    private val connection: DLServiceConnection = DLServiceConnection()
    private var downloadService: IDownloadService? = null

    private var isServiceConnected: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnBind.setOnClickListener { testBind() }
            btnUnbind.setOnClickListener { testUnbind() }
            btnGetPID.setOnClickListener { testGetPID() }
            btnAddTask.setOnClickListener { testAddTask() }
            btnGetTasks.setOnClickListener { testGetTasks() }
        }
    }

    private fun testBind() {
        appendLog("\n--- 绑定服务 ---\n")
        Log.i(TAG, "--- 绑定服务 ---")

        val intent = Intent().apply {
            setPackage("net.bi4vmr.study.system.service.aidlserver")
            action = "net.bi4vmr.aidl.DOWNLOAD_KT"
        }
        val result: Boolean = bindService(intent, connection, Context.BIND_AUTO_CREATE)
        appendLog("绑定结果：[$result]\n")
        Log.i(TAG, "绑定结果：[$result]")
    }

    private fun testUnbind() {
        appendLog("\n--- 解绑服务 ---\n")
        Log.i(TAG, "--- 解绑服务 ---")

        unbindService(connection)
        isServiceConnected = false
        downloadService = null
        binding.tvLog.append("连接已断开！\n")
        Log.i(TAG, "连接已断开！")
    }

    private fun testGetPID() {
        appendLog("\n--- 获取服务端进程ID ---\n")
        Log.i(TAG, "--- 获取服务端进程ID ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || downloadService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        try {
            val pid = requireNotNull(downloadService).pid
            appendLog("Server PID:[$pid]\n")
            Log.i(TAG, "Server PID:[$pid]")
        } catch (e: RemoteException) {
            appendLog(e.message ?: "未知错误。")
            e.printStackTrace()
        }
    }

    private fun testAddTask() {
        appendLog("\n--- 添加任务 ---\n")
        Log.i(TAG, "--- 添加任务 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || downloadService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        try {
            val task = "https://test.net/1.txt"
            requireNotNull(downloadService).addTask(task)
        } catch (e: RemoteException) {
            appendLog(e.message ?: "未知错误。")
            e.printStackTrace()
        }
    }

    private fun testGetTasks() {
        appendLog("\n--- 查询任务 ---\n")
        Log.i(TAG, "--- 查询任务 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || downloadService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        try {
            val tasks: List<String> = requireNotNull(downloadService).tasks
            appendLog(tasks.toString())
            Log.i(TAG, "$tasks")
        } catch (e: RemoteException) {
            appendLog(e.message ?: "未知错误。")
            e.printStackTrace()
        }
    }

    /**
     * 服务连接回调实现类。
     */
    private inner class DLServiceConnection : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            appendLog("OnServiceConnected.\n")
            Log.i(TAG, "OnServiceConnected.")
            appendLog("连接已就绪。\n")
            Log.i(TAG, "连接已就绪。")

            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService.Stub.asInterface(service)
            // 将连接标记位置为 `true` ，此时可以进行远程调用。
            isServiceConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            appendLog("OnServiceDisconnected.\n")
            Log.i(TAG, "OnServiceDisconnected.")
            appendLog("连接已断开！\n")
            Log.i(TAG, "连接已断开！")

            // 将连接标记位置为 `false`
            isServiceConnected = false
            // 将Service实例置空
            downloadService = null
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: CharSequence) {
        binding.tvLog.apply {
            append(text)
            post {
                val offset = layout.getLineTop(lineCount) - height
                if (offset > 0) {
                    scrollTo(0, offset)
                }
            }
        }
    }
}
