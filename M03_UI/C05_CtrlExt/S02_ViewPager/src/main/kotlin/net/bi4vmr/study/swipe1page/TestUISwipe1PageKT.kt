package net.bi4vmr.study.swipe1page

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import net.bi4vmr.study.databinding.TestuiSwipe1pageKtBinding

class TestUISwipe1PageKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUISwipe1PageKT::class.java.simpleName}"
    }

    private val binding: TestuiSwipe1pageKtBinding by lazy {
        TestuiSwipe1pageKtBinding.inflate(layoutInflater)
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
        val adapter = MyVPAdapterKT(supportFragmentManager, pages.toList())
        // 将适配器与ViewPager绑定
        binding.viewpager.adapter = adapter

        // 注册监听器：OnPageChangeListener
        binding.viewpager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {

            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG, "OnPageChangeCallback-PageScrollStateChanged. State:[$state]")
                binding.touchMaskView.updateVPScrollState(state)
            }
        })

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager.setCurrentItem(2, false)
        }
    }
}
