package net.bi4vmr.study.launchmode;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLaunchmodeBinding;
import net.bi4vmr.study.viewstate.TestUIViewState;

import java.util.List;

public class TestUILaunchMode extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIViewState.class.getSimpleName();

    private TestuiLaunchmodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLaunchmodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 页面ID
        binding.tvID.setText("起始页");

        // 启动Standard模式的界面
        binding.btnStandard.setOnClickListener(v -> {
            Intent intent = new Intent(this, StandardActivity.class);
            startActivity(intent);
        });

        // 启动SingleTop模式的界面
        binding.btnSingleTop.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleTopActivity.class);
            startActivity(intent);
        });

        // 启动SingleTask模式的界面
        binding.btnSingleTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleTaskActivity.class);
            startActivity(intent);
        });

        // 启动SingleInstance模式的界面
        binding.btnSingleInstance.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleInstanceActivity.class);
            startActivity(intent);
        });

        // 获取ActivityManager实例
        ActivityManager am = getSystemService(ActivityManager.class);
        // 获取任务列表并打印它们的信息
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo item : list) {
            Log.i(TAG, "----------");
            Log.i(TAG, "TaskID:" + item.id);
            Log.i(TAG, "Package:" + item.topActivity.getPackageName());
            Log.i(TAG, "----------");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String cmpName = TestUILaunchMode.class.getSimpleName();
        Log.i(TAG, cmpName + "所属的TaskID:" + getTaskId());
    }
}
