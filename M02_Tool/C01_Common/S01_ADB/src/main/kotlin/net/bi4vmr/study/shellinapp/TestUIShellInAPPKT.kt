package net.bi4vmr.study.shellinapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding
import net.bi4vmr.study.util.IOUtil
import java.io.InputStream

/**
 * 测试界面：在应用程序中执行ADB命令。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIShellInAPPKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIShellInAPPKT::class.java.simpleName
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnADBShell.setOnClickListener { testADBCMD() }
    }

    private fun testADBCMD() {
        Log.i(TAG, "--- 执行ADB命令 ---")
        binding.tvLog.append("\n--- 执行ADB命令 ---\n")

        // 命令语句
        val cmd = "free -h"

        try {
            // 执行命令
            val process: Process = Runtime.getRuntime().exec(cmd)
            // 阻塞当前线程等待命令执行完毕
            val resultCode: Int = process.waitFor()
            Log.i(TAG, "'free -h'命令的执行结果:[$resultCode]")
            binding.tvLog.append("'free -h'命令的执行结果:[$resultCode]\n")

            if (resultCode == 0) {
                /* 命令执行成功 */
                val isStdOut: InputStream = process.inputStream
                val text: String = IOUtil.readFile(isStdOut)
                Log.i(TAG, "标准信息输出：\n$text")
                binding.tvLog.append("标准信息输出：\n$text\n")
            } else {
                /* 命令执行失败 */
                val isStdErr: InputStream = process.errorStream
                val text: String = IOUtil.readFile(isStdErr)
                Log.i(TAG, "标准错误输出：\n$text")
                binding.tvLog.append("标准错误输出：\n$text\n")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
