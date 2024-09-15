package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.coroutine.TestUICoroutineKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;

/**
 * 主页。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Kotlin协程
        binding.btnKTCoroutine.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUICoroutineKT.class);
            startActivity(intent);
        });
    }
}
