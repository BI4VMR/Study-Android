package net.bi4vmr.study.base;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.lang.reflect.Method;

/**
 * 测试界面：TODO 添加简述。
 * <p>
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUserInfo extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIUserInfo.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetUserHandle.setOnClickListener(v -> testGetUserHandle());
        binding.btnGetUserID.setOnClickListener(v -> testGetUserID());
        binding.btnGetUID.setOnClickListener(v -> testGetUID());
    }

    // 获取当前用户的UserHandle对象
    private void testGetUserHandle() {
        Log.i(TAG, "--- 获取当前用户的UserHandle对象 ---");
        binding.tvLog.append("\n--- 获取当前用户的UserHandle对象 ---\n");

        UserHandle userHandle = Process.myUserHandle();
        Log.i(TAG, userHandle.toString());
        binding.tvLog.append("\n" + userHandle + "\n");
    }

    // 获取当前用户的ID
    private void testGetUserID() {
        Log.i(TAG, "--- 获取当前用户的ID ---");
        binding.tvLog.append("\n--- 获取当前用户的ID ---\n");

        // 隐藏接口，需要引入Framework JAR包或通过反射调用。
        try {
            Method method = ActivityManager.class.getMethod("getCurrentUser");
            Integer userID = (Integer) method.invoke(null);
            assert userID != null;
            Log.i(TAG, String.valueOf(userID));
            binding.tvLog.append("\n" + userID + "\n");
        } catch (Exception e) {
            Log.e(TAG, "反射操作失败：" + e.getMessage());
            binding.tvLog.append("\n反射操作失败：" + e.getMessage() + "\n");
        }
    }

    // 获取当前应用的UID
    private void testGetUID() {
        Log.i(TAG, "--- 获取当前应用的UID ---");
        binding.tvLog.append("\n--- 获取当前应用的UID ---\n");

        int uid = Process.myUid();
        Log.i(TAG, String.valueOf(uid));
        binding.tvLog.append("\n" + uid + "\n");

        // 1010105
        // int i = 10 * 100000 + (10140 % 100000);
        int uid1 = 10105 / PER_USER_RANGE;
        int aid = 10105 % PER_USER_RANGE;
        binding.tvLog.append("\n uid1:" + uid1 + "\n");
        binding.tvLog.append("\n aid:" + aid + "\n");
        binding.tvLog.append("\n 10 % 1010:" + 10 % 1010 + "\n");
        binding.tvLog.append("\n zzz:" + getUid(10, 10105) + "\n");
    }

    public static final int PER_USER_RANGE = 100000;

    public static int getUid(int userId, int appId) {
        return userId * PER_USER_RANGE + (appId % PER_USER_RANGE);
    }
}
