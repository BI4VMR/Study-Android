package net.bi4vmr.study.event;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiEventBinding;

public class TestUIEvent extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIEvent.class.getSimpleName();

    private TestuiEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 设置OnCheckedChangeListener
        binding.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            /**
             * Name        : "onCheckedChanged()"
             * <p>
             * Description : 当按钮的开关状态发生改变时，该回调方法将被触发。
             *
             * @param buttonView 事件源控件。
             * @param isChecked 控件当前的开关状态。
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean userInput = buttonView.isPressed();
                Log.i(TAG, "OnCheckedChanged. State:[" + isChecked + "] UserInput:[" + userInput + "]");
            }
        });

        binding.btnSwitch.setOnClickListener(v -> {
            binding.toggleButton.toggle();
        });
    }
}
