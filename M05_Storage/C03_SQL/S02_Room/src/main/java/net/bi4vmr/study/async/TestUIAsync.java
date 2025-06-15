package net.bi4vmr.study.async;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：异步查询。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIAsync extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIAsync.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
