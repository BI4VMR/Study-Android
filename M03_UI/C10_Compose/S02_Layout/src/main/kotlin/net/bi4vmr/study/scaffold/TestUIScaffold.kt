package net.bi4vmr.study.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：全局属性。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIScaffold : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                // 顶层容器
                Column(modifier = Modifier.fillMaxSize()) {
                    Text("宽高约束：")
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
