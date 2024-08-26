package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

/**
 * Name        : TestFragment
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 14:51
 * <p>
 * Description : 测试Fragment。
 */
public class TestFragment extends Fragment {

    private static final String TAG = "TestApp-" + TestFragment.class.getSimpleName();

    private MyViewModel activityVM;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取宿主Activity的MyViewModel实例
        activityVM = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView tvContent = view.findViewById(R.id.tvContent);

        // 读取LiveData的初始值
        Log.i(TAG, "LiveData初始值：" + activityVM.roNumberData.getValue());
        // 调用LiveData的"observe()"方法，注册本Fragment为该LiveData的观察者。
        activityVM.roNumberData.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i(TAG, "LiveData数值改变：" + integer);
                // 观察到数值改变时，将其更新到控件上。
                tvContent.setText("Num:" + integer);
            }
        });
        return view;
    }
}
