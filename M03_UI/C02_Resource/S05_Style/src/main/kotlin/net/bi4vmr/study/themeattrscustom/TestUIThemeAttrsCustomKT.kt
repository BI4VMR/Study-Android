package net.bi4vmr.study.themeattrscustom

import android.content.Intent
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiThemeAttrsCustomBinding

class TestUIThemeAttrsCustomKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIThemeAttrsCustomKT::class.java.simpleName}"
    }

    private val binding: TestuiThemeAttrsCustomBinding by lazy {
        TestuiThemeAttrsCustomBinding.inflate(layoutInflater)
    }

    // 当前主题编号，默认为"0"。
    private var themeType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 根据外部传入的参数选择主题
        intent?.let {
            val type: Int = it.getIntExtra("Theme", 0)
            if (type == 1) {
                setTheme(R.style.Theme_TypeB)
            } else {
                setTheme(R.style.Theme_TypeA)
            }
            themeType = type
        }

        setContentView(binding.root)

        // 通过代码引用主题属性，为控件设置颜色。
        val ta: TypedArray = theme.obtainStyledAttributes(intArrayOf(R.attr.titleColor))
        val color = ta.getColor(0, Color.BLACK)
        ta.recycle()
        binding.text2.setTextColor(color)

        binding.btnSwitchTheme.setOnClickListener {
            switchTheme()
        }
    }

    // 切换主题
    private fun switchTheme() {
        val intent = Intent()
        intent.putExtra("Theme", themeType xor 1)
        intent.setClass(this, this::class.java)
        // 禁止启动动画（可选）
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        // 在某些设备上"FLAG_ACTIVITY_NO_ANIMATION"无效，需要额外添加此配置。
        overridePendingTransition(0, 0)

        finish()
    }
}
