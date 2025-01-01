package net.bi4vmr.study.autosize

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiAutosizeBinding

/**
 * 测试界面：自动文本尺寸。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIAutoSizeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIAutoSizeKT::class.java.simpleName
    }

    private val binding: TestuiAutosizeBinding by lazy {
        TestuiAutosizeBinding.inflate(layoutInflater)
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
        // binding.tvMarquee.text = text
        binding.textView4.isSelected = true
    }
}
