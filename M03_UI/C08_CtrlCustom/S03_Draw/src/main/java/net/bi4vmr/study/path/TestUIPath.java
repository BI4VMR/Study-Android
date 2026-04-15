package net.bi4vmr.study.path;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiShapeBinding;

/**
 * 测试界面：路径。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIPath extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPath.class.getSimpleName();

    private TestuiShapeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiShapeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().post(() -> {
            test_Single();
        });
    }

    private void test_Single() {

    }
}
