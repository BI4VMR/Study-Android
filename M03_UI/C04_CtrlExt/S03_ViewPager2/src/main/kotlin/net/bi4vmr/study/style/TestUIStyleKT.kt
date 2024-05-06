package net.bi4vmr.study.style

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import net.bi4vmr.study.databinding.TestuiStyleBinding

class TestUIStyleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIStyleKT::class.java.simpleName
    }

    private val binding: TestuiStyleBinding by lazy {
        TestuiStyleBinding.inflate(layoutInflater)
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
        val adapter = MyVPAdapterKT(this, pages)
        // 将适配器与ViewPager2绑定
        binding.vp2Direction.adapter = adapter

        /* 设置边缘阴影效果 */
        (binding.vp2Direction.getChildAt(0) as? RecyclerView)?.let {
            // 关闭边缘阴影效果
            it.overScrollMode = View.OVER_SCROLL_NEVER
        }
    }
}
