package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.entity.DownloadItemKT;

interface IDownloadService2KT {

    // 添加任务并开始下载
    void addTask(in DownloadItemKT task);

    // 获取任务列表
    List<DownloadItemKT> getTasks();
}
