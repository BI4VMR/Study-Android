package net.bi4vmr.study.barrier;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class DemoBarrierUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_barrier);

        TextView tv2 = findViewById(R.id.tv2);
        ToggleButton btChange = findViewById(R.id.btChangeTV2);
        btChange.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tv2.setText("ABCDEFGHIJK");
            } else {
                tv2.setText("ABCD");
            }
        });
    }
}
