package net.bi4vmr.study.gotopage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.ActivityTestBinding

class TestActivityKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestActivityKT::class.java.simpleName
    }

    private val binding: ActivityTestBinding by lazy {
        ActivityTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 获取开启当前Activity的Intent实例
        val intent: Intent? = intent
        intent?.let {
            // 根据Key获取初始化参数的值
            val info: String? = it.getStringExtra("PARAM_INIT")
            if (info != null) {
                Log.i(TAG, "初始化参数内容：$info")
                binding.tvLog.append("\n初始化参数内容：$info")
            } else {
                Log.i(TAG, "初始化参数为空。")
                binding.tvLog.append("\n初始化参数为空。")
            }
        }

        binding.btnClose.setOnClickListener {
            // 关闭当前Activity
            finish()
        }
    }
}
