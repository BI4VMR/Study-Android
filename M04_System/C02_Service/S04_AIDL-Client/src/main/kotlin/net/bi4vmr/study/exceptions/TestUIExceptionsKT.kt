package net.bi4vmr.study.exceptions

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.aidl.IExceptions
import net.bi4vmr.study.databinding.TestuiExceptionsBinding

/**
 * 测试界面：自定义数据类型。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIExceptionsKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-Client-${TestUIExceptionsKT::class.simpleName}"
    }

    private val binding: TestuiExceptionsBinding by lazy {
        TestuiExceptionsBinding.inflate(layoutInflater)
    }

    private val connection: DLServiceConnection = DLServiceConnection()

    private var testService: IExceptions? = null

    private var isServiceConnected: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnBind.setOnClickListener { testBind() }
            btnUnbind.setOnClickListener { testUnbind() }
            btnDivide.setOnClickListener { testDivide() }
            btnDivide2.setOnClickListener { testDivide2() }
        }
    }

    private fun testBind() {
        appendLog("\n--- 绑定服务 ---\n")
        Log.i(TAG, "--- 绑定服务 ---")

        val cn = ComponentName(
            "net.bi4vmr.study.system.service.aidlserver",
            "net.bi4vmr.study.exceptions.ExceptionTestServiceKT"
        )
        val intent = Intent()
        intent.setComponent(cn)
        val result: Boolean = bindService(intent, connection, Context.BIND_AUTO_CREATE)
        appendLog("绑定结果：[$result]\n")
        Log.i(TAG, "绑定结果：[$result]")
    }

    private fun testUnbind() {
        appendLog("\n--- 解绑服务 ---\n")
        Log.i(TAG, "--- 解绑服务 ---")

        unbindService(connection)
        isServiceConnected = false
        testService = null
        binding.tvLog.append("连接已断开！\n")
        Log.i(TAG, "连接已断开！")
    }

    private fun testDivide() {
        appendLog("\n--- 计算除法 ---\n")
        Log.i(TAG, "--- 计算除法 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || testService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        runCatching {
            val result = requireNotNull(testService).divide(100, 0)
            appendLog("计算结果：$result")
            Log.i(TAG, "计算结果：$result")
        }.onFailure { e ->
            appendLog(e.message ?: "未知错误。")
            e.printStackTrace()
        }
    }

    private fun testDivide2() {
        appendLog("\n--- 计算除法2 ---\n")
        Log.i(TAG, "--- 计算除法2 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || testService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        runCatching {
            val result = requireNotNull(testService).divide2(100, 0)
            appendLog("计算结果：$result")
            Log.i(TAG, "计算结果：$result")
        }.onFailure { e ->
            appendLog(e.message ?: "未知错误。")
            e.printStackTrace()
        }
    }

    /**
     * 服务连接回调实现类。
     */
    private inner class DLServiceConnection : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            appendLog("连接已就绪。\n")
            Log.i(TAG, "连接已就绪。")

            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            testService = IExceptions.Stub.asInterface(service)
            // 将连接标记位置为 `true` ，此时可以进行远程调用。
            isServiceConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            appendLog("连接已断开！\n")
            Log.i(TAG, "连接已断开！")

            // 将连接标记位置为 `false`
            isServiceConnected = false
            // 将Service实例置空
            testService = null
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
