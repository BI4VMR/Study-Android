package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
            Row {
                RecommendSpeech("Test 0101")
                RecommendSpeech("Test")
                RecommendSpeech("T")
            }
        }
    }

    @Composable
    fun RecommendSpeech(
        content: String
    ) {
        ConstraintLayout {
            // 创建引用，便于设置约束条件。
            val (text, background) = createRefs()

            Box(
                modifier = Modifier
                    .alpha(0.1F)
                    .background(MaterialTheme.colorScheme.error, RoundedCornerShape(24.dp))
                    .constrainAs(background) {
                        top.linkTo(text.top)
                        bottom.linkTo(text.bottom)
                        start.linkTo(text.start)
                        end.linkTo(text.end)
                    }
            ){
                Text(
                    text = "“$content”",
                    modifier = Modifier
                        .padding(24.dp, 8.dp)
                )
            }

            Text(
                text = "“$content”",
                modifier = Modifier
                    .padding(24.dp, 8.dp)
                    .constrainAs(text) {}
            )
        }
    }
}
