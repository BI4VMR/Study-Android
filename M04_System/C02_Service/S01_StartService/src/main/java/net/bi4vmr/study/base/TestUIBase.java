package net.bi4vmr.study.base;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnStart.setOnClickListener(v -> testStart());
        binding.btnEnd.setOnClickListener(v -> testEnd());
    }

    private void testStart() {
        Log.i(TAG, "--- 启动服务 ---");
        appendLog("\n--- 启动服务 ---\n");

        // 指明目标服务
        Intent intent = new Intent(this, DownloadService.class);
        // 添加初始化数据
        intent.putExtra("LINK", "https://dl.test.com/file");
        // 启动服务
        ComponentName serviceInfo = startService(intent);
        Log.i(TAG, "服务名称：" + serviceInfo);
        appendLog("服务名称：" + serviceInfo);


        // 指明目标服务（通过ComponentName）
        // ComponentName cn = new ComponentName(
        //         "net.bi4vmr.study.system.service.startservice",
        //         "net.bi4vmr.study.base.DownloadService");
        // Intent intent2 = new Intent();
        // intent2.setComponent(cn);

        // 指明目标服务（通过Action与Category等）
        // Intent intent3 = new Intent();
        // intent3.setPackage("net.bi4vmr.study.system.service.startservice");
        // intent3.setAction("net.bi4vmr.action.DOWNLOAD");
        // intent3.addCategory("net.bi4vmr.category.DOWNLOAD");
    }

    // 停止服务
    private void testEnd() {
        Log.i(TAG, "--- 停止服务 ---");
        appendLog("\n--- 停止服务 ---\n");

        Intent intent = new Intent(this, DownloadService.class);
        boolean isSuccess = stopService(intent);
        if (isSuccess) {
            Log.i(TAG, "服务已被停止。");
            appendLog("服务已被停止。\n");
        } else {
            Log.e(TAG, "服务停止失败！");
            appendLog("服务停止失败！\n");
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
