package net.bi4vmr.study.types;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IDownloadService2;
import net.bi4vmr.study.databinding.TestuiTypesBinding;

import java.util.List;

/**
 * 测试界面：自定义数据类型。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUITypes extends AppCompatActivity {

    private static final String TAG = "TestApp-Server-" + TestUITypes.class.getSimpleName();

    private TestuiTypesBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();
    private IDownloadService2 downloadService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiTypesBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnAddTask.setOnClickListener(v -> testAddTask());
        binding.btnGetTasks.setOnClickListener(v -> testGetTasks());
    }

    private void testBind() {
        appendLog("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        Intent intent = new Intent(this, DownloadService2.class);
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
        binding.tvLog.append("连接已断开！\n");
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
            appendLog(e.getMessage());
            e.printStackTrace();
        }
    }

    private void testGetTasks() {
        appendLog("\n--- 查询任务 ---\n");
        Log.i(TAG, "--- 查询任务 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !downloadService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            List<DownloadItem> tasks = downloadService.getTasks();
            appendLog(tasks.toString());
            Log.i(TAG, tasks.toString());
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
            appendLog("连接已就绪。\n");
            Log.i(TAG, "连接已就绪。");

            // 使用Stub抽象类的 `asInterface()` 方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService2.Stub.asInterface(service);
            // 将连接标记位置为 `true` ，此时可以进行远程调用。
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            appendLog("连接已断开！\n");
            Log.i(TAG, "连接已断开！");

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
            int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
            if (offset > 0) {
                binding.tvLog.scrollTo(0, offset);
            }
        });
    }
}
