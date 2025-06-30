package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.bean.ItemBean;
// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.callback.TaskCallback;

interface IDownloadService33 {

    // 获取服务端进程ID
    int getPID();

    // 添加任务并开始下载
    void addTask(in ItemBean task);

    // 添加任务并开始下载（AIDL异步方法）
    oneway void addTaskAsync(in ItemBean task);

    // 获取任务列表
    List<ItemBean> getTasks();

    // 注册状态回调
    void setTaskCallback(in TaskCallback cb);
}
