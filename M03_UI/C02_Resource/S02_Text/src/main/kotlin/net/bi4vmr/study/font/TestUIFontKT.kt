package net.bi4vmr.study.font

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.TestuiFontBinding

class TestUIFontKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIFontKT::class.java.simpleName
    }

    private val binding: TestuiFontBinding by lazy {
        TestuiFontBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 获取字体对象（从"asset"目录加载）
        val typefaceFromAssert: Typeface =
            Typeface.createFromAsset(application.assets, "fonts/jetbrainsmono_regular.ttf")

        // 获取字体对象（从"res"目录加载）
        val typeface: Typeface = application.resources.getFont(R.font.jetbrainsmono)
        // 将字体设置到控件上
        binding.tvFontFile.typeface = typeface
        // 将字体与样式设置到控件上
        binding.tvFontFile.setTypeface(typeface, Typeface.BOLD_ITALIC)
    }
}
