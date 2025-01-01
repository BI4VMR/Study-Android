package net.bi4vmr.study.themedark;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiThemeDarkBinding;

/**
 * 测试界面：深色模式。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIThemeDark extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIThemeDark.class.getSimpleName();

    private TestuiThemeDarkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = TestuiThemeDarkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // binding.btnSwitchTheme.setOnClickListener(v -> switchTheme());
        binding.btnSwitchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.vvv.setBackgroundResource(R.drawable.widget_list_item_bg);
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        binding.vvv.setBackgroundResource(R.drawable.widget_list_item_bg);
    }

    // 切换深色模式
    private void switchTheme() {
        // Intent intent = new Intent();
        // intent.putExtra("Theme", themeType = (themeType == 0) ? 1 : 0);
        // intent.setClass(this, this.getClass());
        // // 禁止启动动画（可选）
        // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        // startActivity(intent);
        // // 在某些设备上"FLAG_ACTIVITY_NO_ANIMATION"无效，需要额外添加此配置。
        // overridePendingTransition(0, 0);
        //
        // finish();
    }
}
