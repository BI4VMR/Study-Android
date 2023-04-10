package net.bi4vmr.study.ui.extctrl.viewpager2.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import net.bi4vmr.study.ui.extctrl.viewpager2.NestedFragment;

import java.util.List;

/**
 * Name        : Adapter02
 * Author      : BI4VMR
 * Email       : bi4vmr@qq.com
 * Date        : 2022-06-14 23:39
 * Description : TODO 添加描述
 */
public class Adapter02Out extends FragmentStateAdapter {

    // 数据源List
    private final List<NestedFragment> pages;

    // 构造方法
    public Adapter02Out(FragmentActivity activity, List<NestedFragment> pages) {
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
