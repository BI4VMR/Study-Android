package net.bi4vmr.study.base;

import android.os.Bundle;
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

        binding.btnLog.setOnClickListener(v -> testLog());
        binding.btnJavaLog.setOnClickListener(v -> testJavaLog());
        binding.btnChatty.setOnClickListener(v -> testChatty());
        binding.btnLongLine.setOnClickListener(v -> testLongLine());
    }

    // 输出日志
    private void testLog() {
        Log.i(TAG, "--- 输出日志 ---");
        binding.tvLog.append("\n--- 输出日志 ---\n");

        // 输出Verbose级别日志
        Log.v(TAG, "Verbose Log.");
        // 输出Debug级别日志
        Log.d(TAG, "Debug Log.");
        // 输出Info级别日志
        Log.i(TAG, "Info Log.");
        // 输出Warn级别日志
        Log.w(TAG, "Warn Log.");
        // 输出Error级别日志
        Log.e(TAG, "Error Log.");
    }

    // Java日志兼容性
    private void testJavaLog() {
        Log.i(TAG, "--- Java日志兼容性 ---");
        binding.tvLog.append("\n--- Java日志兼容性 ---\n");

        // 输出标准信息
        System.out.println("标准信息输出测试。");
        // 输出错误信息
        System.err.println("错误信息输出测试。");
    }

    // Chatty机制
    private void testChatty() {
        Log.i(TAG, "--- Chatty机制 ---");
        binding.tvLog.append("\n--- Chatty机制 ---\n");

        // 连续输出100行相同的日志
        for (int i = 0; i < 100; i++) {
            Log.i(TAG, "Chatty机制测试内容。");
        }
    }

    // 输出超长内容
    private void testLongLine() {
        Log.i(TAG, "--- 输出超长内容 ---");
        binding.tvLog.append("\n--- 输出超长内容 ---\n");

        // 测试数据
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1000; i++) {
            sb.append("日志内容");
        }
        String input = sb.toString();

        // 每行最大长度
        final int lineLength = 1000;

        // 如果无需换行，则原样打印。
        if (input.length() <= lineLength) {
            Log.i(TAG, input);
            return;
        }

        // 计算切分后的行数（不含最后一行）
        int lines = input.length() / lineLength;
        // 循环打印每一行内容
        for (int i = 0; i <= lines; i++) {
            if (i != lines) {
                /* 打印完整的行 */
                String line = input.substring(i * lineLength, (i + 1) * lineLength);
                Log.i(TAG, "Line:[" + (i + 1) + "] Text:[" + line + "]");
            } else {
                /* 打印最后一行 */
                String line = input.substring(i * lineLength);
                if (!line.isEmpty()) {
                    Log.i(TAG, "Line:[" + (i + 1) + "] Text:[" + line + "]");
                }
            }
        }
    }
}
