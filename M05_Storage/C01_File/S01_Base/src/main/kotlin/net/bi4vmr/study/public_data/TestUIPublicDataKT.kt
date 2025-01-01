package net.bi4vmr.study.public_data

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiPublicDataBinding
import java.io.File

class TestUIPublicDataKT : AppCompatActivity() {

    companion object {
        private val TAG = "TestApp-${TestUIPublicDataKT::class.simpleName}"
    }

    private val binding: TestuiPublicDataBinding by lazy {
        TestuiPublicDataBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnGetPath.setOnClickListener { testGetPath() }
            btnGotoSetting.setOnClickListener { testGotoSetting() }
        }
    }

    // 获取共享目录路径
    private fun testGetPath() {
        binding.tvLog.append("\n--- 获取共享目录路径 ---\n")
        Log.i(TAG, "--- 获取共享目录路径 ---")

        // 获取共享目录的根路径
        val shareDir = Environment.getExternalStorageDirectory()
        binding.tvLog.append("\n共享存储-根目录: $shareDir")
        Log.i(TAG, "共享存储-根目录: $shareDir")
        binding.tvLog.append("\n共享存储-根目录: 读-${shareDir.canRead()} ，写-${shareDir.canWrite()}")
        Log.i(TAG, "共享存储-根目录: 读-${shareDir.canRead()} ，写-${shareDir.canWrite()}")

        // 获取指定类型的共享目录
        val shareDLDir: File =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        binding.tvLog.append("\n共享存储-下载: $shareDLDir")
        Log.i(TAG, "共享存储-下载: $shareDLDir")
        binding.tvLog.append("\n共享存储-下载: 读-${shareDLDir.canRead()} ，写-${shareDLDir.canWrite()}")
        Log.i(TAG, "共享存储-下载: 读-${shareDLDir.canRead()} ，写-${shareDLDir.canWrite()}")
    }

    // 打开应用详情页面
    private fun testGotoSetting() {
        val uri = Uri.parse("package:${packageName}")
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = uri
        startActivity(intent)
    }
}
