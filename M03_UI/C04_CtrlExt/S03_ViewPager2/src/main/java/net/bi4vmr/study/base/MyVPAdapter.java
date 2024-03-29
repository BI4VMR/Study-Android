package net.bi4vmr.study.base;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MyVPAdapter extends FragmentStateAdapter {

    // 数据源List
    private final List<TestFragment> pages;

    // 构造方法
    public MyVPAdapter(FragmentActivity activity, List<TestFragment> pages) {
        super(activity);
        this.pages = pages;
    }

    /* 创建Fragment实例 */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("myapp", "createFragment()-position:" + position);
        return pages.get(position);
    }

    /* 获取页面数量 */
    @Override
    public int getItemCount() {
        Log.d("myapp", "getItemCount()-count:" + pages.size());
        return pages.size();
    }
}
