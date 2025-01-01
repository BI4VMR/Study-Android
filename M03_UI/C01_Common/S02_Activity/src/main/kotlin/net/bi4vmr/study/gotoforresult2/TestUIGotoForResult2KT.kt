package net.bi4vmr.study.gotoforresult2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiGotoforresult2Binding
import net.bi4vmr.study.gotoforresult.ResultActivityKT

class TestUIGotoForResult2KT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIGotoForResult2KT::class.java.simpleName
    }

    private val binding: TestuiGotoforresult2Binding by lazy {
        TestuiGotoforresult2Binding.inflate(layoutInflater)
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

        val intent = Intent(this, ResultActivityKT::class.java)
        // 启动新的Activity
        getActivityResultLauncher().launch(intent)
    }

    private fun getActivityResultLauncher(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // 获取返回码
            val resultCode: Int = it.resultCode
            Log.i(TAG, "OnActivityResult. ResultCode:[$resultCode]")
            binding.tvLog.append("OnActivityResult. ResultCode:[$resultCode]\n")

            // 获取结果
            val data: Intent? = it.data
            if (data != null) {
                val s: String? = data.getStringExtra("RESULT")
                Log.i(TAG, "OnActivityResult. Data:[$s]")
                binding.tvLog.append("OnActivityResult. Data:[$s]\n")
            }
        }
    }
}
