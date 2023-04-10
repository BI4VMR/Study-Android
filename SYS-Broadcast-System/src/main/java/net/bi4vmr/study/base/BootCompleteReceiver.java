package net.bi4vmr.study.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 获取Action
        String action = intent.getAction();
        // 根据Action执行对应动作
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            // 接收到广播时，需要执行的操作。
            Log.i("myapp", "系统启动完成");
        }
    }
}
