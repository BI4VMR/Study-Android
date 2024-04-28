package net.bi4vmr.study.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding
import net.bi4vmr.study.databinding.TestuiEventBinding

class TestUIEventKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIEventKT::class.java.simpleName
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 创建测试页面
        // val pages: MutableList<TestFragmentKT> = mutableListOf()
        // for (i in 0 until 10) {
        //     pages.add(TestFragmentKT())
        // }

        // 创建适配器实例
        // val adapter = MyVPAdapterKT(supportFragmentManager, pages)
        // 将适配器与ViewPager绑定
        // binding.viewpager.adapter = adapter

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager.setCurrentItem(2, false)
        }
    }
}
