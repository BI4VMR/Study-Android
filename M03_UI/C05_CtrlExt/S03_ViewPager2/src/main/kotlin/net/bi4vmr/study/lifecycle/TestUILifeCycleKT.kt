package net.bi4vmr.study.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiLifecycleBinding

class TestUILifeCycleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUILifeCycleKT::class.java.simpleName}"
    }

    private val binding: TestuiLifecycleBinding by lazy {
        TestuiLifecycleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 创建测试页面
        val pages: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 1..9) {
            pages.add(TestFragmentKT.newInstance("页面$i "))
        }

        // 创建适配器实例
        val adapter = MyVPAdapterKT(this, pages)
        // 将适配器与ViewPager2绑定
        binding.viewpager2.adapter = adapter

        // 预加载当前页面左右的1个页面
        binding.viewpager2.offscreenPageLimit = 1
    }
}
