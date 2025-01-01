package net.bi4vmr.study.file;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IFileService;
import net.bi4vmr.study.databinding.TestuiFileBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;

/**
 * 测试界面：文件传输。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIFile extends AppCompatActivity {

    private static final String TAG = "TestApp-Client-" + TestUIFile.class.getSimpleName();

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
        appendLog("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        ComponentName cn = new ComponentName(
                "net.bi4vmr.study.system.service.aidlserver",
                "net.bi4vmr.study.file.FileService"
        );
        Intent intent = new Intent();
        intent.setComponent(cn);

        // 根据品牌实施兼容性措施
        if (Build.BRAND.contains("Meizu")) {
            // 绑定服务前先调用一次启动服务的方法
            startService(intent);
        }

        boolean result = bindService(intent, connection, Context.BIND_AUTO_CREATE);
        appendLog("绑定结果：[" + result + "]\n");
        Log.i(TAG, "绑定结果：[" + result + "]");
    }

    private void testUnbind() {
        appendLog("\n--- 解绑服务 ---\n");
        Log.i(TAG, "--- 解绑服务 ---");

        unbindService(connection);
        isServiceConnected = false;
        appendLog("连接已断开！\n");
        Log.i(TAG, "连接已断开！");
    }

    private void testUpload() {
        appendLog("\n--- 上传文件 ---\n");
        Log.i(TAG, "--- 上传文件 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !fileService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        // 创建测试文件
        File textFile = new File(getApplicationContext().getFilesDir(), "text.txt");
        IOUtil.writeFile(textFile, "Hello World!");

        try (
                // 获取测试文件的文件描述符，模式为只读。
                ParcelFileDescriptor pfd = ParcelFileDescriptor.open(textFile, ParcelFileDescriptor.MODE_READ_ONLY)
        ) {
            // 通过远程服务将文件描述符传递给服务端，由服务端读取文件内容。
            fileService.upload(pfd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testDownload() {
        binding.tvLog.append("\n--- 下载文件 ---\n");
        Log.i(TAG, "--- 下载文件 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !fileService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        // 声明目标文件
        File file = new File(getApplicationContext().getFilesDir() + "/download.txt");

        try (
                // 获取测试文件的文件描述符，模式为只写与自动创建文件。
                ParcelFileDescriptor pfd = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_WRITE_ONLY | ParcelFileDescriptor.MODE_CREATE)
        ) {
            // 将目标文件的描述符传递给服务端，由服务端写入数据。
            fileService.download(pfd);

            // 服务端写入数据完毕，读取文件检查结果。
            String content = IOUtil.readFile(file);
            appendLog("文件内容：" + content);
            Log.i(TAG, "文件内容：" + content);
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
            appendLog("连接已就绪。\n");
            Log.i(TAG, "连接已就绪。");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 将连接标记位置为"false"
            isServiceConnected = false;
            // 将Service实例置空
            fileService = null;
            appendLog("连接已断开！\n");
            Log.i(TAG, "连接已断开！");
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(CharSequence text) {
        binding.tvLog.append(text);
        binding.tvLog.post(() -> {
            int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
            if (offset > 0) {
                binding.tvLog.scrollTo(0, offset);
            }
        });
    }
}
