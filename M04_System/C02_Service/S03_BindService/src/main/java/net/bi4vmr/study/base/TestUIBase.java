package net.bi4vmr.study.base;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    /**
     * 服务连接回调实现。
     * <p>
     * 用于接收服务连接成功、连接断开事件，并获取Binder实例。
     */
    private final ServiceConnection connection = new MyConnectionCallback();

    // Binder实例
    private DownloadService.DownloadImpl binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnAddListener.setOnClickListener(v -> testSetListener());
        binding.btnAddTask.setOnClickListener(v -> testAddTask());
    }

    private void testBind() {
        Log.i(TAG, "--- 绑定服务 ---");
        appendLog("\n--- 绑定服务 ---\n");

        Intent intent = new Intent(this, DownloadService.class);
        boolean result = bindService(intent, connection, BIND_AUTO_CREATE);
        Log.i(TAG, "服务存在？：" + result);
        appendLog("服务存在？：" + result + "\n");
    }

    private void testUnbind() {
        Log.i(TAG, "--- 解绑服务 ---");
        appendLog("\n--- 解绑服务 ---\n");

        // 仅当Binder实例不为空时才进行解绑操作
        if (binder != null) {
            unbindService(connection);
            // 连接已断开，将全局变量置空。
            binder = null;
        }
    }

    private void testSetListener() {
        Log.i(TAG, "--- 注册监听器 ---");
        appendLog("\n--- 注册监听器 ---\n");

        // 判断连接是否就绪
        if (binder == null) {
            Log.e(TAG, "--- 连接未就绪！ ---");
            appendLog("--- 连接未就绪！ ---\n");
            return;
        }

        binder.setCallback(percent -> {
            Log.i(TAG, "进度变化：" + percent);
            appendLog("进度变化：" + percent + "\n");
        });
    }

    private void testAddTask() {
        Log.i(TAG, "--- 添加任务 ---");
        appendLog("\n--- 添加任务 ---\n");

        // 判断连接是否就绪
        if (binder == null) {
            Log.e(TAG, "--- 连接未就绪！ ---");
            appendLog("--- 连接未就绪！ ---\n");
            return;
        }

        binder.addTask("https://dl.test.com/file");
    }

    /**
     * 服务连接回调实现类
     */
    private class MyConnectionCallback implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "OnServiceConnected.");
            appendLog("OnServiceConnected.\n");

            // 获取Binder实例，将其转为Service中的业务类类型，并保存至全局变量。
            binder = (DownloadService.DownloadImpl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "OnServiceDisconnected.");
            appendLog("OnServiceDisconnected.\n");

            // 连接已断开，将全局变量置空。
            binder = null;
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
                Log.w(TAG, "TextView scroll failed!");
                e.printStackTrace();
            }
        });
    }
}
