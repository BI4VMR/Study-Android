package net.bi4vmr.study.style;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiStyleBinding;

public class TestUIStyle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIStyle.class.getSimpleName();

    private TestuiStyleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* --- 选中状态 --- */
        // 获取选中状态
        boolean state = binding.cbState.isChecked();
        Log.i(TAG, "选中状态：[" + state + "]");

        // 设置选中状态
        binding.cbState.setChecked(true);

        /* --- 复选框颜色 --- */
    }
}
