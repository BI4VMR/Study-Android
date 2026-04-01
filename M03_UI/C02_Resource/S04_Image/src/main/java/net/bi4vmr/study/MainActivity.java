package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.levellist.TestUILevelList;
import net.bi4vmr.study.levellist.TestUILevelListKT;

/**
 * 主页。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // LevelListDrawable
        binding.btnLevelList.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILevelList.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // LevelListDrawable(KT)
        binding.btnLevelListKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILevelListKT.class);
            startActivity(intent);
        });
    }
}
