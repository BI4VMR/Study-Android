package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.entity.DownloadItemKT;
// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.callback.TaskCallbackKT;

interface IDownloadService3KT {

    // 添加任务并开始下载
    void addTask(in DownloadItemKT task);

    // 添加任务并开始下载（AIDL异步方法）
    oneway void addTaskOneway(in DownloadItemKT task);

    // 注册状态回调
    void setTaskCallback(in TaskCallbackKT cb);
}
