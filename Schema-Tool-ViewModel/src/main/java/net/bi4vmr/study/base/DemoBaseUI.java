package net.bi4vmr.study.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        Button btnPop = findViewById(R.id.btnPop);

        // 从当前Activity中获取VM实例
        MyViewModel vm = new ViewModelProvider(this).get(MyViewModel.class);
        Log.i("myapp", "DemoBaseUI-Get VM in Activity:" + vm.getVMName());

        // 初始化Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new TestFragment())
                .addToBackStack(null)
                .commit();

        btnPop.setOnClickListener(v -> {
            getSupportFragmentManager().popBackStack();
        });
    }
}
