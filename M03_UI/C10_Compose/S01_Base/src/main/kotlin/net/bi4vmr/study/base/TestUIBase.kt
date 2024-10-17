package net.bi4vmr.study.base

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.base.theme.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBase : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 放置控件
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    // 声明Compose组件
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        // 声明文本框
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    // 声明Compose组件预览
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TestComposeTheme {
            Greeting("Android")
        }
    }

    @Composable
    @Preview(showBackground = true)
    fun ClickableMessagePreview() {
        TestComposeTheme {
            ClickableMessage("如何解开安全带", true, {})
        }
    }

    @Composable
    fun ClickableMessage(
        message: String,
        isUserMe: Boolean,
        authorClicked: (String) -> Unit
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(32.dp, 32.dp, 32.dp, 0.dp)
        ) {
            ClickableText(
                text = AnnotatedString(message),
                style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    Log.d("TestApp", "onClick")
                }
            )
        }
    }

    @Composable
    fun ClickableMessage(
        message: String,
        isUserMe: Boolean,
        authorClicked: (String) -> Unit
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(32.dp, 32.dp, 32.dp, 0.dp)
        ) {
            ClickableText(
                text = AnnotatedString(message),
                style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
                modifier = Modifier.padding(16.dp),
                onClick = {
                    Log.d("TestApp", "onClick")
                }
            )
        }
    }
}
