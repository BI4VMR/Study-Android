package net.bi4vmr.study.themedark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiThemeDarkBinding

/**
 * 测试界面：深色模式。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIThemeDarkKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIThemeDarkKT::class.java.simpleName}"
    }

    private val binding: TestuiThemeDarkBinding by lazy {
        TestuiThemeDarkBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnSwitchTheme.setOnClickListener {
            switchTheme()
        }
    }

    // 切换主题
    private fun switchTheme() {
        // val intent = Intent()
        // intent.putExtra("Theme", themeType xor 1)
        // intent.setClass(this, this::class.java)
        // // 禁止启动动画（可选）
        // intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        // startActivity(intent)
        // // 在某些设备上"FLAG_ACTIVITY_NO_ANIMATION"无效，需要额外添加此配置。
        // overridePendingTransition(0, 0)
        //
        // finish()
    }
}
