package net.bi4vmr.study.remote

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import net.bi4vmr.study.databinding.TestuiResourceRemoteBinding

class TestUIRemoteKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIRemoteKT::class.java.simpleName}"
    }

    private val binding: TestuiResourceRemoteBinding by lazy {
        TestuiResourceRemoteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnRemoteResource.setOnClickListener { testRemoteResource() }
            btnAPKResource.setOnClickListener { testAPKResource() }
        }
    }

    private fun testRemoteResource() {
        Log.i(TAG, "----- 获取其他软件包中的资源 -----")
        appendLog("\n----- 获取其他软件包中的资源 -----\n")

        try {
            val targetPackage = "net.bi4vmr.study.ui.resource.base.res"
            // 创建指定软件包的Context
            val remoteContext = applicationContext.createPackageContext(targetPackage, 0)
            // 查询资源名称对应的资源ID
            val resID: Int = remoteContext.resources.getIdentifier("house", "drawable", targetPackage)
            // 如果ID大于0，说明资源存在；否则表示资源不存在。
            if (resID > 0) {
                // 使用目标软件包的Resources获取该ID对应的资源
                val drawable = ResourcesCompat.getDrawable(remoteContext.resources, resID, null)
                // 使用资源
                binding.imageview.setImageDrawable(drawable)
            } else {
                Log.w(TAG, "未找到指定资源！")
                appendLog("未找到指定资源！")
            }
        } catch (e: Exception) {
            // 如果找不到目标软件包，请先确保安装了 `S01_Base_ResPack` 示例程序。
            Log.e(TAG, "获取远程资源失败！", e)
            appendLog("获取远程资源失败！")
        }
    }

    private fun testAPKResource() {
        Log.i(TAG, "----- 获取APK文件中的资源 -----")
        appendLog("\n----- 获取APK文件中的资源 -----\n")
    }

    // 向文本框中追加日志内容并滚动到最底端
    private fun appendLog(text: CharSequence) {
        binding.tvLog.apply {
            append(text)
            append("\n")
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
