package net.bi4vmr.study.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiLifecycleBinding;

public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    private TestuiLifecycleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLifecycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 获取当前Activity的MyViewModel2实例
        MyViewModel2 vm = new ViewModelProvider(this).get(MyViewModel2.class);
        Log.i(TAG, "Get VM in Activity. ID:[" + vm.id + "]");

        // 添加Fragment
        binding.btnAdd.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, TestFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        });

        // 移除Fragment
        binding.btnRemove.setOnClickListener(v -> {
            getSupportFragmentManager()
                    .popBackStack();
        });
    }
}
