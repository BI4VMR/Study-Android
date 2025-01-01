package net.bi4vmr.study.base

import android.os.Bundle
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
        private val TAG: String = "TestApp-" + TestUIBaseKT::class.java.simpleName
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnLog.setOnClickListener { testLog() }
            btnJavaLog.setOnClickListener { testJavaLog() }
            btnChatty.setOnClickListener { testChatty() }
            btnLongLine.setOnClickListener { testLongLine() }
        }
    }

    // 输出日志
    private fun testLog() {
        Log.i(TAG, "--- 输出日志 ---")
        binding.tvLog.append("\n--- 输出日志 ---\n")

        // 输出Verbose级别日志
        Log.v(TAG, "Verbose Log.")
        // 输出Debug级别日志
        Log.d(TAG, "Debug Log.")
        // 输出Info级别日志
        Log.i(TAG, "Info Log.")
        // 输出Warn级别日志
        Log.w(TAG, "Warn Log.")
        // 输出Error级别日志
        Log.e(TAG, "Error Log.")
    }

    // Java日志兼容性
    private fun testJavaLog() {
        Log.i(TAG, "--- Java日志兼容性 ---")
        binding.tvLog.append("\n--- Java日志兼容性 ---\n")

        // 输出标准信息
        println("标准信息输出测试。")
        // 输出错误信息
        System.err.println("错误信息输出测试。")
    }

    // Chatty机制
    private fun testChatty() {
        Log.i(TAG, "--- Chatty机制 ---")
        binding.tvLog.append("\n--- Chatty机制 ---\n")

        for (i in 0 until 100) {
            Log.i(TAG, "Chatty机制测试内容。")
        }
    }

    // 输出超长内容
    private fun testLongLine() {
        Log.i(TAG, "--- 输出超长内容 ---")
        binding.tvLog.append("\n--- 输出超长内容 ---\n")

        // 测试数据
        val stringBuilder = StringBuilder()
        for (i in 1..1000) {
            stringBuilder.append("日志内容")
        }
        val input: String = stringBuilder.toString()

        // 每行最大长度
        val lineLength = 1000

        if (input.length <= lineLength) {
            Log.i(TAG, input)
            return
        }

        // 计算切分后的行数（不含最后一行）
        val lines: Int = input.length / lineLength
        // 循环打印每一行内容
        for (i in 0..lines) {
            if (i != lines) {
                /* 打印完整的行 */
                val line: String = input.substring(i * lineLength, (i + 1) * lineLength)
                Log.i(TAG, "Line:[${i + 1}] Text:[$line]")
            } else {
                /* 打印最后一行 */
                val line: String = input.substring(i * lineLength)
                if (line.isNotEmpty()) {
                    Log.i(TAG, "Line:[${i + 1}] Text:[$line]")
                }
            }
        }
    }
}
