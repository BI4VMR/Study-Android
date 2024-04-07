package net.bi4vmr.study.selector;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiSelectorBinding;

public class TestUISelector extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISelector.class.getSimpleName();

    private TestuiSelectorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSelectorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 通过Resources实例获取Selector资源
        ColorStateList colorStateList = ContextCompat.getColorStateList(getApplicationContext(), R.color.selector_sample);
        // 将Selector设置到控件上
        binding.button.setTextColor(colorStateList);
    }
}
