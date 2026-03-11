package net.bi4vmr.study.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver2 extends BroadcastReceiver {

    private static final String TAG = "TestApp-" + MyReceiver2.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Receiver2接收到广播。");
        // 截断广播
        abortBroadcast();
    }
}
