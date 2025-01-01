package net.bi4vmr.study.dimen.base;

import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiDimenBaseBinding;

/**
 * 测试界面：尺寸 - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIDimenBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIDimenBase.class.getSimpleName();

    private TestuiDimenBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiDimenBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 将尺寸资源解析为像素值
        float sizePX = getResources().getDimension(R.dimen.title_size);
        binding.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, sizePX);
    }
}
