package net.bi4vmr.study.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(LayoutInflater.from(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {
            tbtnShowSecPoint.setOnCheckedChangeListener { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    analogClock.setSecondPointerShowState(isChecked)
                }
            }

            tbtnSecSmoothMove.setOnCheckedChangeListener { buttonView, isChecked ->
                if (buttonView.isPressed) {
                    analogClock.setSecondPointerSmoothMove(isChecked)
                }
            }
        }
    }
}
