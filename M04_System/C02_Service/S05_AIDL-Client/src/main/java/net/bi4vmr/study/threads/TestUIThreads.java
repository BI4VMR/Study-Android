package net.bi4vmr.study.threads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IDownloadService3;
import net.bi4vmr.aidl.callback.TaskCallback;
import net.bi4vmr.study.databinding.TestuiThreadsBinding;
import net.bi4vmr.study.types.DownloadItem;

/**
 * 测试界面：线程调度。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIThreads extends AppCompatActivity {

    private static final String TAG = "TestApp-Client-" + TestUIThreads.class.getSimpleName();

    private TestuiThreadsBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();

    private IDownloadService3 downloadService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiThreadsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnAddTask.setOnClickListener(v -> testAddTask());
        binding.btnAddTaskOneway.setOnClickListener(v -> testAddTaskOneway());
    }

    private void testBind() {
        appendLog("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        Intent intent = new Intent();
        intent.setPackage("net.bi4vmr.study.system.service.aidlserver");
        intent.setAction("net.bi4vmr.aidl.DOWNLOAD3");
        boolean result = bindService(intent, connection, Context.BIND_AUTO_CREATE);
        appendLog("绑定结果：[" + result + "]\n");
        Log.i(TAG, "绑定结果：[" + result + "]");
    }

    private void testUnbind() {
        appendLog("\n--- 解绑服务 ---\n");
        Log.i(TAG, "--- 解绑服务 ---");

        unbindService(connection);
        isServiceConnected = false;
        downloadService = null;
        appendLog("连接已断开！\n");
        Log.i(TAG, "连接已断开！");
    }

    private void testAddTask() {
        appendLog("\n--- 添加任务 ---\n");
        Log.i(TAG, "--- 添加任务 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            DownloadItem task = new DownloadItem("https://test.net/1.txt");
            downloadService.addTask(task);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void testAddTaskOneway() {
        appendLog("\n--- 添加任务（AIDL异步方法） ---\n");
        Log.i(TAG, "--- 添加任务（AIDL异步方法） ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            DownloadItem task = new DownloadItem("https://test.net/1.txt");
            downloadService.addTaskOneway(task);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* 服务连接回调实现类 */
    private class DLServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService3.Stub.asInterface(service);
            // 将连接标记位置为"true"，此时可以进行远程调用。
            isServiceConnected = true;
            appendLog("连接已就绪。\n");
            Log.i(TAG, "连接已就绪。");

            /* 以下为自定义的业务逻辑 */
            // 设置回调以监听服务端的事件
            try {
                downloadService.setTaskCallback(new TaskCallback.Stub() {
                    @Override
                    public void onStateChanged(DownloadItem item) {
                        Log.i(TAG, "OnStateChanged. Item:[" + item + "]");
                        // 服务端回调不在主进程，因此需要切换至主线程更新UI。
                        runOnUiThread(() -> appendLog("OnStateChanged. Item:[" + item + "]\n"));
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 将连接标记位置为"false"
            isServiceConnected = false;
            // 将Service实例置空
            downloadService = null;
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
