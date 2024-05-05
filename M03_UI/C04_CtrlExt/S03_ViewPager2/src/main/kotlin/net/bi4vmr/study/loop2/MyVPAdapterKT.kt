package net.bi4vmr.study.loop2

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager适配器 - 循环滑动（实现二）。
 */
class MyVPAdapterKT(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    companion object {
        private val TAG: String = MyVPAdapterKT::class.java.simpleName
    }

    // 数据源List
    private val datas: MutableList<String> = mutableListOf()

    // 更新数据项。
    @SuppressLint("NotifyDataSetChanged")
    public fun updatePages(newDatas: List<String>) {
        datas.clear()
        datas.addAll(newDatas)

        notifyDataSetChanged()
    }

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    override fun getItemCount(): Int {
        // 返回一个很大的数值
        return Short.MAX_VALUE.toInt()
    }

    /**
     * 获取当前位置的Fragment。
     *
     * @param position 页面索引。
     * @return Fragment实例。
     */
    override fun createFragment(position: Int): Fragment {
        Log.d(TAG, "CreateFragment. Position:[${datas.size}]")
        // 取模运算，将页面索引映射到实际的页面队列中。
        val index = position % datas.size
        Log.d(TAG, "CreateFragment. Index:[${index}]")
        val name = datas[index]
        return TestFragment.newInstance(name)
    }
}
