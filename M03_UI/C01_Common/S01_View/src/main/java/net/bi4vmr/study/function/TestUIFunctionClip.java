package net.bi4vmr.study.function;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiFunctionClipBinding;

/**
 * 测试界面：常用方法。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIFunctionClip extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunctionClip.class.getSimpleName();

    private TestuiFunctionClipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionClipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewA1.setOnClickListener(v -> {
            Log.i(TAG, "点击了viewA1");
            v.setTranslationY(-25);
        });
    }
}
