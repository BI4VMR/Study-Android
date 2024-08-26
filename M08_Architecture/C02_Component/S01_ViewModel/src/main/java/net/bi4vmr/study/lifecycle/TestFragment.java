package net.bi4vmr.study.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.databinding.FragmentTestBinding;

/**
 * 测试Fragment。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestFragment extends Fragment {

    private static final String TAG = "TestApp-" + TestFragment.class.getSimpleName();

    private FragmentTestBinding binding;

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate.");

        // 获取宿主Activity的MyViewModel2实例
        MyViewModel2 vmInActivity = new ViewModelProvider(requireActivity()).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in parent Activity. ID:[" + vmInActivity.id + "]");

        // 获取当前Fragment的ViewModel2实例
        MyViewModel2 vmInFragment = new ViewModelProvider(this).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in Fragment. ID:[" + vmInFragment.id + "]");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy.");
    }
}
