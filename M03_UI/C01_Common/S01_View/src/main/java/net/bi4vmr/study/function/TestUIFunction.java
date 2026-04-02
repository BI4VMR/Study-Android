package net.bi4vmr.study.function;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiFunctionBinding;

public class TestUIFunction extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunction.class.getSimpleName();

    private TestuiFunctionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* 点击事件 */
        // 获取按钮"btnTest"的实例
        // Button btnTest = findViewById(R.id.btnTest);
        // 实现点击监听器并传递给"btnTest"
        // btnTest.setOnClickListener(new View.OnClickListener() {
        //
        //     @Override
        //     public void onClick(View v) {
        //         Log.i(TAG, "按钮Test被点击了！");
        //         binding.tvLog.append("按钮Test被点击了！\n");
        //     }
        // });

        // view 在左侧的坐标
        binding.image.getLeft();
        // x轴偏移量，初始为0,播放动画后会改变，向右为正，向左为负
        binding.image.getTranslationX();
        // 左侧坐标，Left+TranslationX
        binding.image.getX();
    }
}
