package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.entity.DownloadItem;

interface IDownloadService2 {

    // 添加任务并开始下载
    void addTask(in DownloadItem task);

    // 获取任务列表
    List<DownloadItem> getTasks();
}
