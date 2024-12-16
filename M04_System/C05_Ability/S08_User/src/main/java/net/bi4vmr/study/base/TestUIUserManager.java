package net.bi4vmr.study.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiUsermanagerBinding;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 测试界面：用户管理API。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUserManager extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIUserManager.class.getSimpleName();

    private TestuiUsermanagerBinding binding;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUsermanagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userManager = getSystemService(UserManager.class);

        binding.btnFeatures.setOnClickListener(v -> testFeatures());
        binding.btnUserInfo.setOnClickListener(v -> testGetUserInfo());
        // binding.btnGetUserHandle.setOnClickListener(v -> testGetUserHandle());
    }

    // 多用户支持
    private void testFeatures() {
        Log.i(TAG, "--- 多用户支持 ---");
        binding.tvLog.append("\n--- 多用户支持 ---\n");

        // 判断当前系统是否支持多用户
        boolean supportMultiUser = UserManager.supportsMultipleUsers();
        Log.i(TAG, "当前系统是否支持多用户：" + supportMultiUser);
        binding.tvLog.append("当前系统是否支持多用户：" + supportMultiUser + "\n");

        // 判断当前系统用户是否为Headless模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            boolean isInHeadlessMode = UserManager.isHeadlessSystemUserMode();
            Log.i(TAG, "当前系统用户是否为Headless模式：" + isInHeadlessMode);
            binding.tvLog.append("当前系统用户是否为Headless模式：" + isInHeadlessMode + "\n");
        } else {
            Log.i(TAG, "API <= 30 (Android 11), not support.");
            binding.tvLog.append("API <= 30 (Android 11), not support.\n");
        }

        // 获取用户数量
        int userCount = userManager.getUserCount();
        Log.i(TAG, "用户数量：" + userCount);
        binding.tvLog.append("用户数量：" + userCount);

        IntentFilter filter = new IntentFilter();
        // 用户切换
        filter.addAction("android.intent.action.USER_SWITCHED");
        filter.addAction("android.intent.action.USER_ADDED");
        filter.addAction("android.intent.action.USER_REMOVED");
        filter.addAction(Intent.ACTION_USER_FOREGROUND);
        filter.addAction(Intent.ACTION_USER_BACKGROUND);
        // 用户私有存储区域已解锁
        filter.addAction(Intent.ACTION_USER_UNLOCKED);
        registerReceiver(new UserEventReceiver(), filter);
    }

    private class UserEventReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "--- action --- " + action);
            binding.tvLog.append("\n--- action --- " + action);
            if ("android.intent.action.USER_SWITCHED".equals(action)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    UserHandle userHandle = intent.getParcelableExtra(Intent.EXTRA_USER, UserHandle.class);
                    Log.i(TAG, "当前的UserHandle：" + userHandle);
                    binding.tvLog.append("当前的UserHandle：" + userHandle);
                } else {
                    // Intent.EXTRA_USER_HANDLE = "android.intent.extra.user_handle"
                    int userID = intent.getIntExtra("android.intent.extra.user_handle", -1000);
                    Log.i(TAG, "当前的UserID：" + userID);
                    binding.tvLog.append("当前的UserID：" + userID);
                }
            }
        }
    }

    // 获取当前用户信息
    private void testGetUserInfo() {
        Log.i(TAG, "--- 获取当前用户信息 ---");
        binding.tvLog.append("\n--- 获取当前用户信息 ---\n");

        // 判断当前用户是否为系统用户
        boolean systemUser = userManager.isSystemUser();
        Log.i(TAG, "当前用户是否为系统用户：" + systemUser);
        binding.tvLog.append("当前用户是否为系统用户：" + systemUser + "\n");

        // 判断当前用户是否已解锁
        boolean userUnlocked = userManager.isUserUnlocked();
        Log.i(TAG, "当前用户是否已解锁：" + userUnlocked);
        binding.tvLog.append("当前用户是否已解锁：" + userUnlocked + "\n");

        // 判断当前用户是否在前台
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            boolean userForeground = userManager.isUserForeground();
            Log.i(TAG, "当前用户是否在前台：" + userForeground);
            binding.tvLog.append("当前用户是否在前台：" + userForeground + "\n");
        } else {
            Log.i(TAG, "API <= 30 (Android 11), not support.");
            binding.tvLog.append("API <= 30 (Android 11), not support.\n");
        }

        // 获取用户名称
        String userName = userManager.getUserName();
        Log.i(TAG, "用户名称：" + userName);
        binding.tvLog.append("用户名称：" + userName + "\n");

        try {
            @SuppressLint("DiscouragedPrivateApi")
            Method method = UserManager.class.getDeclaredMethod("getUsers");
            Object object = method.invoke(userManager);
            if (object instanceof List<?>) {
                List<?> list = (List) object;
                Log.d(TAG, "getUsers:" + list);
            }
        } catch (Exception e) {
            Log.e(TAG, "反射操作失败：" + e.getMessage(), e);
            binding.tvLog.append("\n反射操作失败：" + e.getMessage() + "\n");
        }

        try {
            @SuppressLint("DiscouragedPrivateApi")
            Method method = UserManager.class.getDeclaredMethod("getUserHandles", boolean.class);
            Object object = method.invoke(userManager, false);
            if (object instanceof List<?>) {
                List<?> list = (List) object;
                Log.d(TAG, "getUserHandles:" + list);
            }
        } catch (Exception e) {
            Log.e(TAG, "反射操作失败：" + e.getMessage(), e);
            binding.tvLog.append("\n反射操作失败：" + e.getMessage() + "\n");
        }
    }

    // 获取当前用户的UserHandle对象
    private void testGetUserHandle() {
        Log.i(TAG, "--- 获取当前用户的UserHandle对象 ---");
        binding.tvLog.append("\n--- 获取当前用户的UserHandle对象 ---\n");

        UserHandle userHandle = Process.myUserHandle();
        Log.i(TAG, userHandle.toString());
        binding.tvLog.append("\n" + userHandle + "\n");
    }
}
