package net.bi4vmr.study.gotoforresult;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIGotoForResult.class.getSimpleName();

    private ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnClose.setOnClickListener(v -> {
            // 从EditText获取字符
            String input = binding.etInfo.getText().toString();

            // 封装返回数据
            Intent intent = new Intent();
            intent.putExtra("RESULT", input);

            // 设置返回码和Intent实例
            setResult(999, intent);
            // 关闭当前Activity
            finish();
        });
    }
}
