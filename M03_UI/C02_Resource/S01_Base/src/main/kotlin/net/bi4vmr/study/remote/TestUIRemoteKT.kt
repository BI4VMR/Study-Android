package net.bi4vmr.study.remote

import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import net.bi4vmr.study.databinding.TestuiResourceRemoteBinding

/**
 * 测试界面：外部资源。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
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
            tvLog.movementMethod = ScrollingMovementMethod.getInstance()

            btnRemoteResource.setOnClickListener { testRemoteResource() }
            btnAPKResource.setOnClickListener { testAPKResource() }
            btnReset.setOnClickListener { imageview.setImageDrawable(null) }
        }
    }

    private fun testRemoteResource() {
        Log.i(TAG, "----- 获取其他软件包中的资源 -----")
        appendLog("\n----- 获取其他软件包中的资源 -----")

        try {
            val targetPackage = "net.bi4vmr.study.ui.resource.base.res"
            // 创建指定软件包的Context
            val remoteContext = createPackageContext(targetPackage, 0)
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
        appendLog("\n----- 获取APK文件中的资源 -----")

        // 目标APK文件路径
        val apkPath = "/data/S01_Base_ResPack-debug.apk"
        // 解析APK包名
        val pkgName = packageManager.getPackageArchiveInfo(
            apkPath,
            PackageManager.PackageInfoFlags.of(0L)
        )?.packageName ?: "UNKNOWN"

        try {
            // 创建AssetManager实例并加载资源
            val remoteAM = AssetManager::class.java.newInstance()
            // 通过反射调用 `addAssetPath()` 方法加载APK资源
            val method = AssetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            // 该方法将返回当前APK资源在AssetManager中的索引，若为 `0` 则表示加载失败。
            val index = method.invoke(remoteAM, apkPath) as? Int ?: -1
            if (index <= 0) {
                Log.e(TAG, "加载APK资源失败！")
                appendLog("加载APK资源失败！")
                return
            }

            // 创建Resources实例，并套用当前应用的显示参数和配置。
            val remoteRes = Resources(remoteAM, resources.displayMetrics, resources.configuration)
            // 查询资源名称对应的资源ID
            val resID: Int = remoteRes.getIdentifier("house", "drawable", pkgName)
            if (resID > 0) {
                val drawable = ResourcesCompat.getDrawable(remoteRes, resID, null)
                binding.imageview.setImageDrawable(drawable)
            } else {
                Log.w(TAG, "未找到指定资源！")
                appendLog("未找到指定资源！")
            }
        } catch (e: Exception) {
            Log.e(TAG, "获取远程资源失败！", e);
            appendLog("获取远程资源失败！");
        }
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
