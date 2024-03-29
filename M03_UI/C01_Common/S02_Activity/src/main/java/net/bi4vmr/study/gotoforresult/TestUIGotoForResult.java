package net.bi4vmr.study.gotoforresult;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiGotoforresultBinding;

public class TestUIGotoForResult extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIGotoForResult.class.getSimpleName();

    private TestuiGotoforresultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiGotoforresultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testStart());
    }

    // 启动页面并等待结果
    private void testStart() {
        Log.i(TAG, "--- 启动页面并等待结果 ---");
        binding.tvLog.append("\n--- 启动页面并等待结果 ---\n");

        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        // 启动页面，并设置请求码。
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "OnActivityResult. RequestCode:[" + requestCode + "] ResultCode:[" + resultCode + "]");
        binding.tvLog.append("OnActivityResult. RequestCode:[" + requestCode + "] ResultCode:[" + resultCode + "]\n");
        // 判断为何种请求
        if (requestCode == 100) {
            // 判断为何种结果
            if (resultCode == 999) {
                // 获取结果
                if (data != null) {
                    String s = data.getStringExtra("RESULT");
                    Log.i(TAG, "OnActivityResult. Data:[" + s + "]");
                    binding.tvLog.append("OnActivityResult. Data:[" + s + "]\n");
                }
            }
        }
    }
}
