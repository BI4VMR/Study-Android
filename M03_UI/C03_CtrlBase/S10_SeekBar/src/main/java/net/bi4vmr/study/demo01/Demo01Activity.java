package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        SeekBar seekBar = findViewById(R.id.seekbar);
        TextView tvInfo = findViewById(R.id.tvProgress);
        Button btIncrease = findViewById(R.id.btIncrease);
        Button btDecrease = findViewById(R.id.btDecrease);

        // 点击此按钮进度+10
        btIncrease.setOnClickListener(v -> {
            int p = seekBar.getProgress();
            seekBar.setProgress(p + 10);
        });

        // 点击此按钮进度-10
        btDecrease.setOnClickListener(v -> {
            int p = seekBar.getProgress();
            seekBar.setProgress(p - 10);
        });

        // 设置OnSeekBarChangeListener监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("myapp", "onProgressChanged-progress:" + progress + " fromUser:" + fromUser);
                tvInfo.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i("myapp", "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("myapp", "onStopTrackingTouch");
            }
        });
    }
}
