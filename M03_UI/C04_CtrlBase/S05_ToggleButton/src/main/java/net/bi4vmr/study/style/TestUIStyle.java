package net.bi4vmr.study.style;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiStyleBinding;

public class TestUIStyle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIStyle.class.getSimpleName();

    private TestuiStyleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* --- 按钮状态 --- */
        // 获取选中状态
        boolean state = binding.tbtnCheckState.isChecked();

        // 设置选中状态
        binding.tbtnCheckState.setChecked(true);

        /* --- 按钮文本 --- */
        // 获取开启状态的文本
        CharSequence textOn = binding.tbtnText.getTextOn();

        // 设置开启状态的文本
        binding.tbtnText.setTextOn("已开启");

        // 获取关闭状态的文本
        CharSequence textOff = binding.tbtnText.getTextOff();

        // 设置关闭状态的文本
        binding.tbtnText.setTextOff("已关闭");
    }
}
