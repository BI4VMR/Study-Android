package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.bi4vmr.study.base.theme.TestComposeTheme

/**
 * 测试界面：基本应用。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIBase2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    // 放置控件
                    Greeting()
                }
            }
        }
    }

    // 声明Compose组件
    @Composable
    fun Greeting() {
        val count: MutableState<Int> = remember { mutableStateOf(0) }
        Column {
            Text("当前数值： ${count.value}")
            Button(onClick = { count.value++ }) {
                Text("增加")
            }
            Button(onClick = { count.value-- }) {
                Text("减少")
            }
        }
    }

    @Composable
    fun Greeting2() {
        var count: Int by remember { mutableStateOf(0) }
        Column {
            Text("当前数值： $count")
            Button(onClick = { count++ }) {
                Text("增加")
            }
            Button(onClick = { count-- }) {
                Text("减少")
            }
        }
    }

    // 声明Compose组件预览
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TestComposeTheme {
            Greeting()
        }
    }

    // 声明Compose组件预览
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        TestComposeTheme {
            Greeting2()
        }
    }
}
