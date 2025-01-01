package net.bi4vmr.study.span;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiSpanBinding;

public class TestUISpan extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUISpan.class.getSimpleName();

    private TestuiSpanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiSpanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 示例文本
        String text = "我能吞下玻璃而不伤身体";

        /*
         * 基本应用
         */
        // 创建SpannableString实例，并设置初始内容。
        SpannableString ss1 = new SpannableString(text);

        /*
         * Name        : 设置样式
         *
         * Description : 设置文本样式。
         *
         * @param what  样式类型，内置样式名称通常以"Span"结尾。
         *              Span实例不可复用，如果重复使用同一个Span实例，则会导致先前设置的样式被清除。因此即使两处文本的样式完全相同，我
         *              们也应当创建两个不同的Span实例。
         * @param start 起始位置（包括）
         * @param end   终止位置（不包括）
         * @param flags 标志位，用于控制在Span区域前后插入新文本时，是否为它们也应用当前样式。
         *              Spanned.SPAN_INCLUSIVE_INCLUSIVE - 包括起始与结束位置。
         *              Spanned.SPAN_INCLUSIVE_EXCLUSIVE - 包括起始位置，不包括结束位置。
         *              Spanned.SPAN_EXCLUSIVE_INCLUSIVE - 不包括起始位置，包括结束位置。
         *              Spanned.SPAN_EXCLUSIVE_EXCLUSIVE - 不包括起始与结束位置。
         */
        ss1.setSpan(new BackgroundColorSpan(Color.RED), 2, 6, 0);
        ss1.setSpan(new BackgroundColorSpan(Color.GREEN), 8, 10, 0);

        // 将SpannableString设置到TextView中
        binding.tvDefault.setText(ss1);

        /*
         * 文本外观
         */
        // 设置前景色（即文本的颜色）
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
        // 设置背景色
        BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.CYAN);
        // 设置尺寸（相对大小：相对原始字号增大2倍）
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(2.0F);
        // 设置尺寸（绝对大小：像素）
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(30);

        SpannableString ss2 = new SpannableString(text);
        ss2.setSpan(foregroundSpan, 0, 2, 0);
        ss2.setSpan(backgroundSpan, 2, 4, 0);
        ss2.setSpan(relativeSizeSpan, 4, 6, 0);
        ss2.setSpan(absoluteSizeSpan, 6, 8, 0);

        binding.tvTextStyle.setText(ss2);

        /*
         * 点击事件
         */
        ClickableSpan clickableSpan = new ClickableSpan() {

            @Override
            public void onClick(@NonNull View widget) {
                /* 设置点击效果 */
                Log.i(TAG, "ClickableSpan-OnClick.");
                Toast.makeText(getApplicationContext(), "已点击文字", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                // 设置文本颜色，覆盖默认样式。
                // ds.setColor(Color.YELLOW);
                // 设置下划线为不显示，覆盖默认样式。
                // ds.setUnderlineText(false);
            }
        };

        SpannableString ss3 = new SpannableString(text);
        ss3.setSpan(clickableSpan, 2, 6, 0);
        // TextView需要添加以下方法，才能使点击事件生效。
        binding.tvClick.setMovementMethod(LinkMovementMethod.getInstance());
        binding.tvClick.setText(ss3);
    }
}
