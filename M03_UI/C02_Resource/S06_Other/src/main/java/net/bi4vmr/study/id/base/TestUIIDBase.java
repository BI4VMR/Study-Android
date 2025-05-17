package net.bi4vmr.study.id.base;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiIdBaseBinding;

/**
 * 测试界面：ID - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIIDBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIIDBase.class.getSimpleName();

    private TestuiIdBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiIdBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 根据ID获取控件
        Button button1 = findViewById(R.id.btnTest);
        button1.setOnClickListener(
                v -> Toast.makeText(this, "btnTest被点击了", Toast.LENGTH_SHORT).show()
        );

        // 使用代码设置控件的ID
        Button button2 = new Button(this);
        button2.setId(R.id.btnTest2);
        binding.container.addView(button2);

        // 根据ID获取控件
        Button button2_ID = findViewById(R.id.btnTest2);
        button2_ID.setOnClickListener(
                v -> Toast.makeText(this, "btnTest2被点击了", Toast.LENGTH_SHORT).show()
        );
    }
}
