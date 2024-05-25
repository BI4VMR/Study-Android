package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.xmlattrs.TestUIXMLAttrs;
import net.bi4vmr.study.xmlattrs.TestUIXMLAttrsKT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 基本应用
        binding.btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBase.class);
            startActivity(intent);
        });

        // XML属性
        binding.btnXMLAttrs.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIXMLAttrs.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // XML属性(KT)
        binding.btnXMLAttrsKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIXMLAttrsKT.class);
            startActivity(intent);
        });
    }
}
