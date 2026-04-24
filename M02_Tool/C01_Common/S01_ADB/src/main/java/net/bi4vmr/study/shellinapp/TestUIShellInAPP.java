package net.bi4vmr.study.shellinapp;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.InputStream;

/**
 * 测试界面：在应用程序中执行ADB命令。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIShellInAPP extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIShellInAPP.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnADBShell.setOnClickListener(v -> testADBCMD());
    }

    // 执行ADB命令
    private void testADBCMD() {
        Log.i(TAG, "----- 执行ADB命令 -----");
        appendLog("\n----- 执行ADB命令 -----");

        // 命令语句
        final String cmd = "free -h";

        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(cmd);
            // 阻塞当前线程等待命令执行完毕
            int resultCode = process.waitFor();
            Log.i(TAG, "'free -h'命令的执行结果:[" + resultCode + "]");
            binding.tvLog.append("'free -h'命令的执行结果:[" + resultCode + "]\n");

            if (resultCode == 0) {
                /* 命令执行成功 */
                InputStream isStdOut = process.getInputStream();
                String text = IOUtil.readFile(isStdOut);
                Log.i(TAG, "标准信息输出：\n" + text);
                binding.tvLog.append("标准信息输出：\n" + text + "\n");
            } else {
                /* 命令执行失败 */
                InputStream isStdErr = process.getErrorStream();
                String text = IOUtil.readFile(isStdErr);
                Log.i(TAG, "标准错误输出：\n" + text);
                binding.tvLog.append("标准错误输出：\n" + text + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
