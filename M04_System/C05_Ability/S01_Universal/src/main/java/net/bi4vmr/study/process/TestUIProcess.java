package net.bi4vmr.study.process;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiProcessBinding;

/**
 * 测试界面：设备信息。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIProcess extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIProcess.class.getSimpleName();

    private TestuiProcessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiProcessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnGetProcessList.setOnClickListener(v -> test_GetProcessList());
        binding.btnGetPID.setOnClickListener(v -> test_GetPID());
        binding.btnGetPName.setOnClickListener(v -> test_GetPName());
        binding.btnIsMainProcess.setOnClickListener(v -> test_IsMainProcess());
    }

    private void test_GetProcessList() {
        Log.i(TAG, "----- 获取当前应用进程信息 -----");
        appendLog("\n----- 获取当前应用进程信息 -----");

        ActivityManager am = getSystemService(ActivityManager.class);
        am.getRunningAppProcesses()
                .forEach(processInfo -> {
                            Log.i(TAG, "当前进程ID: " + processInfo.pid);
                            Log.i(TAG, "当前进程名称: " + processInfo.processName);
                            appendLog("当前进程ID: " + processInfo.pid);
                            appendLog("当前进程名称: " + processInfo.processName);
                        }
                );
    }

    private void test_GetPID() {
        Log.i(TAG, "----- 获取当前进程ID -----");
        appendLog("\n----- 获取当前进程ID -----");

        // 获取当前进程ID
        int pid = Process.myPid();
        Log.i(TAG, "当前进程ID: " + pid);
        appendLog("当前进程ID: " + pid);
    }

    private void test_GetPName() {
        Log.i(TAG, "----- 获取当前进程名称 -----");
        appendLog("\n----- 获取当前进程名称 -----");

        // 获取当前进程名称
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            /* Android 13新增的接口 */
            String processName = Process.myProcessName();
            Log.i(TAG, "当前进程名称: " + processName);
            appendLog("当前进程名称: " + processName);
        } else {
            /* Android 13之前的版本 */
            int currentPID = Process.myPid();
            String processName = null;
            ActivityManager am = getSystemService(ActivityManager.class);
            // 遍历进程列表，寻找进程ID与当前进程相同的项，然后获取其名称。
            for (ActivityManager.RunningAppProcessInfo processInfo : am.getRunningAppProcesses()) {
                if (processInfo.pid == currentPID) {
                    processName = processInfo.processName;
                    break;
                }
            }
            Log.i(TAG, "当前进程名称: " + processName);
            appendLog("当前进程名称: " + processName);
        }
    }

    private void test_IsMainProcess() {
        Log.i(TAG, "----- 判断当前进程是否为主进程 -----");
        appendLog("\n----- 判断当前进程是否为主进程 -----");

        // 遍历当前应用的所有进程，查找不带后缀的进程。
        int mainPID = -1;
        ActivityManager am = getSystemService(ActivityManager.class);
        for (ActivityManager.RunningAppProcessInfo processInfo : am.getRunningAppProcesses()) {
            if (processInfo.processName.equals(getPackageName())) {
                mainPID = processInfo.pid;
                break;
            }
        }
        // 判断当前进程ID是否与主进程ID相同
        boolean isMainProcess = (Process.myPid() == mainPID);
        Log.i(TAG, "当前进程是否为主进程: " + isMainProcess);
        appendLog("当前进程是否为主进程: " + isMainProcess);
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
