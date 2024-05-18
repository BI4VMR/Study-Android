package net.bi4vmr.study.base;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

import java.util.List;

public class DemoBaseUI extends AppCompatActivity {

    private PackageManager packageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 通过Context对象获取PackageManager实例
        packageManager = getPackageManager();

        List<ApplicationInfo> a=packageManager.getInstalledApplications(0);
        for (int i = 0;i<a.size();i++){
            Log.i("myapp","aaa: "+a.get(i).packageName);
        }
    }
}
