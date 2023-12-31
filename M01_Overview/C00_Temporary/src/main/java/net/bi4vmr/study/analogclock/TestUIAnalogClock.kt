package net.bi4vmr.study.analogclock

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiAnalogclockBinding

class TestUIAnalogClock : AppCompatActivity() {

    companion object {
        private val TAG = TestUIAnalogClock::class.java.simpleName
    }

    private val binding: TestuiAnalogclockBinding by lazy {
        TestuiAnalogclockBinding.inflate(LayoutInflater.from(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {
            tbtnShowSecPoint.setOnCheckedChangeListener { _, isChecked ->
                analogClock.secondPointerShowState = isChecked
            }

            tbtnSecSmoothMove.setOnCheckedChangeListener { _, isChecked ->
                binding.analogClock.isSecondPointerSmoothMove = isChecked
            }
        }
    }
}
