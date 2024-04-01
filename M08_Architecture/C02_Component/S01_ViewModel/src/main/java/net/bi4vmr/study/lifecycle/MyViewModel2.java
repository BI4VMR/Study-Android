package net.bi4vmr.study.lifecycle;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

/**
 * Name        : MyViewModel2
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 21:10
 * <p>
 * Description : ViewModel：生命周期测试。
 */
public class MyViewModel2 extends ViewModel {

    private static final String TAG = "TestApp-" + MyViewModel2.class.getSimpleName();

    // 实例ID
    public final String id;

    public MyViewModel2() {
        // 生成随机ID，标识当前实例。
        id = genRandomID();
        Log.i(TAG, "VM created. ID: " + id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "OnCleared. ID: " + id);
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
