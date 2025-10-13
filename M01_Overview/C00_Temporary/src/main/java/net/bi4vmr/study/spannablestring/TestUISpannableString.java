package net.bi4vmr.study.spannablestring;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUISpannableString extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_spannablestring);

        String text = "我能吞下玻璃而不伤身体";

        /*
         * 基本应用
         */
        // 创建SpannableString对实例，并设置初始内容。
        SpannableString ss1 = new SpannableString(text);

        /*
         * Name        : 设置样式
         *
         * Description : 设置文本样式。
         *
         * @param what  样式类型，内置样式名称通常以"Span"结尾。
         *              每个Span实例只会被SpannableString应用一次，如果重复应用Span，则会导致其先前设置的样式
         *              被清除；如果我们不希望清空Span已经设置的样式，应当创建一个新的Span实例再应用到Spannable
         *              String中。
         * @param start 起始位置（包括）
         * @param end   终止位置（不包括）
         * @param flags 标志位
         *              以下标志位用于控制在Span区域前后插入文本时，是否需要也应用该样式。
         *              Spanned.SPAN_INCLUSIVE_INCLUSIVE - 包括起始与结束位置。
         *              Spanned.SPAN_INCLUSIVE_EXCLUSIVE - 包括起始位置，不包括结束位置。
         *              Spanned.SPAN_EXCLUSIVE_INCLUSIVE - 不包括起始位置，包括结束位置。
         *              Spanned.SPAN_EXCLUSIVE_EXCLUSIVE - 不包括起始与结束位置。
         */
        ss1.setSpan(new BackgroundColorSpan(Color.RED), 2, 6, 0);
        ss1.setSpan(new BackgroundColorSpan(Color.GREEN), 8, 10, 0);

        // 将SpannableString设置到TextView中。
        TextView tvDefault = findViewById(R.id.tvDefault);
        tvDefault.setText(ss1);

        /*
         * 文本样式
         */
        // 设置前景色（文字颜色）
        ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
        // 设置背景色
        BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.CYAN);
        // 设置字号（相对大小：相对原始字号的倍数）
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(2.0F);
        // 设置字号（绝对大小：像素）
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(30);

        SpannableString ss2 = new SpannableString(text);
        ss2.setSpan(foregroundSpan, 0, 2, 0);
        ss2.setSpan(backgroundSpan, 2, 4, 0);
        ss2.setSpan(relativeSizeSpan, 4, 6, 0);
        ss2.setSpan(absoluteSizeSpan, 6, 8, 0);

        TextView tvTextStyle = findViewById(R.id.tvTextStyle);
        tvTextStyle.setText(ss2);

        /*
         * 文本样式
         */
        // 点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                /* 设置点击效果 */
                Log.i("TestApp", "ClickableSpan.");
                Toast.makeText(getApplicationContext(), "已点击文字", Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                // 设置文本颜色，覆盖默认样式。
                ds.setColor(Color.YELLOW);
                // 设置下划线为不显示，覆盖默认样式。
                ds.setUnderlineText(false);
            }
        };

        SpannableString ss3 = new SpannableString(text);
        ss3.setSpan(clickableSpan, 2, 6, 0);
        TextView tvClick = findViewById(R.id.tvClick);
        // TextView需要添加以下方法，才能使点击事件生效。
        tvClick.setMovementMethod(LinkMovementMethod.getInstance());
        tvClick.setText(ss3);
    }
}
