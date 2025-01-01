package net.bi4vmr.study.loop1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import net.bi4vmr.study.databinding.TestuiLoopBinding

class TestUILoop1KT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUILoop1KT::class.java.simpleName
    }

    private val binding: TestuiLoopBinding by lazy {
        TestuiLoopBinding.inflate(layoutInflater)
    }
    private val adapter: MyVPAdapterKT by lazy { MyVPAdapterKT(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 将适配器与ViewPager绑定
        binding.viewpager2.adapter = adapter

        // 构建列表并应用到适配器中
        makeDataList()

        // 注册监听器：OnPageChangeCallback
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG, "OnPageChangeCallback-PageScrollStateChanged. State:[$state]")
                // 滑动停止时进行判断
                if (state == 0) {
                    // 当前页面索引
                    val index = binding.viewpager2.currentItem
                    // 最大页面索引
                    val maxIndex = adapter.itemCount - 1
                    if (index == 0) {
                        /* 当前为首页（原始列表的最后一项） */
                        // 滑动至原始列表的最后一项（即当前列表的倒数第二项）
                        binding.viewpager2.setCurrentItem(maxIndex - 1, false)
                    } else if (index == maxIndex) {
                        /* 当前为末页（原始列表的第一项） */
                        // 滑动至原始列表的第一项（即当前列表的第二项）
                        binding.viewpager2.setCurrentItem(1, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                Log.i(TAG, "OnPageChangeCallback-PageSelected. Index:[$position]")
            }
        })

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager2.setCurrentItem(2, false)
        }
    }

    private fun makeDataList() {
        // 创建测试页面1-3
        val fmList: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 1..3) {
            fmList.add(TestFragmentKT.newInstance("页面$i"))
        }

        val pages: MutableList<TestFragmentKT> = mutableListOf()
        // 向列表首部添加最后一项
        pages.add(TestFragmentKT.newInstance("页面3"))
        // 添加所有的列表项
        pages.addAll(fmList)
        // 向列表尾部添加第一项
        pages.add(TestFragmentKT.newInstance("页面1"))

        // 更新数据
        adapter.updatePages(pages)
        // 数据更新完毕后，切换至原始列表中的第一页。
        binding.viewpager2.post {
            if (adapter.itemCount > 0) {
                binding.viewpager2.setCurrentItem(1, false)
            }
        }
    }
}
