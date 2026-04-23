package net.bi4vmr.study.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiEventBinding;

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIEvent extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIEvent.class.getSimpleName();

    private TestuiEventBinding binding;

    private final PackageChangeReceiver receiver = new PackageChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnStartListening.setOnClickListener(v -> test_StartListening());
        binding.btnStopListening.setOnClickListener(v -> test_StopListening());
    }

    private void test_StartListening() {
        Log.i(TAG, "----- 开始监听 -----");
        appendLog("\n----- 开始监听 -----");

        // 创建IntentFilter并添加ACTION_PACKAGE_CHANGED
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        // 注册BroadcastReceiver
        registerReceiver(receiver, filter);
    }

    private void test_StopListening() {
        Log.i(TAG, "----- 停止监听 -----");
        appendLog("\n----- 停止监听 -----");

        unregisterReceiver(receiver);
    }


    /**
     * 软件包事件接收器。
     * <p>
     * 接收软件包的安装、卸载、更新等事件。
     */
    private static class PackageChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Log.i(TAG, "Package changed: " + packageName);
            try {
                boolean b2 = context.getPackageManager().getApplicationInfo(packageName, 0).enabled;
                Log.i(TAG, "Package state: " + b2);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
