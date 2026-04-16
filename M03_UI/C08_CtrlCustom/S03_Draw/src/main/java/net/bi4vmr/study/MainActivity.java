package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIBase;
import net.bi4vmr.study.base.TestUIBaseKT;
import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.line.TestUILine;
import net.bi4vmr.study.line.TestUILineKT;
import net.bi4vmr.study.path.TestUIPath;
import net.bi4vmr.study.path.TestUIPathKT;
import net.bi4vmr.study.point.TestUIPoint;
import net.bi4vmr.study.point.TestUIPointKT;
import net.bi4vmr.study.shader.TestUIShader;
import net.bi4vmr.study.shader.TestUIShaderKT;
import net.bi4vmr.study.shape.TestUIShape;
import net.bi4vmr.study.shape.TestUIShapeKT;
import net.bi4vmr.study.text.TestUIText;
import net.bi4vmr.study.text.TestUITextKT;

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

        // 点
        binding.btnPoint.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPoint.class);
            startActivity(intent);
        });

        // 直线
        binding.btnLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILine.class);
            startActivity(intent);
        });

        // 基本形状
        binding.btnShape.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShape.class);
            startActivity(intent);
        });

        // 路径
        binding.btnPath.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPath.class);
            startActivity(intent);
        });

        // 文本
        binding.btnText.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIText.class);
            startActivity(intent);
        });

        // Shader
        binding.btnShader.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShader.class);
            startActivity(intent);
        });

        // 基本应用(KT)
        binding.btnBaseKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIBaseKT.class);
            startActivity(intent);
        });

        // 点(KT)
        binding.btnPointKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPointKT.class);
            startActivity(intent);
        });

        // 直线(KT)
        binding.btnLineKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUILineKT.class);
            startActivity(intent);
        });

        // 基本形状(KT)
        binding.btnShapeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShapeKT.class);
            startActivity(intent);
        });

        // 路径(KT)
        binding.btnPathKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIPathKT.class);
            startActivity(intent);
        });

        // 文本(KT)
        binding.btnTextKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITextKT.class);
            startActivity(intent);
        });

        // Shader(KT)
        binding.btnShaderKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIShaderKT.class);
            startActivity(intent);
        });
    }
}
