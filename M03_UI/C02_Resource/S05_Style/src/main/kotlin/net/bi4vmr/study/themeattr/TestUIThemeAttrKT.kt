package net.bi4vmr.study.themeattr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiThemeAttrBinding

class TestUIThemeAttrKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIThemeAttrKT::class.java.simpleName}"
    }

    private val binding: TestuiThemeAttrBinding by lazy {
        TestuiThemeAttrBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
