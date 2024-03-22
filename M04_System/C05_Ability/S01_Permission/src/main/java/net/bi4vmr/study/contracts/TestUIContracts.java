package net.bi4vmr.study.contracts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

import java.util.Map;

public class TestUIContracts extends AppCompatActivity {

    private final ActivityResultLauncher<String> activityLauncher = getActivityResultLauncher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_base);

        Button btnRequest = findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(v -> {
            if (checkPermission()) {
                Log.i("myapp", "已拥有该权限，可以访问位置信息。");
                // 相关业务操作 ...
            } else {
                // 请求权限
                activityLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        });
    }

    // 检查是否拥有某项权限
    private boolean checkPermission() {
        int code = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        Log.i("myapp", "Check result code: " + code);
        if (code == PackageManager.PERMISSION_GRANTED) {
            Log.i("myapp", "已拥有该权限。");
            return true;
        } else {
            Log.i("myapp", "未拥有该权限，或无法识别授权状态。");
            return false;
        }
    }

    // 获取ActivityResultLauncher的方法
    private ActivityResultLauncher<String> getActivityResultLauncher() {
        return registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            Log.i("myapp", "用户已授予该权限。");
                        } else {
                            Log.i("myapp", "用户拒绝授予该权限。");
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                                Log.i("myapp", "用户不允许授权并设为不再提示。");
                            }
                        }
                    }
                });
    }

    // 获取ActivityResultLauncher的方法（多个权限）
    private ActivityResultLauncher<String[]> getActivityResultLauncher2() {
        return registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        // 多个权限的请求结果。其中Key为名称；Value为结果。
                    }
                });
    }
}
