package net.bi4vmr.study.i18n

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiMultilanguageBinding

class TestUIMultiLanguageKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-" + TestUIMultiLanguageKT::class.java.simpleName
    }

    private val binding: TestuiMultilanguageBinding by lazy {
        TestuiMultilanguageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
