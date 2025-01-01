package net.bi4vmr.study.constant.base;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiConstantBaseBinding;

import java.util.Arrays;

/**
 * 测试界面：常量 - 基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint("SetTextI18n")
public class TestUIConstantBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIConstantBase.class.getSimpleName();

    private TestuiConstantBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiConstantBaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        testConstant();
        testConstantArray();
        testResArray();
    }

    /**
     * 示例一：常量资源的简单应用。
     * <p>
     * 在本示例中，我们定义一些常量资源，然后使用这些资源。
     */
    private void testConstant() {
        // 将布尔常量资源解析为布尔值
        boolean booleanValue = getResources().getBoolean(R.bool.booleanValue);

        // 将布尔常量资源解析为布尔值
        int intValue = getResources().getInteger(R.integer.intValue);

        // 将文本常量资源解析为字符串（忽略格式）
        String stringValue1 = getResources().getString(R.string.stringValue);
        // 将文本常量资源解析为字符序列（解析格式）
        CharSequence stringValue2 = getResources().getText(R.string.stringValue);

        binding.tvBoolean.setText(booleanValue + "");
        binding.tvInt.setText(intValue + "");
        binding.tvStringRaw.setText(stringValue1);
        binding.tvCharSequence.setText(stringValue2);
    }

    /**
     * 示例二：常量数组资源的简单应用。
     * <p>
     * 在本示例中，我们定义一些常量数组资源，并使用这些资源。
     */
    private void testConstantArray() {
        // 获取整数数组
        int[] intArray = getResources().getIntArray(R.array.intArray);

        // 获取字符串数组（忽略格式）
        String[] stringArray1 = getResources().getStringArray(R.array.stringArray);
        // 获取字符串数组（解析格式）
        CharSequence[] stringArray2 = getResources().getTextArray(R.array.stringArray);

        binding.tvIntArray.setText(Arrays.toString(intArray));
        binding.tvStringArray.setText(Arrays.toString(stringArray1));
        binding.tvTextArray.setText(TextUtils.concat(stringArray2));
    }

    /**
     * 示例三：资源数组的简单应用。
     * <p>
     * 在本示例中，我们定义一些资源数组，并使用这些资源。
     */
    private void testResArray() {
        // 获取资源数组
        TypedArray colorResArray = getResources().obtainTypedArray(R.array.colorResArray);
        // 遍历数组获取每个颜色资源
        for (int i = 0; i < colorResArray.length(); i++) {
            // 使用索引获取单个资源
            int id = colorResArray.getResourceId(i, 0);
            if (id == 0) {
                return;
            }

            // 将资源ID解析为色值
            int color = ContextCompat.getColor(this, id);
            // 使用颜色值
            TextView tv = new TextView(this);
            tv.setText(" " + i + " ");
            tv.setTextSize(32.0F);
            tv.setBackgroundColor(color);
            binding.container.addView(tv);
        }

        // 释放资源数组
        colorResArray.recycle();
    }
}
