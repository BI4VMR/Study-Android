package net.bi4vmr.study.selector

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiSelectorBinding

class TestUISelectorKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUISelectorKT::class.java.simpleName
    }

    private val binding: TestuiSelectorBinding by lazy {
        TestuiSelectorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 通过Resources实例获取Selector资源
        val colorStateList: ColorStateList? =
            ContextCompat.getColorStateList(applicationContext, R.color.selector_sample)
        colorStateList?.let {
            // 将Selector设置到控件上
            binding.button.setTextColor(it)
        }
    }
}
