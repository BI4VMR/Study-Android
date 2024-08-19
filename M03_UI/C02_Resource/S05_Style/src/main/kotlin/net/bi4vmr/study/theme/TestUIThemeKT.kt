package net.bi4vmr.study.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiThemeBinding

class TestUIThemeKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIThemeKT::class.java.simpleName}"
    }

    private val binding: TestuiThemeBinding by lazy {
        TestuiThemeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
