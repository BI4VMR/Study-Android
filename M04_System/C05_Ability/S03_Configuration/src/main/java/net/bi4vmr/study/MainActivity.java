package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.properties_base.DemoPropertiesBaseUI;
import net.bi4vmr.study.settings_base.DemoSettingsBaseUI;
import net.bi4vmr.study.settings_observe.DemoSettingsObserveUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Settings-基本应用
        Button btnBase = findViewById(R.id.btnSettingsBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoSettingsBaseUI.class);
            startActivity(intent);
        });

        // Settings-监听配置项改变
        Button btnSettingObserve = findViewById(R.id.btnSettingsObserve);
        btnSettingObserve.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoSettingsObserveUI.class);
            startActivity(intent);
        });

        // Properties-基本应用
        Button btnPropertiesBase = findViewById(R.id.btnPropertiesBase);
        btnPropertiesBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoPropertiesBaseUI.class);
            startActivity(intent);
        });
    }
}
