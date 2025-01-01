package net.bi4vmr.study.base;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：TODO 添加简述。
 * <p>
 * TODO 添加详情。
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

        // textClock.setFormat24Hour("HH:mm:ss");
        SpannableString ss = new SpannableString("hh:mm");
        ss.setSpan(new BackgroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // textClock.setFormat24Hour(ss);
        binding.textClock.setFormat12Hour(ss);
        binding.textClock.setTimeZone("America/Los_Angeles");
    }
}
