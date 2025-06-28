package net.bi4vmr.study.file;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IFileService;
import net.bi4vmr.study.databinding.TestuiFileBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;

@SuppressLint("SetTextI18n")
public class TestUIFile extends AppCompatActivity {

    private static final String TAG = "TestApp-Server-" + TestUIFile.class.getSimpleName();

    private TestuiFileBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();
    private IFileService fileService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnUpload.setOnClickListener(v -> testUpload());
        binding.btnDownload.setOnClickListener(v -> testDownload());
    }

    private void testBind() {
        binding.tvLog.append("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        Intent intent = new Intent(this, FileService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void testUnbind() {
        binding.tvLog.append("\n--- 解绑服务 ---\n");
        Log.i(TAG, "--- 解绑服务 ---");

        unbindService(connection);
        isServiceConnected = false;
        binding.tvLog.append("连接已断开！\n");
        Log.i(TAG, "连接已断开！");
    }

    private void testUpload() {
        binding.tvLog.append("\n--- 添加任务（AIDL异步方法） ---\n");
        Log.i(TAG, "--- 添加任务（AIDL异步方法） ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        File textFile = new File(getApplicationContext().getFilesDir(), "text.txt");
        IOUtil.writeFile(textFile, "Hello World!");

        try (
                ParcelFileDescriptor pfd = ParcelFileDescriptor.open(textFile, ParcelFileDescriptor.MODE_READ_ONLY)
        ) {
            Log.d(TAG, "upload start ,pfd: " + pfd);
            fileService.upload(pfd);
            Log.d(TAG, "upload end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testDownload() {
        binding.tvLog.append("\n--- 获取任务列表 ---\n");
        Log.i(TAG, "--- 获取任务列表 ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            // 打开待写入文件的描述符
            String path = getApplicationContext().getFilesDir() + "/download.txt";
            File file = new File(path);
            ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_WRITE_ONLY | ParcelFileDescriptor.MODE_CREATE);
            fileService.download(pfd);

            // 服务端写入数据完毕，读取文件检查结果。
            String content = IOUtil.readFile(file);
            binding.tvLog.append("content:" + content);
            Log.i(TAG, "content:" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 服务连接回调实现类 */
    private class DLServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 使用Stub抽象类的"asInterface()"方法将Binder对象转换为对应的Service对象。
            fileService = IFileService.Stub.asInterface(service);
            // 将连接标记位置为"true"，此时可以进行远程调用。
            isServiceConnected = true;
            binding.tvLog.append("连接已就绪。\n");
            Log.i(TAG, "连接已就绪。");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 将连接标记位置为"false"
            isServiceConnected = false;
            // 将Service实例置空
            fileService = null;
            binding.tvLog.append("连接已断开！\n");
            Log.i(TAG, "连接已断开！");
        }
    }
}
