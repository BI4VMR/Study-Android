package net.bi4vmr.study.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import net.bi4vmr.study.common.TestComposeTheme

/**
 * 测试界面：Modifier - 布局。
 *
 * @since 1.0.0
 * @author bi4vmr@outlook.com
 */
class TestUIModifierStyle : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        ColorBackground()
                        ShapeBackground()
                        GradientBackground()
                    }
                }
            }
        }
    }

    // 纯色背景
    @Composable
    fun ColorBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Color.Gray)
        )
    }


    // 形状背景
    @Composable
    fun ShapeBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Color.Gray, RoundedCornerShape(16.dp))
        )
    }


    // 渐变背景
    @Composable
    fun GradientBackground() {
        Text(
            text = "我能吞下玻璃而不伤身体。",
            modifier = Modifier.background(Brush.linearGradient(listOf(Color.Red, Color.Green, Color.Blue)))
        )
    }
}
