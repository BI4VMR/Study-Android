package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiUserinfoBinding

/**
 * 测试界面：用户信息API。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIUserInfoKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIUserInfoKT::class.java.simpleName}"
    }

    private val binding: TestuiUserinfoBinding by lazy {
        TestuiUserinfoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnGetUID.setOnClickListener { test() }
        }
    }

    // 功能模块
    private fun test() {
        Log.i(TAG, "--- 功能模块 ---")
        binding.tvLog.append("\n--- 功能模块 ---\n")
    }
}
