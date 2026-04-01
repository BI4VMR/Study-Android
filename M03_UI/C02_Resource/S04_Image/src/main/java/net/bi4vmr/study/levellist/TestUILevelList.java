package net.bi4vmr.study.levellist;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiLevellistBinding;

/**
 * 测试界面：LevelListDrawable。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILevelList extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILevelList.class.getSimpleName();

    private TestuiLevellistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLevellistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.radiogroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_level0) {
                binding.imageview.setImageLevel(0);
            } else if (checkedId == R.id.rb_level1) {
                binding.imageview.setImageLevel(1);
            } else if (checkedId == R.id.rb_level2) {
                binding.imageview.setImageLevel(2);
            } else if (checkedId == R.id.rb_level3) {
                binding.imageview.setImageLevel(3);
            } else if (checkedId == R.id.rb_level4) {
                binding.imageview.setImageLevel(4);
            }
        });
    }

    private void test() {
        Log.i(TAG, "----- 通过代码解析XML获取LevelListDrawable -----");

        // 通过代码解析XML获取LevelListDrawable实例
        LevelListDrawable drawable = (LevelListDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_wlan, getTheme());
        if (drawable != null) {
            // 获取当前Level
            drawable.getLevel();

            // 设置新的Level
            drawable.setLevel(5);
        }

        // 将Drawable设置到ImageView中
        binding.imageview.setImageDrawable(drawable);
    }
}
