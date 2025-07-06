package net.bi4vmr.study.base

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btn01.setOnClickListener { test() }
        }
    }

    // 功能模块
    private fun test() {
        Log.i(TAG, "--- 功能模块 ---")
        appendLog("\n--- 功能模块 ---\n")

        // ...
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
