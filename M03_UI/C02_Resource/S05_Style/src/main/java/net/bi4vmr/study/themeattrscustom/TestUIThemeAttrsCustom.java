package net.bi4vmr.study.themeattrscustom;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiThemeAttrsCustomBinding;

public class TestUIThemeAttrsCustom extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIThemeAttrsCustom.class.getSimpleName();

    private TestuiThemeAttrsCustomBinding binding;

    // 当前主题编号，默认为"0"。
    private int themeType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 根据外部传入的参数选择主题
        Intent inIntent = getIntent();
        if (inIntent != null) {
            int type = inIntent.getIntExtra("Theme", 0);
            if (type == 1) {
                setTheme(R.style.Theme_TypeB);
            } else {
                setTheme(R.style.Theme_TypeA);
            }
            themeType = type;
        }

        binding = TestuiThemeAttrsCustomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 通过代码引用主题属性，为控件设置颜色。
        TypedArray ta = null;
        try {
            ta = getTheme().obtainStyledAttributes(new int[]{R.attr.titleColor});
            int color = ta.getColor(0, Color.BLACK);
            binding.text2.setTextColor(color);
        } catch (Exception e) {
            Log.e(TAG, "Get color from attr failed!", e);
        } finally {
            if (ta != null) {
                ta.recycle();
            }
        }

        binding.btnSwitchTheme.setOnClickListener(v -> switchTheme());
    }

    // 切换主题
    private void switchTheme() {
        Intent intent = new Intent();
        intent.putExtra("Theme", themeType = (themeType == 0) ? 1 : 0);
        intent.setClass(this, this.getClass());
        // 禁止启动动画（可选）
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        // 在某些设备上"FLAG_ACTIVITY_NO_ANIMATION"无效，需要额外添加此配置。
        overridePendingTransition(0, 0);

        finish();
    }
}
