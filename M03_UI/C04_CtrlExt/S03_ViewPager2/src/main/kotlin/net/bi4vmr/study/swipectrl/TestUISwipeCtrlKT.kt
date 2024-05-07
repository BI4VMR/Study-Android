package net.bi4vmr.study.swipectrl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiSwipectrlBinding

class TestUISwipeCtrlKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUISwipeCtrlKT::class.java.simpleName
    }

    private val binding: TestuiSwipectrlBinding by lazy {
        TestuiSwipectrlBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 创建测试页面
        val pages: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 0 until 10) {
            pages.add(TestFragmentKT.newInstance("页面${i + 1}"))
        }

        // 创建适配器实例
        val adapter = MyVPAdapterKT(this, pages.toList())
        // 将适配器与ViewPager绑定
        binding.viewpager2.adapter = adapter

        binding.tbtnUserInput.setOnCheckedChangeListener { _, isChecked ->
            // 设置用户输入状态
            binding.viewpager2.isUserInputEnabled = isChecked
        }

        binding.btnFakeDrag.setOnClickListener {
            // 开始模拟拖拽
            binding.viewpager2.beginFakeDrag()
            // 向后一个页面偏移100像素
            binding.viewpager2.fakeDragBy(-100.0F)
            // 终止模拟拖拽
            binding.viewpager2.endFakeDrag()
        }
    }
}
