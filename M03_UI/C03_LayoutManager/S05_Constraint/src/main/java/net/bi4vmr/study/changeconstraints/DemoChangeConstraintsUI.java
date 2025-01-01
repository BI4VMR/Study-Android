package net.bi4vmr.study.changeconstraints;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import net.bi4vmr.study.R;

public class DemoChangeConstraintsUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_changeconstraints);

        Button btChange = findViewById(R.id.btChange);
        Button btAdd = findViewById(R.id.btAdd);
        Button btReset = findViewById(R.id.btReset);
        ConstraintLayout layoutRoot = findViewById(R.id.layoutRoot);
        // 备份原始约束属性
        ConstraintSet rawCSet = new ConstraintSet();
        rawCSet.clone(layoutRoot);

        // 改变约束属性按钮
        btChange.setOnClickListener(v -> {
            // 创建ConstraintSet对象
            ConstraintSet cSet = new ConstraintSet();
            // 从根布局复制当前约束条件
            cSet.clone(layoutRoot);
            // 修改水平乖离率
            cSet.setHorizontalBias(R.id.view1, 0.25F);
            // 添加控件顶部的外边距（像素）
            cSet.setMargin(R.id.view1, ConstraintSet.TOP, 50);
            // 将修改后的约束条件设置到根布局上。
            cSet.applyTo(layoutRoot);
        });

        // 添加约束属性按钮
        btAdd.setOnClickListener(v -> {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(layoutRoot);
            // 添加约束条件，将View1的底部约束至根布局的底部
            cSet.connect(R.id.view1, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
            cSet.applyTo(layoutRoot);
        });

        // 重置按钮
        btReset.setOnClickListener(v -> {
            ConstraintSet cSet = new ConstraintSet();
            cSet.clone(rawCSet);
            cSet.applyTo(layoutRoot);
        });
    }
}
