package net.bi4vmr.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        // 创建测试页面
        val pages: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 1..10) {
            pages.add(TestFragmentKT.newInstance("页面$i"))
        }

        // 创建适配器实例
        val adapter = MyVPAdapterKT(supportFragmentManager, pages)
        // 将适配器与ViewPager绑定
        binding.viewpager.adapter = adapter

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager.setCurrentItem(2, false)
        }
    }
}
