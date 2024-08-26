package net.bi4vmr.study.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

/**
 * 自定义ViewModel。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class MyViewModel extends ViewModel {

    private static final String TAG = "TestApp-" + TestFragment.class.getSimpleName();

    // 实例ID
    public final String id;
    // 共享数据
    private int num = 0;

    public MyViewModel() {
        // 生成随机ID，标识当前实例。
        id = genRandomID();
        Log.i(TAG, "VM created. ID:[" + id + "]");
    }

    // 读取共享数据
    public int getNum() {
        return num;
    }

    // 写入共享数据
    public void setNum(int num) {
        this.num = num;
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
