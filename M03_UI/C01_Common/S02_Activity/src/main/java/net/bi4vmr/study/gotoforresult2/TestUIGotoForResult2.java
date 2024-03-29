package net.bi4vmr.study.gotoforresult2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiGotoforresult2Binding;
import net.bi4vmr.study.gotoforresult.ResultActivity;
import net.bi4vmr.study.gotoforresult.TestUIGotoForResult;

public class TestUIGotoForResult2 extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIGotoForResult.class.getSimpleName();

    private TestuiGotoforresult2Binding binding;

    private final ActivityResultLauncher<Intent> activityLauncher = getActivityResultLauncher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiGotoforresult2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testStart());
    }

    // 启动页面并等待结果
    private void testStart() {
        Log.i(TAG, "--- 启动页面并等待结果 ---");
        binding.tvLog.append("\n--- 启动页面并等待结果 ---\n");

        Intent intent = new Intent(this, ResultActivity.class);
        // 启动新的Activity
        activityLauncher.launch(intent);
    }

    // 获取ActivityResultLauncher的方法
    private ActivityResultLauncher<Intent> getActivityResultLauncher() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // 获取返回码
                        int resultCode = result.getResultCode();
                        Log.i(TAG, "OnActivityResult. ResultCode:[" + resultCode + "]");
                        binding.tvLog.append("OnActivityResult. ResultCode:[" + resultCode + "]\n");

                        // 获取结果
                        Intent data = result.getData();
                        if (data != null) {
                            String s = data.getStringExtra("RESULT");
                            Log.i(TAG, "OnActivityResult. Data:" + s);
                            binding.tvLog.append("OnActivityResult. Data:[" + s + "]\n");
                        }
                    }
                });
    }
}
