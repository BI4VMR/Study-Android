package net.bi4vmr.study

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 仪器化测试示例(KT)。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@RunWith(AndroidJUnit4::class)
class TestInstrumentKT {

    @Test
    fun useAppContext() {
        // 获取仪器化测试环境下的Context
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // 访问Android资源
        val appName: String = appContext.getString(R.string.app_name)
        Log.d("Test", "应用名称：$appName")
    }
}
