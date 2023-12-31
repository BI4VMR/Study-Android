package net.bi4vmr.study.gotoforresult2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.gotoforresult.ResultActivity;

public class DemoGotoForResult2UI extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> activityLauncher = getActivityResultLauncher();
    private final ActivityResultLauncher<Intent> activityLauncher2 = getActivityResultLauncher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_startforresult2);

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            // 启动新的Activity
            activityLauncher.launch(intent);
            activityLauncher2.launch(intent);
        });
    }

    // 获取ActivityResultLauncher的方法
    private ActivityResultLauncher<Intent> getActivityResultLauncher() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // 获取返回码
                        int resultCode = result.getResultCode();
                        Log.i("myapp", "OnActivityResult. ResultCode:" + resultCode);
                        // 判断为何种结果
                        if (resultCode == 999) {
                            // 获取结果
                            Intent data = result.getData();
                            if (data != null) {
                                String s = data.getStringExtra("RESULT");
                                Log.i("myapp", "OnActivityResult. Data:" + s);
                            }
                        }
                    }
                });
    }
}
