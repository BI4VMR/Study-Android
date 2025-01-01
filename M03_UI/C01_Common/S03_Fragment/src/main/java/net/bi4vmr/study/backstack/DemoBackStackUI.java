package net.bi4vmr.study.backstack;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import net.bi4vmr.study.R;

import java.util.List;
import java.util.UUID;

public class DemoBackStackUI extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_backstack);

        Button btnGet = findViewById(R.id.btnGet);
        Button btnReload = findViewById(R.id.btnReload);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnBackToA = findViewById(R.id.btnBackToA);
        Button btnBackToB = findViewById(R.id.btnBackToB);

        // 初始化FragmentManager
        fragmentManager = getSupportFragmentManager();
        initFragments();

        // 获取所有Fragment的状态
        btnGet.setOnClickListener(v -> {
            // 获取FragmentManager实例
            FragmentManager fm = getSupportFragmentManager();
            List<Fragment> fmList = fm.getFragments();
            Log.i("myapp", "Fragment count is " + fmList.size());
            for (int i = 0; i < fmList.size(); i++) {
                Fragment f = fmList.get(i);
                if (f instanceof TestFragment) {
                    TestFragment tf = (TestFragment) f;
                    Log.i("myapp", "Fragment " + i + " is " + tf.getName());
                }
            }
        });

        // 重置
        btnReload.setOnClickListener(v -> initFragments());

        // 回退
        btnBack.setOnClickListener(v -> fragmentManager.popBackStack());

        // 回退至状态A
        btnBackToA.setOnClickListener(v -> {
            // 回退至状态A之前的状态。
            fragmentManager.popBackStack("StateA", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        // 回退至状态B
        btnBackToB.setOnClickListener(v -> {
            // 回退至状态B。
            fragmentManager.popBackStack("StateB", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });
    }

    private void initFragments() {
        // 清空所有页面
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        // 构造测试数据
        fragmentManager.beginTransaction()
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .addToBackStack(null)
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .addToBackStack("StateA")
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .addToBackStack("StateB")
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .addToBackStack("StateB")
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .add(R.id.container, TestFragment.newInstance(genRandomID()))
                .addToBackStack("StateA")
                .commit();
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
