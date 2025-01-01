package net.bi4vmr.study.cache

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager适配器 - 缓存与复用。
 */
class MyVPAdapterKT(
    activity: FragmentActivity,
    // 数据源List
    private val pages: MutableList<TestFragmentKT>
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

    /**
     * 获取当前位置表项的ID。
     *
     * @param[position] 页面索引。
     * @return 表项ID。
     */
    override fun getItemId(position: Int): Long {
        // 返回表项的唯一标识符（此处以HashCode代替）
        return pages[position].hashCode().toLong()
    }

    /**
     * 判断当前表项ID是否属于当前数据源。
     *
     * @param[itemId] 待判断的页面ID。
     * @return 表项是否存在。
     */
    override fun containsItem(itemId: Long): Boolean {
        var result = false
        // 遍历数据源，寻找ID与系统传入的值匹配的项。
        for (f in pages) {
            if (f.hashCode().toLong() == itemId) {
                result = true
                break
            }
        }

        return result
    }

    /**
     * 更新数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateDatas(newDatas: List<TestFragmentKT>) {
        pages.clear()
        pages.addAll(newDatas)

        notifyDataSetChanged()
    }
}
