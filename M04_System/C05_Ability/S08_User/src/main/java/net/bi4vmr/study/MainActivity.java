package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.TestUIUserInfo;
import net.bi4vmr.study.base.TestUIUserInfoKT;
import net.bi4vmr.study.base.TestUIUserManager;
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

        // 用户身份API
        binding.btnUserInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUserInfo.class);
            startActivity(intent);
        });

        // 用户管理API
        binding.btnUserManager.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUserManager.class);
            startActivity(intent);
        });

        // 跨用户交互API
        // binding.btnAcrossUsers.setOnClickListener(v -> {
        //     Intent intent = new Intent(this, TestUIUserInfo.class);
        //     startActivity(intent);
        // });

        // 用户信息API(KT)
        binding.btnUserInfoKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIUserInfoKT.class);
            startActivity(intent);
        });

        // 用户管理API(KT)
        // binding.btnUserManager.setOnClickListener(v -> {
        //     Intent intent = new Intent(this, TestUIUserInfo.class);
        //     startActivity(intent);
        // });

        // 跨用户交互API(KT)
        // binding.btnAcrossUsers.setOnClickListener(v -> {
        //     Intent intent = new Intent(this, TestUIUserInfo.class);
        //     startActivity(intent);
        // });
    }
}
