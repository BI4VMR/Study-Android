package net.bi4vmr.study.appcenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import net.bi4vmr.study.common.TestComposeTheme

/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class AppCenterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                AppCenter()
            }
        }
    }
}
