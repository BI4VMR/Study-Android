package net.bi4vmr.study.base;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Activity对应的ViewModel实例
        MyViewModel activityVM = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        Log.i("myapp", "TestFragment-Get VM in Activity:" + activityVM.getVMName());

        // 获取Fragment对应的ViewModel实例
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);
        Log.i("myapp", "TestFragment-Get VM in Fragment:" + vm.getVMName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("myapp", "TestFragment-OnDestroy.");
    }
}
