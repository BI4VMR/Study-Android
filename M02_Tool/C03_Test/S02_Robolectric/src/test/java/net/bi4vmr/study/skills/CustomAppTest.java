package net.bi4vmr.study.skills;

import android.app.Application;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * 测试类使用自定义Application。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner.class)
@Config(application = AppForTest.class)
public class CustomAppTest {

    @Test
    public void test_CustomApp() {
        System.out.println("----- Test CustomApp start -----");

        Application app = RuntimeEnvironment.getApplication();
        System.out.println(app);

        System.out.println("----- Test CustomApp end -----");
    }

    @Test
    public void test_Env() {
        System.out.println("IsRobolectricEnv? " + isRobolectricEnv());
    }

    boolean isRobolectricEnv() {
        return Build.FINGERPRINT.equals("robolectric");
    }
}
