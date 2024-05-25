package net.bi4vmr.study.xmlattrs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiXmlattrsKtBinding

class TestUIXMLAttrsKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIXMLAttrsKT::class.java.simpleName}"
    }

    private val binding: TestuiXmlattrsKtBinding by lazy {
        TestuiXmlattrsKtBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
