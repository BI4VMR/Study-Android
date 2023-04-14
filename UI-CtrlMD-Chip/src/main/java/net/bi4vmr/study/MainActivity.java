package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.chipgroupchoice.DemoChipGroupChoiceUI;
import net.bi4vmr.study.chipgrouplinefeed.DemoChipGroupLineFeedUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 基本应用
        Button btnBase = findViewById(R.id.btnBase);
        btnBase.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBaseUI.class);
            startActivity(intent);
        });

        // ChipGroup：换行显示
        Button btnChipGroupLineFeed = findViewById(R.id.btnChipGroupLineFeed);
        btnChipGroupLineFeed.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoChipGroupLineFeedUI.class);
            startActivity(intent);
        });

        // ChipGroup：单选与多选
        Button btnChipGroupChoice = findViewById(R.id.btnChipGroupChoice);
        btnChipGroupChoice.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoChipGroupChoiceUI.class);
            startActivity(intent);
        });
    }
}
