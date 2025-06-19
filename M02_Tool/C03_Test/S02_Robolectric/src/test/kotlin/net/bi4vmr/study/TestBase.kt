package net.bi4vmr.study

import android.app.Application
import android.content.Context
import android.os.Build
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * TODO 添加简述。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.TIRAMISU])
class TestBase {

    private lateinit var mockApplication: Application
    private lateinit var mockContext: Context

    @Before
    fun setUp() {
        /* 构建模拟环境 */
        mockApplication = RuntimeEnvironment.getApplication()
        mockContext = mockApplication.applicationContext
    }

    @Test
    fun test_Env() {
        println("----- TestEnv start -----")

        println("filesDir:[${mockContext.filesDir}]")

        println("----- TestEnv end -----")
    }
}
