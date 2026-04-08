package net.bi4vmr.study.function

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiFunctionBinding

class TestUIFunctionKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIFunctionKT::class.java.simpleName
    }

    private val binding: TestuiFunctionBinding by lazy {
        TestuiFunctionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // /* 点击事件 */
        // // 获取按钮"btnTest"的实例
        // val btnTest: Button = findViewById(R.id.btnTest)
        // // 实现点击监听器并传递给"btnTest"
        // btnTest.setOnClickListener {
        //     Log.i(TAG, "按钮Test被点击了！")
        //     binding.tvLog.append("按钮Test被点击了！\n")
        // }
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
