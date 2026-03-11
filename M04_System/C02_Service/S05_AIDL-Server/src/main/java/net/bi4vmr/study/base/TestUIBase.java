package net.bi4vmr.study.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

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

    /**
     * 服务连接状态回调，用于监听服务状态与获取Binder接口实现。
     */
    private final ServiceConnection connection = new DLServiceConnection();

    /**
     * Binder接口实现，用于调用服务端提供的远程方法。
     * <p>
     * 变量为空表示服务未就绪或已断开，变量非空表示服务已连接，但服务进程不一定可用，还需要通过 `isBinderAlive()` 方法检测服务进程状态
     * 后才能调用其中的方法。
     */
    private IDownloadService downloadService;

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
        Log.i(TAG, "----- 绑定服务 -----");
        appendLog("\n----- 绑定服务 -----");

        // 通过Intent指明目标服务
        Intent intent = new Intent(this, DownloadService.class);

        // 绑定服务，参数依次为目标服务Intent、连接状态回调实现和选项。
        boolean result = bindService(intent, connection, BIND_AUTO_CREATE);

        /*
         * 绑定方法返回 `true` 表示当前进程可绑定目标服务，连接状态需要从回调获取；返回 `false` 表示服务不可用，原因包括：服务不存在、当前
         * 软件包没有权限与目标软件包交互等。
         */
        Log.i(TAG, "当前进程可绑定目标服务？：[" + result + "]");
        appendLog("当前进程可绑定目标服务？：[" + result + "]");
    }

    private void testUnbind() {
        Log.i(TAG, "----- 解绑服务 -----");
        appendLog("\n----- 解绑服务 -----");

        // 解绑服务
        unbindService(connection);
        // 将本地Binder引用置空，标记其不再可用。
        downloadService = null;

        Log.i(TAG, "连接已断开！");
        appendLog("连接已断开！");
    }

    private void testGetPID() {
        Log.i(TAG, "----- 获取服务端进程ID -----");
        appendLog("\n----- 获取服务端进程ID -----");

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            Log.i(TAG, "连接未就绪！");
            appendLog("连接未就绪！");
            return;
        }

        try {
            int pid = downloadService.getPID();
            Log.i(TAG, "Server PID:[" + pid + "]");
            appendLog("Server PID:[" + pid + "]");
        } catch (RemoteException e) {
            e.printStackTrace();
            appendLog(e.getMessage());
        }
    }

    private void testAddTask() {
        appendLog("\n----- 添加任务 -----");
        Log.i(TAG, "----- 添加任务 -----");

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            Log.i(TAG, "连接未就绪！");
            appendLog("连接未就绪！");
            return;
        }

        try {
            String task = "https://test.net/1.txt";
            downloadService.addTask(task);
        } catch (RemoteException e) {
            e.printStackTrace();
            appendLog(e.getMessage());
        }
    }

    private void testGetTasks() {
        Log.i(TAG, "----- 查询任务 -----");
        appendLog("\n----- 查询任务 -----");

        // 根据Binder变量是否为空判断能否执行远程调用
        if (downloadService == null) {
            appendLog("连接未就绪！");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            List<String> tasks = downloadService.getTasks();
            appendLog(tasks.toString());
            Log.i(TAG, tasks.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
            appendLog(e.getMessage());
        }
    }

    /**
     * 服务连接回调实现类。
     */
    private class DLServiceConnection implements ServiceConnection {

        /**
         * 服务端就绪时，系统将回调此方法。
         *
         * @param name    服务的组件名称。
         * @param service 服务的Binder实例。
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "连接已就绪。");
            appendLog("连接已就绪。");

            // 使用Stub抽象类的 `asInterface()` 方法将Binder实例转为AIDL对应的类型。
            downloadService = IDownloadService.Stub.asInterface(service);
        }

        /**
         * 服务端进程终止（Crash或资源不足被回收等）时，系统将回调此方法。
         *
         * @param name 服务的组件名称。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "连接已断开！");
            appendLog("连接已断开！");

            // 将Binder实例置空
            downloadService = null;
        }
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        if (text == null) {
            Log.w(TAG, "Log item is NULL, ignored!");
            return;
        }

        TextView logArea = binding.tvLog;
        logArea.post(() -> logArea.append("\n" + text));
        logArea.post(() -> {
            try {
                int offset = logArea.getLayout().getLineTop(logArea.getLineCount()) - logArea.getHeight();
                if (offset > 0) {
                    logArea.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
