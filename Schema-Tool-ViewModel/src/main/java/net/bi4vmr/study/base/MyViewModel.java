package net.bi4vmr.study.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

/**
 * Name        : MyViewModel
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-05-11 21:10
 * <p>
 * Description : 测试用ViewModel
 */
public class MyViewModel extends ViewModel {

    private String name;

    public MyViewModel() {
        name = genRandomID();
        Log.i("myapp", "MyViewModel-VM created. Name:" + name);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("myapp", "MyViewModel-OnCleared. Name:" + name);
    }

    public String getVMName() {
        return name;
    }

    // 获取随机ID
    private String genRandomID() {
        return UUID.randomUUID()
                .toString()
                .toUpperCase()
                .substring(0, 6);
    }
}
