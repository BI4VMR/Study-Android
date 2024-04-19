package net.bi4vmr.study.event

import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiEventBinding

class TestUIEventKT : AppCompatActivity() {

    companion object {
        private val TAG: String = TestUIEventKT::class.java.simpleName
    }

    private val binding: TestuiEventBinding by lazy {
        TestuiEventBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 设置OnCheckedChangeListener
        binding.toggleButton.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {

            /**
             * Name        : "onCheckedChanged()"
             *
             * Description : 当按钮的开关状态发生改变时，该回调方法将被触发。
             *
             * @param buttonView 事件源控件。
             * @param isChecked 控件当前的开关状态。
             */
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                val userInput: Boolean = buttonView.isPressed
                Log.i(TAG, "OnCheckedChanged. State:[$isChecked] UserInput:[$userInput]")
            }
        })

        binding.btnSwitch.setOnClickListener {
            binding.toggleButton.toggle()
        }
    }
}
