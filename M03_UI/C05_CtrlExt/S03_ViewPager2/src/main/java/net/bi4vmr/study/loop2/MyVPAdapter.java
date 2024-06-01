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

    /**
     * 获取页面数量。
     *
     * @return 页面数量。
     */
    @Override
    public int getItemCount() {
        // 如果数据源非空，返回一个很大的数值作为页数。
        return datas.isEmpty() ? 0 : Short.MAX_VALUE;
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

    /**
     * 更新数据项。
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateDatas(@NonNull List<String> newDatas) {
        datas.clear();
        datas.addAll(newDatas);

        notifyDataSetChanged();
    }

    /**
     * 获取ViewPager2队列中间的位置，并与数据源首项对齐。
     *
     * @return 位置索引。
     * <p>
     * 当数据源为空时，值为"-1"。
     */
    public int getMiddlePosition() {
        // 数据源为空时，返回"-1"。
        if (datas.isEmpty()) {
            return -1;
        }

        // VP2队列中间的位置
        int midPosition = getItemCount() / 2;
        // 计算该位置在数据源中的偏移量
        int modResult = midPosition % datas.size();
        if (modResult == 0) {
            /* 偏移量为0，说明该位置已经与数据源首项对齐。 */
            return midPosition;
        } else {
            /* 偏移量非0，向右移动若干位置，与数据源首项对齐。 */
            return midPosition + (datas.size() - modResult);
        }
    }
}
