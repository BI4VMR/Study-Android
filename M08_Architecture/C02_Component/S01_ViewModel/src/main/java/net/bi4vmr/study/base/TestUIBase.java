package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_base);

        // 获取当前Activity的MyViewModel实例
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);
        Log.i(TAG, "Get VM in Activity. ID: " + vm.id);

        // 向VM实例写入数据
        Log.i(TAG, "Set data to Activity's VM. Value: 1000");
        vm.setNum(1000);

        // 初始化Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new TestFragment())
                .addToBackStack(null)
                .commit();
    }
}
