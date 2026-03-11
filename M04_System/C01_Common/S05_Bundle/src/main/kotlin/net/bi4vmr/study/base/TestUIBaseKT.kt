package net.bi4vmr.study.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：Bundle的基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnStart.setOnClickListener { testSendData() }
        }
    }

    // 启动界面并传递数据
    private fun testSendData() {
        binding.tvLog.append("\n--- 启动界面并传递数据 ---\n")
        Log.i(TAG, "--- 启动界面并传递数据 ---")

        // 新建Bundle对象，并存入一些数据。
        val bundle = Bundle()
        bundle.putString("KEY_ID", "0001")
        bundle.putString("KEY_NAME", "书籍")
        bundle.putFloat("KEY_PRICE", 39.9F)
        bundle.putBoolean("KEY_SOLDOUT", false)

        val intent = Intent()
        intent.setClass(this, BookInfoActivity::class.java)
        // 将数据存入Intent，然后启动目标Activity。
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
