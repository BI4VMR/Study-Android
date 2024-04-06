package net.bi4vmr.study.selector;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUISelector extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISelector.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 通过Resources实例获取颜色选择器资源
        ColorStateList colorStateList = ContextCompat.getColorStateList(getApplicationContext(), R.color.color_sample);
        // 将颜色选择器设置到控件上
        binding.tvRefColorInCode.setTextColor(colorStateList);
    }
}
