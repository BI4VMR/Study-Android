package net.bi4vmr.study.base;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiUserinfoBinding;

import java.lang.reflect.Method;

/**
 * 测试界面：用户身份API。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIUserInfo extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIUserInfo.class.getSimpleName();

    private TestuiUserinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUserinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetUserID.setOnClickListener(v -> testGetUserID());
        binding.btnGetUID.setOnClickListener(v -> testGetUID());
        binding.btnGetUserHandle.setOnClickListener(v -> testGetUserHandle());
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
            Log.e(TAG, "反射操作失败：" + e.getMessage(), e);
            binding.tvLog.append("\n反射操作失败：" + e.getMessage() + "\n");
        }
    }

    // 获取当前应用的UID
    private void testGetUID() {
        Log.i(TAG, "--- 获取当前应用的UID ---");
        binding.tvLog.append("\n--- 获取当前应用的UID ---\n");

        // 获取当前应用的UID
        int uid = Process.myUid();
        Log.i(TAG, "当前应用的UID：" + uid);
        binding.tvLog.append("当前应用的UID：" + uid + "\n");

        // 推算UserID和AppID

        // UserID的范围，用于分离UserID和AppID。
        final int PER_USER_RANGE = 100000;

        int userID = uid / PER_USER_RANGE;
        int appID = uid % PER_USER_RANGE;
        Log.i(TAG, "UserID: " + userID + ", AppID: " + appID);
        binding.tvLog.append("UserID: " + userID + ", AppID: " + appID + "\n");

        // 将UserID和AppID组合成UID
        int uid2 = (userID * PER_USER_RANGE) + (appID % PER_USER_RANGE);
        Log.i(TAG, "组合生成UID: " + uid2);
        binding.tvLog.append("组合生成UID: " + uid2 + "\n");
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
