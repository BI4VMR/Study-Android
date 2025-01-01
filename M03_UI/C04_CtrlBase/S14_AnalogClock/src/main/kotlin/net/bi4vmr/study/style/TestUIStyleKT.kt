package net.bi4vmr.study.style

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：外观定制。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIStyleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIStyleKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
