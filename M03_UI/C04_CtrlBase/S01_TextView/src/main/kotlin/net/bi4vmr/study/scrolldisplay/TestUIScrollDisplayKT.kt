package net.bi4vmr.study.scrolldisplay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiScrollDisplayBinding

class TestUIScrollDisplayKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIScrollDisplayKT::class.java.simpleName
    }

    private val binding: TestuiScrollDisplayBinding by lazy {
        TestuiScrollDisplayBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 构造一段长的测试文本
        val builder = StringBuilder()
        for (i in 0 until 100) {
            builder.append(i)
        }
        val text: String = builder.toString()

        // 设置文本
        binding.tvMarquee.text = text
        // 设置选中状态为"true"，使滚动生效。
        binding.tvMarquee.isSelected = true
    }
}
