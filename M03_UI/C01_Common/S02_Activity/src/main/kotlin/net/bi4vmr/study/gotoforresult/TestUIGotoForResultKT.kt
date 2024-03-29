package net.bi4vmr.study.gotoforresult

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiGotoforresultBinding

class TestUIGotoForResultKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIGotoForResultKT::class.java.simpleName
    }

    private val binding: TestuiGotoforresultBinding by lazy {
        TestuiGotoforresultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { testStart() }
    }

    // 启动页面并等待结果
    private fun testStart() {
        Log.i(TAG, "--- 启动页面并等待结果 ---")
        binding.tvLog.append("\n--- 启动页面并等待结果 ---\n")

        val intent = Intent(applicationContext, ResultActivityKT::class.java)
        // 启动页面，并设置请求码。
        startActivityForResult(intent, 100)
    }

    @Deprecated("Deprecated")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, "OnActivityResult. RequestCode:[$requestCode] ResultCode:[$resultCode]")
        binding.tvLog.append("OnActivityResult. RequestCode:[$requestCode] ResultCode:[$resultCode]\n")
        // 判断为何种请求
        if (requestCode == 100) {
            // 判断为何种结果
            if (resultCode == 999) {
                // 获取结果
                if (data != null) {
                    val s: String? = data.getStringExtra("RESULT")
                    Log.i(TAG, "OnActivityResult. Data:[$s]")
                    binding.tvLog.append("OnActivityResult. Data:[$s]\n")
                }
            }
        }
    }
}
