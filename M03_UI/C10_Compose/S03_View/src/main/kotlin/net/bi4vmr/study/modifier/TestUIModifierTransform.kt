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
 * 测试界面：Modifier - 变换。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIModifierTransform : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            TestComposeTheme {
                FixSize()
            }
        }
    }

    // 背景
    @Composable
    fun FixSize() {
        Text(
            "12345",
            modifier = Modifier.size(10.dp)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun FixSizePreview() {
        TestComposeTheme {
            FixSize()
        }
    }


    // 边框
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
}
