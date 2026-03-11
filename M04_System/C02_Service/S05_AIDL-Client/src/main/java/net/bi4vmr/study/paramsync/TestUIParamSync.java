package net.bi4vmr.study.paramsync;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.aidl.IParamSync;
import net.bi4vmr.study.databinding.TestuiParamsyncBinding;
import net.bi4vmr.study.types.DownloadItem;

/**
 * 测试界面：数据方向标签。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIParamSync extends AppCompatActivity {

    private static final String TAG = "TestApp-Client-" + TestUIParamSync.class.getSimpleName();

    private TestuiParamsyncBinding binding;

    private final ServiceConnection connection = new DLServiceConnection();
    private IParamSync service;

    private boolean isServiceConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiParamsyncBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnBind.setOnClickListener(v -> testBind());
        binding.btnUnbind.setOnClickListener(v -> testUnbind());
        binding.btnExec.setOnClickListener(v -> testExec());
    }

    private void testBind() {
        appendLog("\n--- 绑定服务 ---\n");
        Log.i(TAG, "--- 绑定服务 ---");

        ComponentName cn = new ComponentName(
                "net.bi4vmr.study.system.service.aidlserver",
                "net.bi4vmr.study.paramsync.ParamSyncService"
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
        binding.tvLog.append("连接已断开！\n");
        Log.i(TAG, "连接已断开！");
    }

    private void testExec() {
        appendLog("\n--- 开始测试 ---\n");
        Log.i(TAG, "--- 开始测试 ---");

        // 根据连接状态标志位和Binder状态检测确定是否能够访问接口
        if (!isServiceConnected || !service.asBinder().isBinderAlive()) {
            appendLog("连接未就绪！\n");
            Log.i(TAG, "连接未就绪！");
            return;
        }

        try {
            DownloadItem p1 = new DownloadItem("Client");
            DownloadItem p2 = new DownloadItem("Client");
            DownloadItem p3 = new DownloadItem("Client");
            service.modifyParams(p1, p2, p3);
            // p1.setUrl("Client-Modify");
            // p2.setUrl("Client-Modify");
            // p3.setUrl("Client-Modify");
            // Log.d(TAG, "客户端传值后修改变量");
            // Log.d(TAG, "In Param P1: " + p1);
            // Log.d(TAG, "Out Param P2: " + p2);
            // Log.d(TAG, "In/Out Param P3: " + p3);

            Thread.sleep(3000L);

            Log.d(TAG, "服务端操作完毕后读取变量");
            Log.d(TAG, "In Param P1: " + p1);
            Log.d(TAG, "Out Param P2: " + p2);
            Log.d(TAG, "In/Out Param P3: " + p3);
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
            TestUIParamSync.this.service = IParamSync.Stub.asInterface(service);
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
            service = null;
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
