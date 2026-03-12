package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.MainActivityBinding;
import net.bi4vmr.study.datetime.TestUIDateTime;
import net.bi4vmr.study.datetime.TestUIDateTimeKT;
import net.bi4vmr.study.deviceinfo.TestUIDeviceInfo;
import net.bi4vmr.study.deviceinfo.TestUIDeviceInfoKT;

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

        // 设备信息
        binding.btnDeviceInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDeviceInfo.class);
            startActivity(intent);
        });

        // 时间管理
        binding.btnDateTime.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDateTime.class);
            startActivity(intent);
        });

        // 设备信息(KT)
        binding.btnDeviceInfoKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDeviceInfoKT.class);
            startActivity(intent);
        });

        // 时间管理(KT)
        binding.btnDateTimeKT.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIDateTimeKT.class);
            startActivity(intent);
        });
    }
}
