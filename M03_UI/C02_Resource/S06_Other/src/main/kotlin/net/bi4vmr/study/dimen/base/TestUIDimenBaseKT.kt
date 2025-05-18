package net.bi4vmr.study.dimen.base

import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiDimenBaseBinding

/**
 * 测试界面：尺寸 - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIDimenBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIDimenBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiDimenBaseBinding by lazy {
        TestuiDimenBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 将尺寸资源解析为像素值
        val dimen: Float = resources.getDimension(R.dimen.title_size)
        binding.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, dimen)
    }
}
