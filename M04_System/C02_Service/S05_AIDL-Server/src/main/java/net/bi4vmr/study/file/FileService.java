package net.bi4vmr.study.file;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import net.bi4vmr.aidl.IFileService;
import net.bi4vmr.study.util.IOUtil;

import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 示例服务：文件传输服务。
 *
 * @author bi4vmr@outlook.com
 * @version 1.0.0
 */
public class FileService extends Service {

    private static final String TAG = "TestApp-Server-" + FileService.class.getSimpleName();

    @Override
    public IBinder onBind(Intent intent) {
        return new FileServiceImpl();
    }

    private static class FileServiceImpl extends IFileService.Stub {

        @Override
        public void upload(ParcelFileDescriptor pfd) {
            // 从PFD获取文件描述符
            FileDescriptor fd = pfd.getFileDescriptor();
            try (
                    FileInputStream fis = new FileInputStream(fd)
            ) {
                // 从输入流读取数据
                String content = IOUtil.readFile(fis);
                Log.d(TAG, "获取到内容：" + content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void download(ParcelFileDescriptor pfd) {
            // 将目标文件数据写入客户端提供的文件描述符所指代的文件
            try (
                    FileOutputStream fos = new FileOutputStream(pfd.getFileDescriptor());
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            ) {
                // 向输出流写入数据
                writer.write("File on server");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
