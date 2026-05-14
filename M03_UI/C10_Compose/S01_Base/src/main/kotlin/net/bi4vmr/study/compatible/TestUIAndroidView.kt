package net.bi4vmr.study.compatible

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.material.button.MaterialButton
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：在Compose中使用AndroidView。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIAndroidView : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 显示Compose UI组件
        setContent {
            // 应用主题
            TestComposeTheme {
                // 应用布局
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    var selectedItem by remember { mutableStateOf(0) }

                    // 添加原生View到Compose
                    AndroidView(
                        modifier = Modifier.fillMaxSize(),
                        factory = { context ->
                            // 创建View的实例
                            MaterialButton(context).apply {
                                // 设置View的点击事件，更新状态，这会触发重组
                                setOnClickListener {
                                    selectedItem = 1
                                }
                            }
                        },
                        update = { view ->
                            // 更新View的状态
                            // 这里读了状态，所以重组时update会被再次调用，view能拿到最新的状态
                            view.text.toString()
                        }
                    )
                }
            }
        }
    }
}
