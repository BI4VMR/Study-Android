package net.bi4vmr.aidl;

interface IFileService {

    // 客户端向服务端上传文件
    void upload(in ParcelFileDescriptor pfd);

    // 客户端从服务端下载文件
    void download(in ParcelFileDescriptor pfd);
}
