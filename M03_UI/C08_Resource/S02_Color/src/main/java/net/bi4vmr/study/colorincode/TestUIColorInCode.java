package net.bi4vmr.study.colorincode;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiColorincodeBinding;

public class TestUIColorInCode extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIColorInCode.class.getSimpleName();

    private TestuiColorincodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiColorincodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 使用16进制表示色值
        binding.textview1.setTextColor(0xFFFF0000);
        // 使用系统预定义的色值
        binding.textview2.setTextColor(Color.GREEN);
        // 使用Color类转换10进制格式的色值
        binding.textview3.setTextColor(Color.argb(255, 255, 255, 0));
        // 使用Color类转换字符串格式的色值
        binding.textview4.setTextColor(Color.parseColor("#0000FF"));
    }
}
