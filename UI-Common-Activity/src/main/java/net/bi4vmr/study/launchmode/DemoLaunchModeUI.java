package net.bi4vmr.study.launchmode;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoLaunchModeUI extends AppCompatActivity {

    private ActivityManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launchmodetest);

        am = getSystemService(ActivityManager.class);

        // 页面ID
        TextView tvID = findViewById(R.id.tvID);
        tvID.setText("起始页");

        // 启动Standard模式的界面
        Button btnStandard = findViewById(R.id.btnStandard);
        btnStandard.setOnClickListener(v -> {
            Intent intent = new Intent(this, StandardActivity.class);
            startActivity(intent);
        });

        // 启动SingleTop模式的界面
        Button btnSingleTop = findViewById(R.id.btnSingleTop);
        btnSingleTop.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleTopActivity.class);
            startActivity(intent);
        });

        // 启动SingleTask模式的界面
        Button btnSingleTask = findViewById(R.id.btnSingleTask);
        btnSingleTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleTaskActivity.class);
            startActivity(intent);
        });

        // 启动SingleInstance模式的界面
        Button btnSingleInstance = findViewById(R.id.btnSingleInstance);
        btnSingleInstance.setOnClickListener(v -> {
            Intent intent = new Intent(this, SingleInstanceActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String cmpName = DemoLaunchModeUI.class.getSimpleName();
        Log.i("myapp", cmpName + "所属的TaskID:" + getTaskId());
    }
}
