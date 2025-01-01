package net.bi4vmr.study.font;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiFontBinding;

public class TestUIFont extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFont.class.getSimpleName();

    private TestuiFontBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFontBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 获取字体对象（从"asset"目录加载）
        Typeface typefaceFromAssert = Typeface.createFromAsset(getApplication().getAssets(), "fonts/jetbrainsmono_regular.ttf");

        // 获取字体对象（从"res"目录加载）
        Typeface typeface = getApplication().getResources().getFont(R.font.jetbrainsmono);
        // 将字体设置到控件上
        binding.tvFontFile.setTypeface(typeface);
        // 将字体与样式设置到控件上
        binding.tvFontFile.setTypeface(typeface, Typeface.BOLD_ITALIC);
    }
}
