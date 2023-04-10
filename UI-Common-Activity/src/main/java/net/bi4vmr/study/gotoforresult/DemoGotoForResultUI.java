package net.bi4vmr.study.gotoforresult;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoGotoForResultUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_gotopage);

        // 启动应用内的Activity
        Button btnStartInner = findViewById(R.id.btnStartInner);
        btnStartInner.setOnClickListener(null);
    }
}
