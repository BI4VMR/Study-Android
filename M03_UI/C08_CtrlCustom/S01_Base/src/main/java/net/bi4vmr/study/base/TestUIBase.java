package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 构建BusinessCard实例
        BusinessCard businessCard = new BusinessCard(this);
        // 设置文本与图像
        businessCard.updateInfo("田所浩二", "11451419198", R.drawable.ic_funny_256);
        // 将其添加到布局中
        binding.container.addView(businessCard);
    }
}
