package net.bi4vmr.study.style;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiStyleBinding;

/**
 * 测试界面：外观定制。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIStyle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIStyle.class.getSimpleName();

    private TestuiStyleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
