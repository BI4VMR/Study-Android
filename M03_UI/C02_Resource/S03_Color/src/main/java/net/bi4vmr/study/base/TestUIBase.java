package net.bi4vmr.study.base;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

        /* 在代码中引用颜色资源 */
        // 通过Resources实例获取颜色资源
        int colorValue = ContextCompat.getColor(getApplicationContext(), R.color.color_sample);
        // 将颜色设置到控件上
        binding.tvRefColorInCode.setTextColor(colorValue);

        /* 在代码中表示颜色 */
        // 使用16进制表示色值
        binding.textview1.setTextColor(0xFFFF0000);
        // 使用Color类预定义的色值
        binding.textview2.setTextColor(Color.GREEN);
        // 使用Color类转换10进制格式的色值
        binding.textview3.setTextColor(Color.argb(255, 255, 255, 0));
        // 使用Color类转换字符串格式的色值
        binding.textview4.setTextColor(Color.parseColor("#0000FF"));
    }
}
