package net.bi4vmr.study.loop2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiEventBinding

class TestUILoop2KT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUILoop2KT::class.java.simpleName
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(layoutInflater)
    }
    private val adapter: MyVPAdapterKT by lazy { MyVPAdapterKT(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 将适配器与ViewPager绑定
        binding.viewpager2.adapter = adapter

        // 构建列表并应用到适配器中
        makeDataList()

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager2.setCurrentItem(2, false)
        }
    }

    private fun makeDataList() {
        // 创建测试页面1-3
        val nameList: MutableList<String> = mutableListOf()
        for (i in 1..3) {
            nameList.add("页面$i")
        }

        // 更新数据
        adapter.updatePages(nameList)
        // 数据更新完毕后，切换至中间的一页。
        binding.viewpager2.post {
            if (adapter.itemCount > 0) {
                binding.viewpager2.setCurrentItem(adapter.getMiddlePosition(), false)
            }
        }
    }
}
