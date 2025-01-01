package net.bi4vmr.study.base;

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

        // 获取宿主Activity的MyViewModel实例
        MyViewModel vm = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        Log.i(TAG, "Get VM in parent Activity. ID:[" + vm.id + "]");

        // 从VM实例读取数据
        int data = vm.getNum();
        Log.i(TAG, "Get data in Activity's VM. Value:[" + data + "]");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
