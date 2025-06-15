package net.bi4vmr.study.transaction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：事务支持。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUITransaction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUITransaction.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
