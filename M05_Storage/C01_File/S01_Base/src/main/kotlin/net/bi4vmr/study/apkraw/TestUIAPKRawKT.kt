package net.bi4vmr.study.apkraw

import android.content.ContentResolver
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiApkrawBinding
import net.bi4vmr.study.util.IOUtil
import java.io.InputStream

class TestUIAPKRawKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIAPKRawKT::class.java.simpleName}"
    }

    private val binding: TestuiApkrawBinding by lazy {
        TestuiApkrawBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnReadStream.setOnClickListener { testReadStream() }
            btnReadURI.setOnClickListener { testReadURI() }
        }
    }

    private fun testReadStream() {
        Log.i(TAG, "--- 读取文件（字节流） ---")
        appendLog("\n--- 读取文件（字节流） ---\n")

        // 读取"raw/test.txt"
        val resources: Resources = applicationContext.resources
        try {
            // 传入资源ID，获取输入流。
            val stream: InputStream = resources.openRawResource(R.raw.test)
            // 从输入流读取文本。
            val content: String = IOUtil.readFile(stream)
            binding.tvLog.append("test.txt文件的内容为：\n$content")
            Log.i(TAG, "test.txt文件的内容为：$content")
        } catch (e: Exception) {
            Log.e(TAG, "读取文件失败！", e)
        }
    }

    private fun testReadURI() {
        Log.i(TAG, "--- 读取文件（URI） ---")
        appendLog("\n--- 读取文件（URI） ---\n")

        // 拼接"raw"目录中图片文件的URI
        val uri = "${ContentResolver.SCHEME_ANDROID_RESOURCE}://${packageName}/raw/pic"
        binding.tvLog.append("pic.png文件的URI为：\n$uri")
        Log.i(TAG, "pic.png文件的URI为：$uri")

        // 将图片加载至ImageView
        binding.ivTest.setImageURI(Uri.parse(uri))
        binding.tvLog.append("\n图片已加载至ImageView。")
        Log.i(TAG, "图片已加载至ImageView。")
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
                    Log.w(TAG, "TextView scroll failed!", e)
                }
            }
        }
    }
}
