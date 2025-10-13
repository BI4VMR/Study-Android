package net.bi4vmr.study.transform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;
import net.bi4vmr.study.base.TestUIBase;

public class TestUITransform extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_transform);

        // 获取当前Activity的MyViewModel2实例
        MyViewModel2 vm = new ViewModelProvider(this).get(MyViewModel2.class);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        TextView tvNum = findViewById(R.id.tvNum);
        TextView tvSquared = findViewById(R.id.tvSquared);
        TextView tvSum = findViewById(R.id.tvSum);

        btnPlus.setOnClickListener(v -> vm.plus());
        btnMinus.setOnClickListener(v -> vm.minus());

        // 观察原始数值的变化
        vm.numberData.observe(this, integer -> {
            Log.i(TAG, "Number LiveData数值改变：" + integer);
            tvNum.setText("原始数值:" + integer);
        });

        // 观察平方数值的变化
        vm.squaredData.observe(this, integer -> {
            Log.i(TAG, "Squared LiveData数值改变：" + integer);
            tvSquared.setText("平方数值:" + integer);
        });

        // 观察上述两个LiveData数值之和的变化
        vm.mediatorData.observe(this, integer -> {
            Log.i(TAG, "Mediator LiveData数值改变：" + integer);
            tvSum.setText("两者之和:" + integer);
        });
    }
}
