package net.bi4vmr.study.event

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
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
        val pages: MutableList<TestFragmentKT> = mutableListOf()
        for (i in 0 until 10) {
            pages.add(TestFragmentKT.newInstance("页面${i + 1}"))
        }

        // 创建适配器实例
        val adapter = MyVPAdapterKT(this, pages.toList())
        // 将适配器与ViewPager绑定
        binding.viewpager2.adapter = adapter

        // 注册监听器：OnPageChangeCallback
        binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            /**
             * 当滑动状态改变时，该方法将被触发。
             *
             * @param[state] 表示滑动状态。
             *
             * [ViewPager.SCROLL_STATE_IDLE] 数值为"0"，表示滑动完毕，动画播放结束，目标页面已经完全显示。
             *
             * [ViewPager.SCROLL_STATE_DRAGGING] 数值为"1"，表示用户用手指按住屏幕，当前正在进行拖拽。
             *
             * [ViewPager.SCROLL_STATE_SETTLING] 数值为"2"，表示用户手指已离开屏幕，ViewPager向目标页面自动滑动，以使其完全显示。
             */
            override fun onPageScrollStateChanged(state: Int) {
                Log.i(TAG, "OnPageChangeCallback-PageScrollStateChanged. State:[$state]")
            }

            /**
             * 当用户滑动完毕最终选定某个页面时，该方法将被触发。
             *
             * 该事件仅在目标页面与先前显示的页面不同时触发，如果我们反复拖拽最后抬手时又回到了拖拽前显示的页面，则该事件并不会触发。
             *
             * @param[position] 目标页面在ViewPager中的索引。
             */
            override fun onPageSelected(position: Int) {
                Log.i(TAG, "OnPageChangeCallback-PageSelected. Index:[$position]")
            }

            /**
             * 当页面正在切换时，该方法以较高频率被触发。
             *
             * @param[position] 当前正在滑动的页面索引。
             * @param[positionOffset] 当前页面滑动至目标位置的进度，取值范围为 `[0, 1)` ，数值越大则离目标位置越近。
             * @param[positionOffsetPixels] 当前页面滑过的像素数量，取值范围为 `[0, <ViewPager宽度>)` 。
             */
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.i(
                    TAG,
                    "OnPageChangeCallback-PageScrolled. Index:[$position] Offset[$positionOffset] OffsetPX:[$positionOffsetPixels]"
                )
            }
        })

        // “切换至第三页”按钮
        binding.btnSwitchPage.setOnClickListener {
            binding.viewpager2.setCurrentItem(2, false)
        }
    }
}
