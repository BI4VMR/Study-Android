package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.togglebutton.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        ToggleButton button = findViewById(R.id.button);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * “选中状态改变”回调方法
             *
             * 当按钮的选中事件发生改变时，此回调被触发。
             *
             * @param buttonView 事件源。
             * @param isChecked 布尔值，表示按钮当前的选中状态。
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("Test", "按钮当前的选中状态：" + isChecked);
            }
        });
    }
}
