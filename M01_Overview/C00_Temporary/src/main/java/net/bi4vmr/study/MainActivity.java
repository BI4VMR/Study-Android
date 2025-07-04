package net.bi4vmr.study;

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
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bi4vmr.study.databinding.ActivityMainBinding;
import net.bi4vmr.study.permission.AospPermissionMgr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        // getLanguages().forEach(locale -> {
        //     // Log.d("TestApp","locale: "+ locale);
        // });

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
            AospPermissionMgr mgr = AospPermissionMgr.getInstance(this);
            mgr.startListenPermissionChange2(pkg -> Log.d("TestApp", "pkg: " + pkg));

            // Intent intent = new Intent(this, TestUIView.class);
            // startActivity(intent);
            //
            // WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            //
            // final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            // drawableToFile(wallpaperDrawable, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/1.png", Bitmap.CompressFormat.PNG);

            // TaskUtil.INSTANCE.getbgapps(getApplicationContext());
            // Log.d("TestAPP", "AppEnabled:" + isAppEnabled(this, "com.android.nfc"));
        });

        LevelListDrawable drawable = (LevelListDrawable) getResources().getDrawable(R.drawable.ic_status_wlan);
        drawable.setAutoMirrored(true);
        drawable.setLevel(0);
        binding.ivDraw.setImageDrawable(drawable);
        // binding.ivDraw.setImageResource(R.drawable.ic_status_wlan_disconnected);

        binding.ivDraw.setOnClickListener(v -> {
            View v2 = LayoutInflater.from(this).inflate(R.layout.popup_subjects, null);
            PopupWindow window = new PopupWindow(v2, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            window.setFocusable(true);
            window.update();
            window.setOverlapAnchor(true);
            window.showAsDropDown(v);
            // window.showAtLocation(v, Gravity.BOTTOM, 0, 0);

        });

        // Log.d("Test123", "addOnActiveSessionsChangedListener: ");
        // MediaSessionManager manager = getSystemService(MediaSessionManager.class);
        // manager.addOnActiveSessionsChangedListener(new MediaSessionManager.OnActiveSessionsChangedListener() {
        //     @Override
        //     public void onActiveSessionsChanged(@Nullable List<MediaController> controllers) {
        //         if (controllers != null) {
        //             for (MediaController controller : controllers) {
        //                 Log.d("Test123", "onActiveSessionsChanged: " + controller.getPackageName());
        //
        //             }
        //         }
        //     }
        // }, null);

        ExoPlayer player = new ExoPlayer.Builder(this)
                .build();
        binding.playerView.hideController();
        binding.playerView.setPlayer(player);

        // String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/ba";
        // MediaItem item = MediaItem.fromUri(uri);
        // player.setMediaItem(item);

        // player.prepare();
        // player.play();

        List<VO> list = new ArrayList<>();
        list.add(new VO("表项A", R.drawable.ic_funny_256));
        list.add(new VO("表项B", R.drawable.ic_status_wlan_level1));
        list.add(new VO("表项C", R.drawable.ic_status_wlan_level3));
        MyAdapter adapter = new MyAdapter();
        adapter.submit(list);

        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setAdapter(adapter);
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

    /**
     * 获取当前系统支持的语言集
     *
     * @return
     */
    private List<String> getLanguages() {
        List<String> list = new ArrayList<>();
        Locale[] lg = Locale.getAvailableLocales();
        for (Locale language : lg) {
            String name = language.getDisplayLanguage();
            // 去掉重复的语言
            // if (!list.contains(name)){
            list.add(name);
            // }
            Log.d("TestApp", "locale: " + language.toLanguageTag());
        }
        return list;
    }

    /**
     * 获取当前系统语言
     * * settings get system system_locales
     *
     * @return
     */
    private String getCurrentLanguage() {
        Locale locale = getResources().getConfiguration().locale;
        return locale.getDisplayLanguage();
    }

}
