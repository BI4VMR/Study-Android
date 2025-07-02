package net.bi4vmr.study.exceptions;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IExceptions;
import net.bi4vmr.study.databinding.TestuiExceptionsBinding;

/**
 * 测试界面：异常处理。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIExceptions extends AppCompatActivity {

    private static final String TAG = "TestApp-Client-" + TestUIExceptions.class.getSimpleName();

    private TestuiExceptionsBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();

    private IExceptions testService;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiExceptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnDivide.setOnClickListener(v -> testDivide());
        binding.btnDivide2.setOnClickListener(v -> testDivide2());
    }

    private void testBind() {
        appendLog("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        ComponentName cn = new ComponentName(
                "net.bi4vmr.study.system.service.aidlserver",
                "net.bi4vmr.study.exceptions.ExceptionTestService"
        );
        Intent intent = new Intent();
        intent.setComponent(cn);
        boolean result = bindService(intent, connection, Context.BIND_AUTO_CREATE);
        appendLog("绑定结果：[" + result + "]\n");
        Log.i(TAG, "绑定结果：[" + result + "]");
    }

    private void testUnbind() {
        appendLog("\n--- 解绑服务 ---\n");
        Log.i(TAG, "--- 解绑服务 ---");

        unbindService(connection);
        isServiceConnected = false;
        testService = null;
        binding.tvLog.append("连接已断开！\n");
        Log.i(TAG, "连接已断开！");
    }

    private void testDivide() {
        appendLog("\n--- 计算除法 ---\n");
        Log.i(TAG, "--- 计算除法 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !testService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            int result = testService.divide(100, 0);
            appendLog("计算结果：" + result);
            Log.i(TAG, "计算结果：" + result);
        } catch (Exception e) {
            appendLog(e.getMessage());
            e.printStackTrace();
        }
    }

    private void testDivide2() {
        appendLog("\n--- 计算除法2 ---\n");
        Log.i(TAG, "--- 计算除法2 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !testService.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            int result = testService.divide2(100, 0);
            appendLog("计算结果：" + result);
            Log.i(TAG, "计算结果：" + result);
        } catch (Exception e) {
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
            TestUIExceptions.this.testService = IExceptions.Stub.asInterface(service);
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
            testService = null;
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
