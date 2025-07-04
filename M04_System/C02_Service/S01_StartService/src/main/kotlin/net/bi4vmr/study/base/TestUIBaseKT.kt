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
        val intent = Intent(this, DownloadService::class.java)
        // 添加初始化数据
        intent.putExtra("LINK", "https://dl.test.com/file")
        // 启动服务
        val service: ComponentName? = startService(intent)
        Log.i(TAG, "组件名：$service")
    }

    private fun testStop() {
        Log.i(TAG, "--- 停止服务 ---")
        appendLog("\n--- 停止服务 ---\n")

        val intent = Intent(this, DownloadService::class.java)
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
                val offset = layout.getLineTop(lineCount) - height
                if (offset > 0) {
                    scrollTo(0, offset)
                }
            }
        }
    }
}
