package net.bi4vmr.study.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        // 获取FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();
        // 获取Fragment事务实例
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加Fragment
        transaction.add(R.id.container2, new TestFragment());
        // 提交事务
        transaction.commit();
    }
}
