package net.bi4vmr.study.apkassets

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiApkassetsBinding
import net.bi4vmr.study.util.IOUtil
import java.io.File
import java.io.InputStream

class TestUIAPKAssetsKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIAPKAssetsKT::class.java.simpleName}"
    }

    private val binding: TestuiApkassetsBinding by lazy {
        TestuiApkassetsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnReadStream.setOnClickListener { testReadStream() }
        }
    }

    // 读取文件（字节流）
    private fun testReadStream() {
        Log.i(TAG, "--- 读取文件（字节流） ---")
        binding.tvLog.append("\n--- 读取文件（字节流） ---\n")

        // 获取AssetManager
        val am: AssetManager = applicationContext.assets
        try {
            // 获取"config"目录下的子项
            val filenames: Array<String>? = am.list("config")
            if (filenames == null) {
                binding.tvLog.append("\n遍历目录config失败！")
                Log.i(TAG, "遍历目录config失败！")
                return
            }

            // 依次读取每个文件的内容
            filenames.forEach {
                val stream: InputStream = am.open("config${File.separator}$it")
                val content: String = IOUtil.readFile(stream)
                binding.tvLog.append("\n文件名称：$it")
                Log.i(TAG, "文件名称：$it")
                binding.tvLog.append("\n文件内容：$it")
                Log.i(TAG, "文件内容：$content")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 关闭AssetManager后，整个进程都无法再使用它，因此该方法不能由开发者调用。
        // am.close()
    }
}
