package net.bi4vmr.study.file;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import net.bi4vmr.aidl.IFileService;
import net.bi4vmr.study.util.IOUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Name        : 下载服务
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-20 14:05
 * <p>
 * Description : 下载服务。
 */
public class FileService extends Service {

    private static final String TAG = "TestApp-Client-" + FileService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new FileServiceImpl();
    }

    private class FileServiceImpl extends IFileService.Stub {

        @Override
        public void upload(ParcelFileDescriptor pfd) {
            Log.d(TAG, "upload server start ,pfd: " + pfd);
            FileDescriptor fd = pfd.getFileDescriptor();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(fd);
                String s = IOUtil.readFile(fis);
                Log.d(TAG, "     s   " + s);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtil.closeQuietly(fis);
            }
            Log.d(TAG, "upload server end");
        }

        @Override
        public void download(ParcelFileDescriptor pfd) {
            // 创建测试文件
            String path = FileService.this.getApplicationContext().getFilesDir() + "/download.txt";
            File file = new File(path);
            IOUtil.writeFile(file, "File on server");

            // 将目标文件数据写入客户端提供的文件描述符所指代的文件
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(pfd.getFileDescriptor());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                writer.write("File on server");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtil.closeQuietly(pfd);
            }
        }
    }
}
