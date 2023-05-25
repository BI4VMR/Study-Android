package net.bi4vmr.study.transform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

/**
 * Name        : DemoBaseUI
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 14:51
 * <p>
 * Description : 示例首页：基本应用。
 */
public class DemoTransformUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_transform);

        // 获取当前Activity对应的ViewModel实例
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);

        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        TextView tvNum = findViewById(R.id.tvNum);
        TextView tvSquared = findViewById(R.id.tvSquared);
        TextView tvSum = findViewById(R.id.tvSum);

        btnPlus.setOnClickListener(v -> vm.plus());
        btnMinus.setOnClickListener(v -> vm.minus());

        // 观察原始数值的变化
        vm.numberData.observe(this, integer -> {
            Log.i("myapp", "Number LiveData数值改变：" + integer);
            tvNum.setText("原始数值:" + integer);
        });

        // 观察平方数值的变化
        vm.squaredData.observe(this, integer -> {
            Log.i("myapp", "Squared LiveData数值改变：" + integer);
            tvSquared.setText("平方数值:" + integer);
        });

        // 观察两个LiveData数值之和的变化
        vm.mediatorData.observe(this, integer -> {
            Log.i("myapp", "Mediator LiveData数值改变：" + integer);
            tvSum.setText("两者之和:" + integer);
        });
    }
}
