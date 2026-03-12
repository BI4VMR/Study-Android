package net.bi4vmr.study.deviceinfo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiDeviceinfoBinding

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
class TestUIDeviceInfoKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIDeviceInfoKT::class.java.simpleName}"
    }

    private val binding:
            TestuiDeviceinfoBinding by lazy {
        TestuiDeviceinfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnHardwareInfo.setOnClickListener { testHardwareInfo() }
            btnSystemInfo.setOnClickListener { testSystemInfo() }
        }
    }

    private fun testHardwareInfo() {
        Log.i(TAG, "----- 硬件信息 -----")
        appendLog("\n----- 硬件信息 -----")

        // 示例代码...
    }

    private fun testSystemInfo() {
        Log.i(TAG, "----- 系统信息 -----")
        appendLog("\n----- 系统信息 -----")

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