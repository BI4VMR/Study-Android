package net.bi4vmr.study.loop1;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器 - 循环滑动（实现一）。
 */
public class MyVPAdapter extends FragmentStateAdapter {

    private static final String TAG = "TestApp-" + MyVPAdapter.class.getSimpleName();

    // 数据源List
    private final List<TestFragment> pages = new ArrayList<>();

    // 构造方法
    public MyVPAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    /*
     * 更新数据项。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updatePages(@NonNull List<TestFragment> datas) {
        pages.clear();
        pages.addAll(datas);

        notifyDataSetChanged();
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
}
