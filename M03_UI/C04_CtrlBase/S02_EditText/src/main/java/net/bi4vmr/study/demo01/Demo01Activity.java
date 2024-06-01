package net.bi4vmr.study.demo01;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.ui.ctrlbase.edittext.R;

public class Demo01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_01);

        EditText edittext = findViewById(R.id.edittext);

        // 构造文本类型标志位
        int inputType = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL
                | InputType.TYPE_NUMBER_FLAG_SIGNED;
        // 设置文本类型
        edittext.setInputType(inputType);
    }
}
