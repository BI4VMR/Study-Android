package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // view 在左侧的坐标
        binding.image.getLeft();
        // x轴偏移量，初始为0,播放动画后会改变，向右为正，向左为负
        binding.image.getTranslationX();
        // 左侧坐标，Left+TranslationX
        binding.image.getX();
    }
}
