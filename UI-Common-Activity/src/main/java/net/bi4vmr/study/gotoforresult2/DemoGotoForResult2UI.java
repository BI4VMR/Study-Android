package net.bi4vmr.study.gotoforresult2;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoGotoForResult2UI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_gotopage);

        // 启动应用内的Activity
        Button btnStartInner = findViewById(R.id.btnStartInner);
        btnStartInner.setOnClickListener(null);
    }
}
