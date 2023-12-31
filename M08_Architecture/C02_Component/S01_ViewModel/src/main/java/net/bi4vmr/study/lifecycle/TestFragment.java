package net.bi4vmr.study.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate.");

        // 获取宿主Activity的MyViewModel2实例
        MyViewModel2 vmInActivity = new ViewModelProvider(requireActivity()).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in parent Activity. ID: " + vmInActivity.id);

        // 获取当前Fragment的ViewModel2实例
        MyViewModel2 vm = new ViewModelProvider(this).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in Fragment. ID: " + vm.id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy.");
    }
}
