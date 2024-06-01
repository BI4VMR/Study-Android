package net.bi4vmr.study.style

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiStyleBinding

class TestUIStyleKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIStyleKT::class.java.simpleName
    }

    private val binding: TestuiStyleBinding by lazy {
        TestuiStyleBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
