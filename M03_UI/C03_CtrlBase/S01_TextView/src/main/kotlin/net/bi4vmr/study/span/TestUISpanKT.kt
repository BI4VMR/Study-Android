package net.bi4vmr.study.span

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiSpanBinding

class TestUISpanKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUISpanKT::class.java.simpleName
    }

    private val binding: TestuiSpanBinding by lazy {
        TestuiSpanBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
