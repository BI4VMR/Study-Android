package net.bi4vmr.study.initdata;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.bi4vmr.study.R;

public class DemoInitDataUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_initdata);

        // 创建Fragment实例
        TestFragment fragment = TestFragment.newInstance("初始参数");
        // 获取FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();
        // 获取Fragment事务实例
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加Fragment
        transaction.add(R.id.container, fragment);
        // 提交事务
        transaction.commit();
    }
}
