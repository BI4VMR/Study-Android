package net.bi4vmr.study.base

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.bi4vmr.study.databinding.TestuiBaseBinding
import javax.inject.Inject

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
@AndroidEntryPoint
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    /**
     * 业务组件实例。
     *
     * `@Inject` 注解表示该变量在运行时由Hilt进行依赖注入，此类变量必须是非私有的。
     */
    @Inject
    lateinit var httpManager: HTTPManagerKT

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnLogin.setOnClickListener { testLogin() }
        }
    }

    private fun testLogin() {
        Log.i(TAG, "----- 调用业务方法 -----")
        appendLog("\n----- 调用业务方法 -----")

        httpManager.login()
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
