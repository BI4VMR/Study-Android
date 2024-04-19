package net.bi4vmr.study.style

import android.os.Bundle
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

        /* --- 按钮状态 --- */
        // 获取选中状态
        val state: Boolean = binding.tbtnCheckState.isChecked

        // 设置选中状态
        binding.tbtnCheckState.isChecked = true

        /* --- 按钮文本 --- */
        // 获取开启状态的文本
        val textOn: CharSequence = binding.tbtnText.textOn

        // 设置开启状态的文本
        binding.tbtnText.textOn = "已开启"

        // 获取关闭状态的文本
        val textOff: CharSequence = binding.tbtnText.textOff

        // 设置关闭状态的文本
        binding.tbtnText.textOn = "已关闭"
    }
}
