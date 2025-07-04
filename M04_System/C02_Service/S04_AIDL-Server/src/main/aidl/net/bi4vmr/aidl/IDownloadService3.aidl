package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.entity.DownloadItem;
// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.callback.TaskCallback;

interface IDownloadService3 {

    // 注册状态回调
    void setTaskCallback(in TaskCallback cb);

    // 添加任务并开始下载
    void addTask(in DownloadItem task);

    // 添加任务并开始下载（AIDL异步方法）
    oneway void addTaskOneway(in DownloadItem task);
}
