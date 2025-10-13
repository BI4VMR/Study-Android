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
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IDownloadService;
import net.bi4vmr.aidl.callback.TaskCallback;
import net.bi4vmr.study.R;

public class DemoBaseUI extends AppCompatActivity {

    private TextView tvInfo;
    private TextView tvInfo2;

    private final ServiceConnection connection = new DLServiceConnection();
    private IDownloadService downloadService;

    private boolean isServiceConnected = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_base);

        tvInfo = findViewById(R.id.tvInfo);
        tvInfo2 = findViewById(R.id.tvInfo2);
        Button btBind = findViewById(R.id.btBind);
        Button btUnbind = findViewById(R.id.btUnbind);
        Button btGetPID = findViewById(R.id.btGetPID);
        Button btAddTask = findViewById(R.id.btAddTask);
        Button btAddTaskAsync = findViewById(R.id.btAddTaskAsync);
        Button btGetTasks = findViewById(R.id.btGetTasks);

        // 按钮：绑定服务
        btBind.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setPackage("net.bi4vmr.study.sys.service.aidl.server");
            intent.setAction("net.bi4vmr.aidl.DOWNLOAD");
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        // 按钮：解绑服务
        btUnbind.setOnClickListener(v -> {
            unbindService(connection);
            isServiceConnected = false;
            tvInfo.setText("连接已断开");
            Log.i("myapp-client", "连接已断开");
        });

        // 按钮：获取进程ID
        btGetPID.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (!isServiceConnected) {
                tvInfo.setText("连接未就绪");
                Log.i("myapp-client", "连接未就绪");
                return;
            }

            try {
                int pid = downloadService.getPID();
                tvInfo.setText("PID: " + pid);
                Log.i("myapp-client", "PID: " + pid);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 按钮：添加任务
        btAddTask.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (!isServiceConnected) {
                tvInfo.setText("连接未就绪");
                Log.i("myapp-client", "连接未就绪");
                return;
            }

            try {
                ItemBean task = new ItemBean("https://test.net/1.txt");
                downloadService.addTask(task);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 按钮：添加任务（AIDL异步方法）
        btAddTaskAsync.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (!isServiceConnected) {
                tvInfo.setText("连接未就绪");
                Log.i("myapp-client", "连接未就绪");
                return;
            }

            try {
                ItemBean task = new ItemBean("https://test.net/1.txt");
                downloadService.addTask(task);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 按钮：获取任务列表
        btGetTasks.setOnClickListener(v -> {
            // 根据连接状态标志位确定是否能够访问接口
            if (!isServiceConnected) {
                tvInfo.setText("连接未就绪");
                Log.i("myapp-client", "连接未就绪");
                return;
            }

            try {
                tvInfo.setText(downloadService.getTasks().toString());
                Log.i("myapp-client", downloadService.getTasks().toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    /* 服务连接回调实现类 */
    class DLServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 使用Stub抽象类的"asInterface()"方法将Binder对象转换为对应的Service对象。
            downloadService = IDownloadService.Stub.asInterface(service);
            // 连接标记位置为"true"，此时可以进行远程调用。
            isServiceConnected = true;
            tvInfo.setText("连接已就绪");
            Log.i("myapp-client", "连接已就绪");
            // 设置回调以监听服务端的事件
            try {
                downloadService.setTaskCallback(new TaskCallback.Stub() {
                    @Override
                    public void onStateChanged(ItemBean item) {
                        Log.i("myapp-client", "OnStateChanged. Item: " + item);
                        // 服务端回调不在主进程，因此需要切换至主线程更新UI。
                        runOnUiThread(() -> tvInfo2.setText(item.toString()));
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
            tvInfo.setText("连接已断开");
            Log.i("myapp-client", "连接已断开");
        }
    }
}
