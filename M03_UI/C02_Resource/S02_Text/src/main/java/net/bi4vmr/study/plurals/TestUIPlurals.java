package net.bi4vmr.study.plurals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiPluralsBinding;

public class TestUIPlurals extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPlurals.class.getSimpleName();

    private TestuiPluralsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPluralsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 使用Plurals资源
        String num0 = getResources().getQuantityString(R.plurals.apple_info, 0, 0);
        binding.tvNum0.setText(num0);

        String num1 = getResources().getQuantityString(R.plurals.apple_info, 1, 1);
        binding.tvNum1.setText(num1);

        String num2 = getResources().getQuantityString(R.plurals.apple_info, 2, 2);
        binding.tvNum2.setText(num2);

        String num10 = getResources().getQuantityString(R.plurals.apple_info, 10, 10);
        binding.tvNum10.setText(num10);
    }
}
