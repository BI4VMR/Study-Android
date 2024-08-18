package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding

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
            // 动态设置样式
            swChangeStyle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    tvChangeStyle.setTextAppearance(R.style.MyText_Emphasize)
                } else {
                    tvChangeStyle.setTextAppearance(R.style.MyText_Normal)
                }
            }
        }
    }
}
