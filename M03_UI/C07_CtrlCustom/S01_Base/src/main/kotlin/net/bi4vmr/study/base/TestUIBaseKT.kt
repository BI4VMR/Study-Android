package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiBaseBinding

class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIBaseKT::class.java.simpleName
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 构建BusinessCard实例
        val businessCard = BusinessCardKT(this)
        // 设置文本与图像
        businessCard.updateInfo("田所浩二", "11451419198", R.drawable.ic_funny_256)
        // 将其添加到布局中
        binding.container.addView(businessCard)
    }
}
