package net.bi4vmr.study.skills

import android.os.Build
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * 测试类使用自定义Application。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = AppForTestKT::class)
class CustomAppTestKT {

    @Test
    fun test_CustomApp() {
        println("----- Test CustomApp start -----")

        val app = RuntimeEnvironment.getApplication()
        println(app)

        println("----- Test CustomApp end -----")
    }

    @Test
    fun test_Env() {
        println("IsRobolectricEnv? ${isRobolectricEnv()}")
    }

    fun isRobolectricEnv(): Boolean {
        return Build.FINGERPRINT == "robolectric"
    }
}
