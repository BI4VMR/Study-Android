package net.bi4vmr.study;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.analogclock.TestUIAnalogClock;
import net.bi4vmr.study.spannablestring.TestUISpannableString;
import net.bi4vmr.study.textclock.TestUITextClock;
import net.bi4vmr.study.view.TestUIView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TestApp","onCreate");
        Button btnSpannableString = findViewById(R.id.btnSpannableString);
        btnSpannableString.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUISpannableString.class);
            startActivity(intent);
        });

        Button btnTextClock = findViewById(R.id.btnTextClock);
        btnTextClock.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUITextClock.class);
            startActivity(intent);
        });

        Button btnAnalogClock = findViewById(R.id.btnAnalogClock);
        btnAnalogClock.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIAnalogClock.class);
            startActivity(intent);
        });

        Button btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestUIView.class);
            startActivity(intent);
            //
            // WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            //
            // final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            // drawableToFile(wallpaperDrawable, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/1.png", Bitmap.CompressFormat.PNG);
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleList l = newConfig.getLocales();
        Log.i("TestApp","onConfigurationChanged. list: "+l);
        for (int i = 0;i < l.size();i++ ){
            Log.i("TestApp","onConfigurationChanged. i: "+i +", c: "+l.get(i).getLanguage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TestApp","onDestroy.");
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
}
