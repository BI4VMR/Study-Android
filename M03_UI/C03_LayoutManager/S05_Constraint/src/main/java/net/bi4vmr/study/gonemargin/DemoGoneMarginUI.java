package net.bi4vmr.study.gonemargin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoGoneMarginUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_gonemargin);

        TextView viewA = findViewById(R.id.viewA);
        TextView viewA2 = findViewById(R.id.viewA2);
        Button btnChange = findViewById(R.id.btnChange);
        btnChange.setOnClickListener(v -> {
            // 改变两个控件A的显示状态
            viewA.setVisibility(View.GONE);
            viewA2.setVisibility(View.GONE);
        });
    }
}
