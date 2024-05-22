package net.bi4vmr.study.apkassets

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiApkassetsBinding

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
    }
}
