package net.bi4vmr.study.base

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnStart.setOnClickListener { testStart() }
            btnEnd.setOnClickListener { testStop() }
        }
    }

    private fun testStart() {
        Log.i(TAG, "--- 启动服务 ---")
        appendLog("\n--- 启动服务 ---\n")

        // 指明目标服务
        val intent = Intent(this, DownloadServiceKT::class.java)
        // 添加初始化数据
        intent.putExtra("LINK", "https://dl.test.com/file")
        // 启动服务
        val serviceInfo: ComponentName? = startService(intent)
        Log.i(TAG, "服务名称：$serviceInfo")
        appendLog("服务名称：$serviceInfo")


        // 指明目标服务（通过ComponentName）
        // val cn = ComponentName(
        //     "net.bi4vmr.study.system.service.startservice",
        //     "net.bi4vmr.study.base.DownloadService"
        // )
        // val intent2 = Intent()
        // intent2.setComponent(cn)

        // 指明目标服务（通过Action与Category等）
        // val intent3 = Intent().apply {
        //     setPackage("net.bi4vmr.study.system.service.startservice")
        //     setAction("net.bi4vmr.action.DOWNLOAD")
        //     addCategory("net.bi4vmr.category.DOWNLOAD")
        // }
    }

    private fun testStop() {
        Log.i(TAG, "--- 停止服务 ---")
        appendLog("\n--- 停止服务 ---\n")

        val intent = Intent(this, DownloadServiceKT::class.java)
        val isSuccess = stopService(intent)
        if (isSuccess) {
            Log.i(TAG, "服务已被停止。")
            appendLog("服务已被停止。\n")
        } else {
            Log.e(TAG, "服务停止失败！")
            appendLog("服务停止失败！\n")
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
