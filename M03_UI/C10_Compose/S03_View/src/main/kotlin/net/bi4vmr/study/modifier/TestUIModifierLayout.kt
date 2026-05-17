package net.bi4vmr.study.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：Modifier - 布局。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIModifierLayout : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                FixSize(
                    name = "Android",
                    // modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

    // 固定尺寸
    @Composable
    fun FixSize(name: String) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = Modifier.size(10.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun FixSizePreview() {
        TestComposeTheme {
            FixSize("Android")
        }
    }


    // 跟随父容器
    @Composable
    fun MatchParent(name: String) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = Modifier.size(10.dp)
        )
    }

    // 包裹内容
    @Composable
    fun WrapContent(name: String) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = Modifier.size(10.dp)
        )
    }

    // 最小限制

    // 是否允许超出父容器

    // 边距
}
