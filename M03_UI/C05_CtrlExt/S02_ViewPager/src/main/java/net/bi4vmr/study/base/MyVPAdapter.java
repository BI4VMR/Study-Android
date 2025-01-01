package net.bi4vmr.study.base;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * ViewPager适配器 - 基本应用。
 */
public class MyVPAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "TestApp-" + MyVPAdapter.class.getSimpleName();

    // 数据源List
    private final List<TestFragment> pages;

    // 构造方法
    public MyVPAdapter(@NonNull FragmentManager fm, List<TestFragment> pages) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pages = pages;
    }

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    @Override
    public int getCount() {
        Log.d(TAG, "GetCount. Count:[" + pages.size() + "]");
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
    public Fragment getItem(int position) {
        Log.d(TAG, "GetItem. Position:[" + position + "]");
        return pages.get(position);
    }
}
