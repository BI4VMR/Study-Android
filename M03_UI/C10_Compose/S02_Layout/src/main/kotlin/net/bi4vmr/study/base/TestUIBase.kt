package net.bi4vmr.study.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import net.bi4vmr.study.common.TestComposeTheme

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
                Scaffold(
                    Modifier.fillMaxSize()
                ) { innerPadding ->
                    // 放置控件
                    Column(
                        modifier = Modifier.padding(innerPadding),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        (1..3).forEach { i ->
                            Text(
                                text = "$i"
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        (1..3).forEach { i ->
                            Text(
                                text = "$i"
                            )
                        }
                    }

                    Box {
                        (1..3).forEach { i ->
                            Text(
                                text = "$i"
                            )
                        }
                    }
                }
            }
        }
    }
}
