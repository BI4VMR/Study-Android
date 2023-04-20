package net.bi4vmr.study.manage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import net.bi4vmr.study.R;

import java.util.List;
import java.util.UUID;

public class DemoManageUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_manage);

        Button btnGet = findViewById(R.id.btnGet);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnReplace = findViewById(R.id.btnReplace);
        Button btnBack = findViewById(R.id.btnBack);

        // 获取容器状态
        btnGet.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            List<Fragment> fmList = manager.getFragments();
            Log.i("myapp", fmList.toString());
        });


        // 向容器中添加Fragment
        btnAdd.setOnClickListener(v -> {
            // 创建Fragment实例
            TestFragment fragment = TestFragment.newInstance(genRandomID());
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 添加Fragment
            transaction.add(R.id.container, fragment);
            transaction.addToBackStack("");
            // 提交事务
            transaction.commit();
        });

        // 替换容器中的Fragment
        btnReplace.setOnClickListener(v -> {
            // 创建Fragment实例
            TestFragment fragment = TestFragment.newInstance(genRandomID());
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 添加Fragment
            transaction.replace(R.id.container, fragment);
            // transaction.addToBackStack("");
            // 提交事务
            transaction.commit();
        });

        // 回退
        btnBack.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // manager.popBackStack();
            manager.popBackStack("AAA", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            manager.popBackStack("AAA", 0);
        });

        // 创建Fragment实例
        TestFragment fragment = TestFragment.newInstance(genRandomID());
        // 获取FragmentManager实例
        FragmentManager manager = getSupportFragmentManager();
        // 获取Fragment事务实例
        FragmentTransaction transaction = manager.beginTransaction();
        // 添加Fragment
        transaction.add(R.id.container, fragment, "AAA");
        transaction.addToBackStack("");
        // 提交事务
        transaction.commit();
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
