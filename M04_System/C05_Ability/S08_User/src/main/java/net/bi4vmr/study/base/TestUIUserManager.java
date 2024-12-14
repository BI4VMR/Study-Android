package net.bi4vmr.study.base;

import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiUsermanagerBinding;

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

        binding.btnSupportMultiUser.setOnClickListener(v -> testSupportMultiUser());
        binding.btnGetUID.setOnClickListener(v -> testGetUID());
        binding.btnGetUserHandle.setOnClickListener(v -> testGetUserHandle());
    }

    // 判断当前系统是否支持多用户
    private void testSupportMultiUser() {
        Log.i(TAG, "--- 判断当前系统是否支持多用户 ---");
        binding.tvLog.append("\n--- 判断当前系统是否支持多用户 ---\n");

        boolean supportMultiUser = UserManager.supportsMultipleUsers();
        Log.i(TAG, "当前系统是否支持多用户：" + supportMultiUser);
        binding.tvLog.append("当前系统是否支持多用户：" + supportMultiUser + "\n");

        // String name = userManager.getUserName();
        // Log.i(TAG, "name：" + name);
        // binding.tvLog.append("name：" + name + "\n");
    }

    // 获取当前应用的UID
    private void testGetUsers() {
        Log.i(TAG, "--- 获取当前应用的UID ---");
        binding.tvLog.append("\n--- 获取当前应用的UID ---\n");


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
