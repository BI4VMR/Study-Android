package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.apkassets.DemoAPKAssetsUI;
import net.bi4vmr.study.apkraw.DemoAPKRawUI;
import net.bi4vmr.study.privateinner.DemoPrivateInnerUI;
import net.bi4vmr.study.privateoutter.DemoPrivateOutterUI;
import net.bi4vmr.study.publicdir.DemoPublicUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 读取raw目录资源
        Button btnAPKRaw = findViewById(R.id.btnAPKRaw);
        btnAPKRaw.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoAPKRawUI.class);
            startActivity(intent);
        });

        // 读取assets目录资源
        Button btnAPKAssets = findViewById(R.id.btnAPKAssets);
        btnAPKAssets.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoAPKAssetsUI.class);
            startActivity(intent);
        });

        // 读写应用私有数据（内部）
        Button btnPrivateInner = findViewById(R.id.btnPrivateInner);
        btnPrivateInner.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoPrivateInnerUI.class);
            startActivity(intent);
        });

        // 读写应用私有数据（外部）
        Button btnPrivateOutter = findViewById(R.id.btnPrivateOutter);
        btnPrivateOutter.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoPrivateOutterUI.class);
            startActivity(intent);
        });

        // 读写共享数据目录
        Button btnPublic = findViewById(R.id.btnPublic);
        btnPublic.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoPublicUI.class);
            startActivity(intent);
        });
    }
}
