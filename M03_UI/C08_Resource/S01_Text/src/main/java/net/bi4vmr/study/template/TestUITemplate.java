package net.bi4vmr.study.template;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiTemplateBinding;

public class TestUITemplate extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUITemplate.class.getSimpleName();

    private TestuiTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 根据占位符依次传入对应的内容
        String text = getResources().getString(R.string.text_template, "李田所", 24);
        // 将字符串设置到控件上
        binding.textview.setText(text);
    }
}
