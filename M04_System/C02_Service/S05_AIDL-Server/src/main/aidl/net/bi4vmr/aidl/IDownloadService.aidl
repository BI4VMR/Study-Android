package net.bi4vmr.aidl;

interface IDownloadService {

    // 获取服务端进程ID
    int getPID();

    // 添加任务并开始下载
    void addTask(in String url);

    // 获取任务列表
    List<String> getTasks();
}
