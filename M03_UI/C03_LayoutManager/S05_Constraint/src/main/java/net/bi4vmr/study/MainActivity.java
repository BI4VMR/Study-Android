package net.bi4vmr.study;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.barrier.DemoBarrierUI;
import net.bi4vmr.study.base.DemoBaseUI;
import net.bi4vmr.study.bias.DemoBiasUI;
import net.bi4vmr.study.chainconstraint.DemoChainConstraintUI;
import net.bi4vmr.study.changeconstraints.DemoChangeConstraintsUI;
import net.bi4vmr.study.circleconstraint.DemoCircleConstraintUI;
import net.bi4vmr.study.gonemargin.DemoGoneMarginUI;
import net.bi4vmr.study.guideline.DemoGuideLineUI;
import net.bi4vmr.study.sizeconstraint.DemoSizeConstraintUI;

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

        // 乖离率
        Button btnBias = findViewById(R.id.btnBias);
        btnBias.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBiasUI.class);
            startActivity(intent);
        });

        // 尺寸约束
        Button btnWHConstraint = findViewById(R.id.btnWHConstraint);
        btnWHConstraint.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoSizeConstraintUI.class);
            startActivity(intent);
        });

        // 链式约束
        Button btnChainConstraint = findViewById(R.id.btnChainConstraint);
        btnChainConstraint.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoChainConstraintUI.class);
            startActivity(intent);
        });

        // 角度约束
        Button btnCircleConstraint = findViewById(R.id.btnCircleConstraint);
        btnCircleConstraint.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoCircleConstraintUI.class);
            startActivity(intent);
        });

        // GoneMargin属性
        Button btnGoneMargin = findViewById(R.id.btnGoneMargin);
        btnGoneMargin.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGoneMarginUI.class);
            startActivity(intent);
        });

        // 辅助线
        Button btnGuideLine = findViewById(R.id.btnGuideLine);
        btnGuideLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoGuideLineUI.class);
            startActivity(intent);
        });

        // 屏障
        Button btnBarrier = findViewById(R.id.btnBarrier);
        btnBarrier.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoBarrierUI.class);
            startActivity(intent);
        });

        // 动态调整约束条件
        Button btnChangeConstraints = findViewById(R.id.btnChangeConstraints);
        btnChangeConstraints.setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoChangeConstraintsUI.class);
            startActivity(intent);
        });
    }
}
