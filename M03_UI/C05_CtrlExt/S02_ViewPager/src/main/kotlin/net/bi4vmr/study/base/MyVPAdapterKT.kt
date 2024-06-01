package net.bi4vmr.study.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * ViewPager适配器 - 基本应用。
 */
class MyVPAdapterKT(
    fm: FragmentManager,
    // 数据源List
    private val pages: List<TestFragmentKT>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    override fun getCount(): Int {
        return pages.size
    }

    /**
     * 获取当前位置的Fragment。
     *
     * @param position 页面索引。
     * @return Fragment实例。
     */
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }
}
