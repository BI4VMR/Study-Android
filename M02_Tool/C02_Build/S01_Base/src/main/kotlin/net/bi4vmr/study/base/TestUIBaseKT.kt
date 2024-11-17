package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：编译测试。
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
            btn01.setOnClickListener { test() }
        }
    }

    // 功能模块
    private fun test() {
        Log.i(TAG, "--- 功能模块 ---")
        binding.tvLog.append("\n--- 功能模块 ---\n")

        Log.d(TAG, "该工程主要用于测试编译，没有代码逻辑。")
        binding.tvLog.append("该工程主要用于测试编译，没有代码逻辑。")
    }
}
