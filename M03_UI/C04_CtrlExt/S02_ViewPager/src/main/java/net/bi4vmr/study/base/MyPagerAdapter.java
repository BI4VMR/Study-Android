package net.bi4vmr.study.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Name        : MyPagerAdapter
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-07-06 17:04
 * <p>
 * Description : ViewPager适配器。
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "TestApp-" + MyPagerAdapter.class.getSimpleName();

    // 数据源List
    private final List<TestFragment> pages;

    // 构造方法
    public MyPagerAdapter(@NonNull FragmentManager fm, List<TestFragment> pages) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.pages = pages;
    }

    /**
     * Name        : 获取当前位置的Fragment
     * <p>
     * Description : 无。
     *
     * @param position 页面索引。
     * @return Fragment实例。
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    /**
     * Name        : 获取页面数量
     * <p>
     * Description : 获取页面数量。
     *
     * @return 页面数量
     */
    @Override
    public int getCount() {
        return pages.size();
    }
}
