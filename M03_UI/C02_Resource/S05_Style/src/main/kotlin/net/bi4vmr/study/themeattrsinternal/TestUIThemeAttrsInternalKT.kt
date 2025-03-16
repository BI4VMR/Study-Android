package net.bi4vmr.study.themeattrsinternal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiThemeAttrsInternalBinding

class TestUIThemeAttrsInternalKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIThemeAttrsInternalKT::class.java.simpleName}"
    }

    private val binding: TestuiThemeAttrsInternalBinding by lazy {
        TestuiThemeAttrsInternalBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
