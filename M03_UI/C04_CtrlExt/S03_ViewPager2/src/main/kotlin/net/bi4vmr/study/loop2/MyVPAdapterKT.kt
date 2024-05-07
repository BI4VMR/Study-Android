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

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    override fun getItemCount(): Int {
        // 如果数据源非空，返回一个很大的数值作为页数。
        return if (datas.isEmpty()) 0 else Short.MAX_VALUE.toInt()
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

    /**
     * 更新数据项。
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updatePages(newDatas: List<String>) {
        datas.clear()
        datas.addAll(newDatas)

        notifyDataSetChanged()
    }

    /**
     * 获取ViewPager2队列中间的位置，并与数据源首项对齐。
     *
     * @return 位置索引。
     *
     * 当数据源为空时，值为"-1"。
     */
    fun getMiddlePosition(): Int {
        // 数据源为空时，返回"-1"。
        if (datas.isEmpty()) {
            return -1
        }

        // VP2队列中间的位置
        val midPosition: Int = itemCount / 2
        // 计算该位置在数据源中的偏移量
        val modResult: Int = midPosition % datas.size
        return if (modResult == 0) {
            /* 偏移量为0，说明该位置已经与数据源首项对齐。 */
            midPosition
        } else {
            /* 偏移量非0，向右移动若干位置，与数据源首项对齐。 */
            midPosition + (datas.size - modResult)
        }
    }
}
