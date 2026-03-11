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
        private val TAG: String = "TestApp-Server-${TestUIBaseKT::class.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    /**
     * 服务连接状态回调，用于监听服务状态与获取Binder接口实现。
     */
    private val connection: DLServiceConnection = DLServiceConnection()

    /**
     * Binder接口实现，用于调用服务端提供的远程方法。
     * <p>
     * 变量为空表示服务未就绪或已断开，变量非空表示服务已连接，但服务进程不一定可用，还需要通过 `isBinderAlive()` 方法检测服务进程状态
     * 后才能调用其中的方法。
     */
    private var downloadService: IDownloadService? = null

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
        Log.i(TAG, "----- 绑定服务 -----")
        appendLog("\n----- 绑定服务 -----")

        // 通过Intent指明目标服务
        val intent = Intent(this, DownloadServiceKT::class.java)

        // 绑定服务，参数依次为目标服务Intent、连接状态回调实现和选项。
        val result: Boolean = bindService(intent, connection, Context.BIND_AUTO_CREATE)

        /*
         * 绑定方法返回 `true` 表示当前进程可绑定目标服务，连接状态需要从回调获取；返回 `false` 表示服务不可用，原因包括：服务不存在、当前
         * 软件包没有权限与目标软件包交互等。
         */
        Log.i(TAG, "当前进程可绑定目标服务？：[$result]")
        appendLog("当前进程可绑定目标服务？：[$result]")
    }

    private fun testUnbind() {
        Log.i(TAG, "----- 解绑服务 -----")
        appendLog("\n----- 解绑服务 -----")

        // 解绑服务
        unbindService(connection)

        // 将本地Binder引用置空，标记其不再可用。
        downloadService = null

        Log.i(TAG, "连接已断开！")
        appendLog("连接已断开！")
    }

    private fun testGetPID() {
        Log.i(TAG, "----- 获取服务端进程ID -----")
        appendLog("\n----- 获取服务端进程ID -----")

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            Log.i(TAG, "连接未就绪！")
            appendLog("连接未就绪！")
            return
        }

        try {
            val pid = requireNotNull(downloadService).pid
            Log.i(TAG, "Server PID:[$pid]")
            appendLog("Server PID:[$pid]")
        } catch (e: RemoteException) {
            e.printStackTrace()
            appendLog(e.message ?: "未知错误。")
        }
    }

    private fun testAddTask() {
        appendLog("\n--- 添加任务 ---\n")
        Log.i(TAG, "--- 添加任务 ---")

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            Log.i(TAG, "连接未就绪！")
            appendLog("连接未就绪！")
            return
        }

        try {
            val task = "https://test.net/1.txt"
            requireNotNull(downloadService).addTask(task)
        } catch (e: RemoteException) {
            e.printStackTrace()
            appendLog(e.message ?: "未知错误。")
        }
    }

    private fun testGetTasks() {
        appendLog("\n----- 查询任务 -----")
        Log.i(TAG, "----- 查询任务 -----")

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            Log.i(TAG, "连接未就绪！")
            appendLog("连接未就绪！")
            return
        }

        try {
            val tasks: List<String> = requireNotNull(downloadService).tasks
            Log.i(TAG, "$tasks")
            appendLog(tasks.toString())
        } catch (e: RemoteException) {
            e.printStackTrace()
            appendLog(e.message ?: "未知错误。")
        }
    }

    /**
     * 服务连接回调实现类。
     */
    private inner class DLServiceConnection : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i(TAG, "连接已就绪。")
            appendLog("连接已就绪。")

            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.i(TAG, "连接已断开！")
            appendLog("连接已断开！")

            // 将Service实例置空
            downloadService = null
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: Any) {
        binding.tvLog.apply {
            post { append("\n$text") }
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
