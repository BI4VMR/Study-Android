package net.bi4vmr.study.index

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiResourceIndexBinding

/**
 * 测试界面：资源索引。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIIndexKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIIndexKT::class.java.simpleName}"
    }

    private val binding: TestuiResourceIndexBinding by lazy {
        TestuiResourceIndexBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnGetResource.setOnClickListener { testGetResource() }
            btnGetResourceInfo.setOnClickListener { testGetResourceInfo() }
        }
    }

    private fun testGetResource() {
        Log.i(TAG, "----- 获取资源 -----")
        appendLog("\n----- 获取资源 -----")

        // 在代码中获取颜色资源
        val colorValue = getResources().getColor(R.color.common_text, theme)


        /*
         * Context也提供了快捷方式，等同于 `Context.getResources().getColor()` ，将会自动使用当前Theme。
         *
         * 如果想要获取Android内置的资源，需要添加前缀 `android.` 。
         */
        val colorValue2 = getColor(android.R.color.holo_purple)

        // 使用色值
        binding.tvLog.setTextColor(colorValue2)
    }

    private fun testGetResourceInfo() {
        Log.i(TAG, "----- 获取资源信息 -----")
        appendLog("\n----- 获取资源信息 -----")

        // 获取完整名称
        val fullName = resources.getResourceName(R.string.app_name)
        // 获取资源名称
        val name = resources.getResourceEntryName(R.string.app_name)
        // 获取资源类型
        val type = resources.getResourceTypeName(R.string.app_name)
        // 获取资源所在包名
        val pkgName = resources.getResourcePackageName(R.string.app_name)

        Log.i(TAG, "完整名称：$fullName")
        Log.i(TAG, "资源名称：$name")
        Log.i(TAG, "资源类型：$type")
        Log.i(TAG, "所在包名：$pkgName")

        appendLog("完整名称：$fullName")
        appendLog("资源名称：$name")
        appendLog("资源类型：$type")
        appendLog("所在包名：$pkgName")
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
