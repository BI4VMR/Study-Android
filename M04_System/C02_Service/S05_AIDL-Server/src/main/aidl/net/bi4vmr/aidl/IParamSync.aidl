package net.bi4vmr.aidl;

// 该路径为对应AIDL文件所在的包
import net.bi4vmr.aidl.entity.DownloadItem;

interface IParamSync {

    // 添加任务并开始下载
    void modifyParams(in DownloadItem p1, out DownloadItem p2, inout DownloadItem p3);
}
