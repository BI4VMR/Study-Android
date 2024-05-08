package net.bi4vmr.study.cache

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiCacheBinding

class TestUICacheKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUICacheKT::class.java.simpleName
    }

    private val binding: TestuiCacheBinding by lazy {
        TestuiCacheBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 创建测试页面
        val pages: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 1..3) {
            pages.add(TestFragmentKT.newInstance("页面$i"))
        }

        // 创建适配器实例
        val adapter = MyVPAdapterKT(this, pages)
        // 将适配器与ViewPager2绑定
        binding.viewpager2.adapter = adapter

        // 按钮：“更新数据”
        binding.btnUpdate.setOnClickListener {
            // 创建新的数据源
            val pages2: MutableList<TestFragmentKT> = mutableListOf()
            for (i in 11..13) {
                pages2.add(TestFragmentKT.newInstance("页面$i"))
            }
            adapter.updateDatas(pages2)
        }
    }
}
