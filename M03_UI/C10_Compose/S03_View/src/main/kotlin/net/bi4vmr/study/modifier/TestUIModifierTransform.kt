package net.bi4vmr.study.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
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
class TestUIModifierTransform : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 放置控件
                    FixSize(
                        name = "Android",
                        // modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    // 背景
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
