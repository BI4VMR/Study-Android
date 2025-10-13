package net.bi4vmr.aidl.callback;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.bean.ItemBean;

interface TaskCallback {

    // 进度改变事件
    void onStateChanged(in ItemBean item);
}
