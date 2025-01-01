package net.bi4vmr.study.lifecycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiLifecycleBinding;

/**
 * 测试界面：生命周期。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUILifeCycle extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUILifeCycle.class.getSimpleName();

    private TestuiLifecycleBinding binding;

    private final ServiceConnection connectionA = new MyConnectionCallback("A");
    private final ServiceConnection connectionB = new MyConnectionCallback("B");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiLifecycleBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> testStartService());
        binding.btnStop.setOnClickListener(v -> testStopService());
        binding.btnBindA.setOnClickListener(v -> testBindServiceA());
        binding.btnUnbindA.setOnClickListener(v -> testUnbindServiceA());
        binding.btnBindB.setOnClickListener(v -> testBindServiceB());
        binding.btnUnbindB.setOnClickListener(v -> testUnbindServiceB());
    }

    private void testStartService() {
        Log.i(TAG, "--- 启动服务 ---");
        appendLog("\n--- 启动服务 ---\n");

        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }

    private void testStopService() {
        Log.i(TAG, "--- 停止服务 ---");
        appendLog("\n--- 停止服务 ---\n");

        Intent intent = new Intent(this, TestService.class);
        stopService(intent);
    }

    private void testBindServiceA() {
        Log.i(TAG, "--- 绑定服务（请求A） ---");
        appendLog("\n--- 绑定服务（请求A） ---\n");

        Intent intent = new Intent(this, TestService.class);
        intent.setType("请求A");
        bindService(intent, connectionA, BIND_AUTO_CREATE);
    }

    private void testUnbindServiceA() {
        Log.i(TAG, "--- 解绑服务（请求A） ---");
        appendLog("\n--- 解绑服务（请求A） ---\n");

        unbindService(connectionA);
    }

    private void testBindServiceB() {
        Log.i(TAG, "--- 绑定服务（请求B） ---");
        appendLog("\n--- 绑定服务（请求B） ---\n");

        Intent intent = new Intent(this, TestService.class);
        intent.setType("请求B");
        bindService(intent, connectionB, BIND_AUTO_CREATE);
    }

    private void testUnbindServiceB() {
        binding.tvLog.append("\n--- 解绑服务（请求B） ---\n");
        Log.i(TAG, "--- 解绑服务（请求B） ---");

        unbindService(connectionB);
    }

    /**
     * 连接回调实现类。
     */
    private class MyConnectionCallback implements ServiceConnection {

        private final String cbName;

        public MyConnectionCallback(String cbName) {
            this.cbName = cbName;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "OnServiceConnected. Name:[" + cbName + "] Binder:[" + service.hashCode() + "]");
            appendLog("OnServiceConnected. Name:[" + cbName + "] Binder:[" + service.hashCode() + "]\n");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "OnServiceDisconnected. Name:[" + cbName + "]");
            appendLog("OnServiceDisconnected. Name:[" + cbName + "]\n");
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
