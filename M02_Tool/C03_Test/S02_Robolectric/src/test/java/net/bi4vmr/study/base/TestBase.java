package net.bi4vmr.study.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import net.bi4vmr.study.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * 示例一：构建环境。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.TIRAMISU})
public class TestBase {

    private Application mockApplication;
    private Context mockContext;

    @Before
    public void setup() {
        /* 构建模拟环境 */
        // 获取Robolectric提供的模拟Application实例
        mockApplication = RuntimeEnvironment.getApplication();
        // 获取Context实例
        mockContext = mockApplication.getApplicationContext();
    }

    @Test
    public void test_Env() {
        System.out.println("----- TestEnv start -----");

        // 调用Android的API，验证模拟环境是否正常。
        System.out.println("获取应用名称:[" + mockContext.getString(R.string.app_name) + "]");

        System.out.println("----- TestEnv end -----");
    }
}
