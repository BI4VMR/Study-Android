package net.bi4vmr.study.apkassets;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.util.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class DemoAPKAssetsUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_apkassets);

        TextView tvInfo = findViewById(R.id.tvInfo);

        // 获取AssetManager
        AssetManager am = getApplicationContext().getAssets();
        try {
            // 查看"config"目录下的子项
            String[] filenames = am.list("config");
            tvInfo.setText("files: " + Arrays.toString(filenames));
            Log.i("myapp", "files: " + Arrays.toString(filenames));
            // 依次读取每个文件的内容
            for (String file : filenames) {
                InputStream is = am.open("config/" + file);
                String content = IOUtil.getInstance().readFile(is);
                tvInfo.append("\n" + file + ": " + content);
                Log.i("myapp", file + ": " + content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭AssetManager
        am.close();
    }
}
