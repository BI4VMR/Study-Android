package net.bi4vmr.study.datetime

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import net.bi4vmr.study.databinding.TestuiDatetimeBinding
import androidx.appcompat.app.AppCompatActivity

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
class TestUIDateTimeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIDateTimeKT::class.java.simpleName}"
    }

    private val binding: TestuiDatetimeBinding by lazy {
        TestuiDatetimeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnTimeInfo.setOnClickListener { testTimeInfo() }
        }
    }

    private fun testTimeInfo() {
        Log.i(TAG, "----- 时间信息 -----")
        appendLog("\n----- 时间信息 -----")

        // 示例代码...
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