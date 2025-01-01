package net.bi4vmr.study.androidvm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.bi4vmr.study.R;

public class TestUIAndroidVM extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIAndroidVM.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_androidvm);

        // 获取AndroidViewModel实例
        AndroidVM vm = new ViewModelProvider(this).get(AndroidVM.class);
        // 使用AndroidViewModel
        Context context = vm.getApplication().getApplicationContext();
        Log.i(TAG, "Get APPContext:" + context.toString());
    }
}
