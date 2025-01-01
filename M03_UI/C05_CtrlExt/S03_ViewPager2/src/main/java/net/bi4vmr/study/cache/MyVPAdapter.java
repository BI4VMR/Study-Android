package net.bi4vmr.study.cache;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * ViewPager适配器 - 缓存与复用。
 */
public class MyVPAdapter extends FragmentStateAdapter {

    private static final String TAG = "TestApp-" + MyVPAdapter.class.getSimpleName();

    // 数据源List
    private final List<TestFragment> pages;

    // 构造方法
    public MyVPAdapter(@NonNull FragmentActivity activity, List<TestFragment> pages) {
        super(activity);
        this.pages = pages;
    }

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    @Override
    public int getItemCount() {
        Log.d(TAG, "GetItemCount. Count:[" + pages.size() + "]");
        return pages.size();
    }

    /**
     * 获取当前位置的Fragment。
     *
     * @param position 页面索引。
     * @return Fragment实例。
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG, "CreateFragment. Position:[" + position + "]");
        return pages.get(position);
    }

    /**
     * 获取当前位置表项的ID。
     *
     * @param position 页面索引。
     * @return 表项ID。
     */
    @Override
    public long getItemId(int position) {
        // 返回表项的唯一标识符（此处以HashCode代替）
        return pages.get(position).hashCode();
    }

    /**
     * 判断当前表项ID是否属于当前数据源。
     *
     * @param itemId 待判断的页面ID。
     * @return 表项是否存在。
     */
    @Override
    public boolean containsItem(long itemId) {
        boolean result = false;
        // 遍历数据源，寻找ID与系统传入的值匹配的项。
        for (TestFragment f : pages) {
            if (f.hashCode() == itemId) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * 更新数据源。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateDatas(@NonNull List<TestFragment> newDatas) {
        pages.clear();
        pages.addAll(newDatas);

        notifyDataSetChanged();
    }
}
