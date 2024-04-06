package net.bi4vmr.study.style

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiStyleBinding

class TestUIStyleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIStyleKT::class.java.simpleName
    }

    private val binding: TestuiStyleBinding by lazy {
        TestuiStyleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* --- 选中状态 --- */
        // 获取选中状态
        val state: Boolean = binding.cbState.isChecked
        Log.i(TAG, "选中状态：[$state]")

        // 设置选中状态
        binding.cbState.isChecked = true
    }
}
