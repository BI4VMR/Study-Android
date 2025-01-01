package net.bi4vmr.study.event

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager适配器 - 基本应用。
 */
class MyVPAdapterKT(
    activity: FragmentActivity,
    // 数据源List
    private val pages: List<TestFragmentKT>
) : FragmentStateAdapter(activity) {

    companion object {
        private val TAG: String = MyVPAdapterKT::class.java.simpleName
    }

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    override fun getItemCount(): Int {
        Log.d(TAG, "GetItemCount. Count:[${pages.size}]")
        return pages.size
    }

    /**
     * 获取当前位置的Fragment。
     *
     * @param position 页面索引。
     * @return Fragment实例。
     */
    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "CreateFragment. Position:[${pages.size}]")
        return pages[position]
    }
}
