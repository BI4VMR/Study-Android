package net.bi4vmr.study.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：Bundle的基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testSendData());
    }

    // 启动界面并传递数据
    private void testSendData() {
        binding.tvLog.append("\n--- 启动界面并传递数据 ---\n");
        Log.i(TAG, "--- 启动界面并传递数据 ---");

        // 新建Bundle对象，并存入一些数据。
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ID", "0001");
        bundle.putString("KEY_NAME", "书籍");
        bundle.putFloat("KEY_PRICE", 39.9F);
        bundle.putBoolean("KEY_SOLDOUT", false);

        Intent intent = new Intent();
        intent.setClass(this, BookInfoActivity.class);
        // 将数据存入Intent，然后启动目标Activity。
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
