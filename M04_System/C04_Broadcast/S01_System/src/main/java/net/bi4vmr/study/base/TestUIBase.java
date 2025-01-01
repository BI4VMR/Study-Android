package net.bi4vmr.study.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TimeTickReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testui_base);

        // 创建过滤器，设置想要接收的广播类型。
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        // 创建接收器实例，并进行动态注册。
        receiver = new TimeTickReceiver();
        registerReceiver(receiver, filter);
        Log.i(TAG, "已注册TimeTickReceiver。");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 组件被销毁时，注销广播接收器。
        unregisterReceiver(receiver);
        Log.i(TAG, "已注销TimeTickReceiver。");
    }

    // 自定义广播接收器类，收到相应广播时执行"onReceive()"方法中的操作。
    static class TimeTickReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // 接收到广播时，需要执行的操作。
            Log.i(TAG, "Time Tick.");
        }
    }
}
