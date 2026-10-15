package net.bi4vmr.study.dialog

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.WindowCompat
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面： Dialog 。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIDialog : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* 根据主题设置状态栏图标颜色 */
        val darkModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val darkMode = (darkModeFlag == Configuration.UI_MODE_NIGHT_YES)
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = !darkMode

        setContent {
            TestComposeTheme {
                // 顶层容器
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .systemBarsPadding()
                ) {
                    AlertDialogTest()
                    DialogTest()
                }
            }
        }
    }


    @Composable
    fun AlertDialogTest() = Example("AlertDialog") {
        // 通过 Compose 状态控制弹窗是否显示
        var dialogState: Boolean by remember { mutableStateOf(false) }

        Button(
            onClick = {
                dialogState = true
            }
        ) { Text("打开窗口") }

        // 状态变量为 `true` 时显示弹窗
        if (dialogState) {
            AlertDialog(
                // 标题（可选）
                title = { Text("标题") },
                // 内容（可选）
                text = { Text("内容区域", modifier = Modifier.background(Color.Cyan.copy(0.3F))) },
                // 弹窗属性
                properties = DialogProperties(usePlatformDefaultWidth = false),
                // 取消行为
                onDismissRequest = { dialogState = false },
                // 确认按钮
                confirmButton = {
                    Button(onClick = { dialogState = false }) { Text("确定") }
                },
                // 取消按钮
                dismissButton = {
                    Button(onClick = { dialogState = false }) { Text("取消") }
                }
            )
        }
    }

    @Composable
    fun DialogTest() = Example("Dialog") {
        // 通过 Compose 状态控制弹窗是否显示
        var dialogState: Boolean by remember { mutableStateOf(false) }

        Button(
            onClick = {
                dialogState = true
            }
        ) { Text("打开窗口") }

        // 状态变量为 `true` 时显示弹窗
        if (dialogState) {
            Dialog(
                // 弹窗属性
                properties = DialogProperties(usePlatformDefaultWidth = false),
                // 取消行为
                onDismissRequest = { dialogState = false },
            ) {
                /* 内容 */
                Text("内容区域", modifier = Modifier.background(Color.Cyan.copy(0.3F)))
            }
        }
    }


    // 示例框架
    @Composable
    private inline fun Example(title: String, crossinline content: @Composable () -> Unit) {
        Column(Modifier.padding(10.dp)) {
            // 显示标题
            Text(text = title)
            // 显示内容
            Box(
                // 为容器设置边框以便观察布局属性的效果
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .border(1.dp, Color.Blue)
            ) {
                content()
            }
        }
    }
}
