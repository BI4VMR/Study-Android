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
        Button btnRemove = findViewById(R.id.btnRemove);
        Button btnReplace = findViewById(R.id.btnReplace);
        Button btnShow = findViewById(R.id.btnShow);
        Button btnHide = findViewById(R.id.btnHide);
        Button btnAttach = findViewById(R.id.btnAttach);
        Button btnDetach = findViewById(R.id.btnDetach);

        // 测试Fragment实例
        Fragment fragment = TestFragment.newInstance(genRandomID());

        // 获取容器状态
        btnGet.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager fm = getSupportFragmentManager();
            List<Fragment> fmList = fm.getFragments();
            Log.i("myapp", "Fragment count is " + fmList.size());
            for (int i = 0; i < fmList.size(); i++) {
                Fragment f = fmList.get(i);
                if (f instanceof net.bi4vmr.study.backstack.TestFragment) {
                    net.bi4vmr.study.backstack.TestFragment tf = (net.bi4vmr.study.backstack.TestFragment) f;
                    Log.i("myapp", "Fragment " + i + " is " + tf.getName());
                }
            }
        });

        // 向容器中添加Fragment
        btnAdd.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 添加Fragment
            transaction.add(R.id.container, fragment);
            // 提交事务
            transaction.commit();
        });

        // 移除容器中的Fragment
        btnRemove.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 移除Fragment
            transaction.remove(fragment);
            // 提交事务
            transaction.commit();
        });

        // 替换容器中的Fragment
        btnReplace.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 替换Fragment
            transaction.replace(R.id.container, TestFragment.newInstance(genRandomID()));
            // 提交事务
            transaction.commit();
        });

        // 显示指定的Fragment
        btnShow.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 显示Fragment
            transaction.show(fragment);
            // 提交事务
            transaction.commitNow();
            // 查看状态
            Log.i("myapp", "Fragment隐藏状态：" + fragment.isHidden());
        });

        // 隐藏指定的Fragment
        btnHide.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 隐藏Fragment
            transaction.hide(fragment);
            // 提交事务
            transaction.commitNow();
            // 查看状态
            Log.i("myapp", "Fragment隐藏状态：" + fragment.isHidden());
        });

        // 附加指定的Fragment
        btnAttach.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 隐藏Fragment
            transaction.attach(fragment);
            // 提交事务
            transaction.commit();
        });

        // 分离指定的Fragment
        btnDetach.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager manager = getSupportFragmentManager();
            // 获取Fragment事务实例
            FragmentTransaction transaction = manager.beginTransaction();
            // 隐藏Fragment
            transaction.detach(fragment);
            // 提交事务
            transaction.commit();
        });
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
