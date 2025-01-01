package net.bi4vmr.study.base;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnRequest.setOnClickListener(v -> testRequest());
    }

    private void testRequest() {
        if (checkPermission()) {
            Log.i(TAG, "已拥有该权限，可以访问位置信息。");
            // 相关业务操作 ...
        } else {
            // 准备需要请求的权限列表
            String[] reqParams = new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION};
            // 请求权限
            requestPermissions(reqParams, 100);
        }
    }

    // 检查是否拥有某项权限
    private boolean checkPermission() {
        int code = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        Log.i(TAG, "Check result code:[" + code + "]");
        if (code == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "已拥有该权限。");
            return true;
        } else {
            Log.i(TAG, "未拥有该权限，或无法识别授权状态。");
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "OnRequestPermissionsResult. Request: " + requestCode);
        for (int i = 0; i < permissions.length; i++) {
            Log.i(TAG, permissions[i] + " | " + grantResults[i]);
            // 判断“请求精确位置”权限是否被用户拒绝
            if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION) && (grantResults[i] == PackageManager.PERMISSION_DENIED)) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Log.i(TAG, "用户不允许授权并设为不再提示。");

                    // 跳转至当前应用的详情页面
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        }
    }
}
