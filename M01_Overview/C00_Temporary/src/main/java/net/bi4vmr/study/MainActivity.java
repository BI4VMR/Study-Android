package net.bi4vmr.study;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    public boolean isAppEnabled(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            return applicationInfo.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            // 应用未安装
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 创建IntentFilter并添加ACTION_PACKAGE_CHANGED
        // IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_CHANGED);
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addDataScheme("package");
        // 注册BroadcastReceiver
        registerReceiver(new PackageChangedReceiver(), filter);

        Button btnTextClock = findViewById(R.id.btnTextClock);
        btnTextClock.setOnClickListener(v -> {
            // Intent intent = new Intent(this, TestUITextClock.class);
            // startActivity(intent);
            Log.d("TestAPP", "AppEnabled:" + isAppEnabled(this, "com.android.nfc"));
        });

        Button btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(v -> {
            // Intent intent = new Intent(this, TestUIView.class);
            // startActivity(intent);
            //
            // WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            //
            // final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            // drawableToFile(wallpaperDrawable, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/1.png", Bitmap.CompressFormat.PNG);

            // TaskUtil.INSTANCE.getbgapps(getApplicationContext());
            // Log.d("TestAPP", "AppEnabled:" + isAppEnabled(this, "com.android.nfc"));
            int userID = UserManagerExtend.getInstance(getApplicationContext()).getCurrentUserID();
            Log.d("TestAPP", "userID:" +userID +"          -- :"+currentApplication());

        });

        // LevelListDrawable drawable = (LevelListDrawable) getResources().getDrawable(R.drawable.ic_status_wlan);
        // drawable.setLevel(2);
        // Drawable d = drawable;
        // Log.i("TestApp", "d list: " + d.getLevel());
        // binding.ivDraw.setImageDrawable(drawable);
        // // binding.ivDraw.getDrawable().getLevel();
        // Log.i("TestApp", "d2 list: " + binding.ivDraw.getDrawable().getLevel());
    }
    public static Application currentApplication() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method application = activityThreadClass.getDeclaredMethod("currentApplication");
            application.setAccessible(true);
            return (Application) application.invoke(null);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleList l = newConfig.getLocales();
        Log.i("TestApp", "onConfigurationChanged. list: " + l);
        for (int i = 0; i < l.size(); i++) {
            Log.i("TestApp", "onConfigurationChanged. i: " + i + ", c: " + l.get(i).getLanguage());
        }
    }

    // Bitmap.CompressFormat.PNG
    public void drawableToFile(Drawable drawable, String filePath, Bitmap.CompressFormat format) {
        if (drawable == null)
            return;

        try {
            File file = new File(filePath);

            if (file.exists())
                file.delete();

            if (!file.exists())
                file.createNewFile();

            FileOutputStream out = null;
            out = new FileOutputStream(file);
            ((BitmapDrawable) drawable).getBitmap().compress(format, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class PackageChangedReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Log.d("TestAPP", "Package changed: " + packageName);
            try {
                boolean b2 = context.getPackageManager().getApplicationInfo(packageName, 0).enabled;
                Log.d("TestAPP", "Package state: " + b2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
