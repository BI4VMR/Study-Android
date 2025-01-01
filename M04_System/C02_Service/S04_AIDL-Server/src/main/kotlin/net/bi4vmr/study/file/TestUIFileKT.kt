package net.bi4vmr.study.file

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.aidl.IFileService
import net.bi4vmr.study.databinding.TestuiFileBinding
import net.bi4vmr.study.util.IOUtil
import java.io.File

/**
 * 测试界面：自定义数据类型。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIFileKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-Server-${TestUIFileKT::class.simpleName}"
    }

    private val binding: TestuiFileBinding by lazy {
        TestuiFileBinding.inflate(layoutInflater)
    }

    private val connection: DLServiceConnection = DLServiceConnection()

    private var testService: IFileService? = null

    private var isServiceConnected: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnBind.setOnClickListener { testBind() }
            btnUnbind.setOnClickListener { testUnbind() }
            btnUpload.setOnClickListener { testUpload() }
            btnDownload.setOnClickListener { testDownload() }
        }
    }

    private fun testBind() {
        appendLog("\n--- 绑定服务 ---\n")
        Log.i(TAG, "--- 绑定服务 ---")

        val intent = Intent(this, FileServiceKT::class.java)
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

    private fun testUpload() {
        appendLog("\n--- 上传文件 ---\n")
        Log.i(TAG, "--- 上传文件 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || testService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }


        // 创建测试文件
        val textFile = File(applicationContext.filesDir, "text.txt")
        IOUtil.writeFile(textFile, "Hello World!")

        runCatching {
            ParcelFileDescriptor.open(textFile, ParcelFileDescriptor.MODE_READ_ONLY).use { pfd ->
                // 通过远程服务将文件描述符传递给服务端，由服务端读取文件内容。
                requireNotNull(testService).upload(pfd)
            }
        }.onFailure { e ->
            e.printStackTrace()
        }
    }

    private fun testDownload() {
        appendLog("\n--- 下载文件 ---\n")
        Log.i(TAG, "--- 下载文件 ---")

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || testService?.asBinder()?.isBinderAlive != true) {
            appendLog("连接未就绪！\n")
            Log.i(TAG, "连接未就绪！")
            return
        }

        // 声明目标文件
        val file = File(applicationContext.filesDir.toString() + "/download.txt")

        runCatching {
            ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_WRITE_ONLY or ParcelFileDescriptor.MODE_CREATE)
                .use { pfd ->
                    // 将目标文件的描述符传递给服务端，由服务端写入数据。
                    requireNotNull(testService).download(pfd)

                    // 服务端写入数据完毕，读取文件检查结果。
                    val content = IOUtil.readFile(file)
                    appendLog("文件内容：$content")
                    Log.i(TAG, "文件内容：$content")
                }
        }.onFailure { e ->
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
            testService = IFileService.Stub.asInterface(service)
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
