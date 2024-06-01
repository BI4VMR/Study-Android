package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.group_additem.DemoGroupAddItemUI;
import net.bi4vmr.study.group_choice.DemoGroupChoiceUI;
import net.bi4vmr.study.group_linefeed.DemoGroupLineFeedUI;
import net.bi4vmr.study.style.DemoStyleUI;

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

        // 外观定制
        Button btnStyle = findViewById(R.id.btnStyle);
        btnStyle.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoStyleUI.class);
            startActivity(intent);
        });

        // ChipGroup：换行显示
        Button btnGroupLineFeed = findViewById(R.id.btnGroupLineFeed);
        btnGroupLineFeed.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGroupLineFeedUI.class);
            startActivity(intent);
        });

        // ChipGroup：单选与多选
        Button btnGroupChoice = findViewById(R.id.btnGroupChoice);
        btnGroupChoice.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGroupChoiceUI.class);
            startActivity(intent);
        });

        // ChipGroup：动态创建表项
        Button btnGroupAddItem = findViewById(R.id.btnGroupAddItem);
        btnGroupAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGroupAddItemUI.class);
            startActivity(intent);
        });
    }
}
