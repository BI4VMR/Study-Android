package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 点击事件 */
        // 获取按钮"btnTest"的实例
        // Button btnTest = findViewById(R.id.btnTest);
        // 实现点击监听器并传递给"btnTest"
        // btnTest.setOnClickListener(new View.OnClickListener() {
        //
        //     @Override
        //     public void onClick(View v) {
        //         Log.i(TAG, "按钮Test被点击了！");
        //         binding.tvLog.append("按钮Test被点击了！\n");
        //     }
        // });
    }
}
