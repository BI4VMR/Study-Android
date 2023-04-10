package net.bi4vmr.study.apkraw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.util.IOUtil;

import java.io.InputStream;

public class DemoAPKRawUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_apkraw);

        TextView tvInfo = findViewById(R.id.tvInfo);

        // 读取"raw/test.txt"
        InputStream is = getResources().openRawResource(R.raw.test);
        String content = IOUtil.getInstance().readFile(is);
        tvInfo.setText("test.txt: " + content);
        Log.i("myapp", "test.txt: " + content);
    }
}
