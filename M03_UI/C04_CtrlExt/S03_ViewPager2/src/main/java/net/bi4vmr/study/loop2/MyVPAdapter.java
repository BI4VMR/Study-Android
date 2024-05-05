package net.bi4vmr.study.loop2;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器 - 循环滑动（实现二）。
 */
public class MyVPAdapter extends FragmentStateAdapter {

    private static final String TAG = "TestApp-" + MyVPAdapter.class.getSimpleName();

    // 数据源List
    private final List<String> datas = new ArrayList<>();

    // 构造方法
    public MyVPAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    /*
     * 更新数据项。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateDatas(@NonNull List<String> newDatas) {
        datas.clear();
        datas.addAll(newDatas);

        notifyDataSetChanged();
    }

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    @Override
    public int getItemCount() {
        // 返回一个很大的数值
        return Short.MAX_VALUE;
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
        // 取模运算，将页面索引映射到实际的页面队列中。
        int index = position % datas.size();
        Log.d(TAG, "CreateFragment. Index:[" + index + "]");
        String name = datas.get(index);
        return TestFragment.newInstance(name);
    }
}
