package net.bi4vmr.study.function;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiFunctionGetviewBinding;

/**
 * 测试界面：常用方法 - 获取控件引用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIFunctionGetView extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIFunctionGetView.class.getSimpleName();

    private TestuiFunctionGetviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiFunctionGetviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 获取按钮控件的引用（控件不存在则为空值）
        TextView tvTitle = findViewById(R.id.text);
        // 设置文本颜色
        if (tvTitle != null) {
            tvTitle.setTextColor(Color.GREEN);
        }


        // Android 9(API 28)新增方法：获取控件引用，如果控件不存在则抛出异常：IllegalArgumentException。
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        //     TextView tvTitle1 = requireViewById(R.id.text);
        //     tvTitle1.setTextColor(Color.GREEN);
        // }


        // 推荐使用ViewBinding取代 `findViewById()` 方法。
        // binding.text.setTextColor(Color.GREEN);
    }
}
