package net.bi4vmr.study.base;

import android.os.Bundle;

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

        // 动态设置样式
        binding.swChangeStyle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.tvChangeStyle.setTextAppearance(R.style.MyText_Emphasize);
            } else {
                binding.tvChangeStyle.setTextAppearance(R.style.MyText_Normal);
            }
        });
    }
}
