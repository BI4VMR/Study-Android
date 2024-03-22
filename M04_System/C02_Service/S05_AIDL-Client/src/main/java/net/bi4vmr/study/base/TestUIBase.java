package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IDownloadService;
import net.bi4vmr.aidl.callback.TaskCallback;
import net.bi4vmr.study.databinding.TestuiBaseBinding;

@SuppressLint("SetTextI18n")
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-Client-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();
    private IDownloadService downloadService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnGetPID.setOnClickListener(v -> testGetPID());
        binding.btnAddTask.setOnClickListener(v -> testAddTask());
        binding.btnAddTaskAsync.setOnClickListener(v -> testAddTaskAsync());
        binding.btnGetTasks.setOnClickListener(v -> testGetTasks());
    }

    private void testBind() {
        binding.tvLog.append("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        Intent intent = new Intent();
        intent.setPackage("net.bi4vmr.study.system.service.aidlserver");
        intent.setAction("net.bi4vmr.aidl.DOWNLOAD");
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

    private void testGetPID() {
        binding.tvLog.append("\n--- 获取服务端进程ID ---\n");
        Log.i(TAG, "--- 获取服务端进程ID ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            int pid = downloadService.getPID();
            binding.tvLog.append("PID:[" + pid + "]\n");
            Log.i(TAG, "PID:[" + pid + "]");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void testAddTask() {
        binding.tvLog.append("\n--- 添加任务 ---\n");
        Log.i(TAG, "--- 添加任务 ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            ItemBean task = new ItemBean("https://test.net/1.txt");
            downloadService.addTask(task);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void testAddTaskAsync() {
        binding.tvLog.append("\n--- 添加任务（AIDL异步方法） ---\n");
        Log.i(TAG, "--- 添加任务（AIDL异步方法） ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            ItemBean task = new ItemBean("https://test.net/1.txt");
            downloadService.addTask(task);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void testGetTasks() {
        binding.tvLog.append("\n--- 获取任务列表 ---\n");
        Log.i(TAG, "--- 获取任务列表 ---");

        // 根据连接状态标志位确定是否能够访问接口
        if (!isServiceConnected) {
            binding.tvLog.append("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            binding.tvLog.append(downloadService.getTasks().toString() + "\n");
            Log.i(TAG, downloadService.getTasks().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /* 服务连接回调实现类 */
    private class DLServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 使用Stub抽象类的"asInterface()"方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService.Stub.asInterface(service);
            // 连接标记位置为"true"，此时可以进行远程调用。
            isServiceConnected = true;
            binding.tvLog.append("连接已就绪。\n");
            Log.i(TAG, "连接已就绪。");

            /* 以下为自定义的业务逻辑 */
            // 设置回调以监听服务端的事件
            try {
                downloadService.setTaskCallback(new TaskCallback.Stub() {
                    @Override
                    public void onStateChanged(ItemBean item) {
                        Log.i(TAG, "OnStateChanged. Item:[" + item + "]");
                        // 服务端回调不在主进程，因此需要切换至主线程更新UI。
                        runOnUiThread(() -> binding.tvLog.setText(item.toString()));
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 连接标记位置为"false"
            isServiceConnected = false;
            // 将Service对象置空
            downloadService = null;
            binding.tvLog.append("连接已断开！\n");
            Log.i(TAG, "连接已断开！");
        }
    }
}
