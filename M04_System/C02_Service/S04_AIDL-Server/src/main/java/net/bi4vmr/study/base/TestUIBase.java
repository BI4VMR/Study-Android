package net.bi4vmr.study.base;

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

import net.bi4vmr.aidl.IDownloadService;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.util.List;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-Server-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();
    private IDownloadService downloadService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnGetPID.setOnClickListener(v -> testGetPID());
        binding.btnAddTask.setOnClickListener(v -> testAddTask());
        binding.btnGetTasks.setOnClickListener(v -> testGetTasks());
    }

    private void testBind() {
        Log.i(TAG, "--- 绑定服务 ---");
        appendLog("\n--- 绑定服务 ---\n");

        Intent intent = new Intent(this, DownloadService.class);
        boolean result = bindService(intent, connection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "绑定结果：[" + result + "]");
        appendLog("绑定结果：[" + result + "]\n");
    }

    private void testUnbind() {
        Log.i(TAG, "--- 解绑服务 ---");
        appendLog("\n--- 解绑服务 ---\n");

        unbindService(connection);
        isServiceConnected = false;
        downloadService = null;
        Log.i(TAG, "连接已断开！");
        appendLog("连接已断开！\n");
    }

    private void testGetPID() {
        Log.i(TAG, "--- 获取服务端进程ID ---");
        appendLog("\n--- 获取服务端进程ID ---\n");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            Log.i(TAG, "连接未就绪！");
            appendLog("连接未就绪！\n");
            return;
        }

        try {
            int pid = downloadService.getPID();
            appendLog("Server PID:[" + pid + "]\n");
            Log.i(TAG, "Server PID:[" + pid + "]");
        } catch (RemoteException e) {
            appendLog(e.getMessage());
            e.printStackTrace();
        }
    }

    private void testAddTask() {
        Log.i(TAG, "--- 添加任务 ---");
        appendLog("\n--- 添加任务 ---\n");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            Log.i(TAG, "连接未就绪！");
            appendLog("连接未就绪！\n");
            return;
        }

        try {
            String task = "https://test.net/1.txt";
            downloadService.addTask(task);
        } catch (RemoteException e) {
            appendLog(e.getMessage());
            e.printStackTrace();
        }
    }

    private void testGetTasks() {
        Log.i(TAG, "--- 查询任务 ---");
        appendLog("\n--- 查询任务 ---\n");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            Log.i(TAG, "连接未就绪！");
            appendLog("连接未就绪！\n");
            return;
        }

        try {
            List<String> tasks = downloadService.getTasks();
            Log.i(TAG, tasks.toString());
            appendLog(tasks.toString());
        } catch (RemoteException e) {
            appendLog(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 服务连接回调实现类。
     */
    private class DLServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "连接已就绪。");
            appendLog("连接已就绪。\n");

            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService.Stub.asInterface(service);
            // 将连接标记位置为 `true` ，此时可以进行远程调用。
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "连接已断开！");
            appendLog("连接已断开！\n");

            // 将连接标记位置为 `false`
            isServiceConnected = false;
            // 将Service实例置空
            downloadService = null;
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(CharSequence text) {
        binding.tvLog.append(text);
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
