package net.bi4vmr.study.base

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    /**
     * 服务连接回调实现。
     *
     * 用于接收服务连接成功、连接断开事件，并获取Binder实例。
     */
    private val connection: ServiceConnection = MyConnectionCallback()

    // Binder实例
    private var binder: DownloadServiceKT.DownloadImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnBind.setOnClickListener { _ -> testBind() }
            btnUnbind.setOnClickListener { _ -> testUnbind() }
            btnAddListener.setOnClickListener { _ -> testSetListener() }
            btnAddTask.setOnClickListener { _ -> testAddTask() }
        }
    }

    private fun testBind() {
        Log.i(TAG, "--- 绑定服务 ---")
        appendLog("\n--- 绑定服务 ---\n")

        val intent = Intent(this, DownloadServiceKT::class.java)
        val result = bindService(intent, connection, BIND_AUTO_CREATE)
        Log.i(TAG, "服务存在？：$result")
        appendLog("服务存在？：$result\n")
    }

    private fun testUnbind() {
        Log.i(TAG, "--- 解绑服务 ---")
        appendLog("\n--- 解绑服务 ---\n")

        // 仅当Binder实例不为空时才进行解绑操作
        if (binder != null) {
            unbindService(connection)
            // 连接已断开，将全局变量置空。
            binder = null
        }
    }

    private fun testSetListener() {
        Log.i(TAG, "--- 注册监听器 ---")
        appendLog("\n--- 注册监听器 ---\n")

        // 判断连接是否就绪
        if (binder == null) {
            Log.e(TAG, "--- 连接未就绪！ ---")
            appendLog("--- 连接未就绪！ ---\n")
            return
        }

        requireNotNull(binder).setCallback { percent: Double ->
            Log.i(TAG, "进度变化：$percent")
            appendLog("进度变化：$percent\n")
        }
    }

    private fun testAddTask() {
        Log.i(TAG, "--- 添加任务 ---")
        appendLog("\n--- 添加任务 ---\n")

        // 判断连接是否就绪
        if (binder == null) {
            Log.e(TAG, "--- 连接未就绪！ ---")
            appendLog("--- 连接未就绪！ ---\n")
            return
        }

        requireNotNull(binder).addTask("https://dl.test.com/file")
    }

    /**
     * 服务连接回调实现类
     */
    private inner class MyConnectionCallback : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.i(TAG, "OnServiceConnected.")
            appendLog("OnServiceConnected.\n")

            // 获取Binder实例，将其转为Service中的业务类类型，并保存至全局变量。
            binder = service as DownloadServiceKT.DownloadImpl
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.i(TAG, "OnServiceDisconnected.")
            appendLog("OnServiceDisconnected.\n")

            // 连接已断开，将全局变量置空。
            binder = null
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: CharSequence) {
        binding.tvLog.apply {
            append(text)
            post {
                runCatching {
                    val offset = layout.getLineTop(lineCount) - height
                    if (offset > 0) {
                        scrollTo(0, offset)
                    }
                }.onFailure { e ->
                    Log.w(TAG, "TextView scroll failed!")
                    e.printStackTrace()
                }
            }
        }
    }
}
