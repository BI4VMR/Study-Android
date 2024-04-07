package net.bi4vmr.study.template

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiTemplateBinding

class TestUITemplateKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUITemplateKT::class.java.simpleName
    }

    private val binding: TestuiTemplateBinding by lazy {
        TestuiTemplateBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 根据占位符依次传入对应的内容
        val text: String = resources.getString(R.string.text_template, "李田所", 24)
        // 将字符串设置到控件上
        binding.textview.text = text
    }
}
