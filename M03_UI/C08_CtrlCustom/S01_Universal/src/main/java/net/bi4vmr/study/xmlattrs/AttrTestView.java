package net.bi4vmr.study.xmlattrs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.AttrTestViewBinding;

/**
 * 自定义控件：属性类型测试。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint({"SetTextI18n", "CustomViewStyleable"})
public class AttrTestView extends FrameLayout {

    private static final String TAG = "TestApp-" + AttrTestView.class.getSimpleName();

    private AttrTestViewBinding binding;

    // 构造方法1
    public AttrTestView(@NonNull Context context) {
        // 调用构造方法2，XML属性集合传入空值，避免重复书写“初始化视图”的逻辑。
        this(context, null);
    }

    // 构造方法2
    public AttrTestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 将布局文件渲染生成View实例
        binding = AttrTestViewBinding.inflate(LayoutInflater.from(getContext()), this, true);

        // 如果当前实例不是通过布局文件生成的，则不必解析XML属性。
        if (attrs == null) {
            return;
        }

        testBaseType(attrs);
        testTextType(attrs);
        testDimenType(attrs);
        testFractionType(attrs);
        testColorType(attrs);
        testEnumType(attrs);
        testFlagType(attrs);
        testRefType(attrs);
        testMultiTypes(attrs);
    }

    /**
     * 示例三：基本数据类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些基本数据类型的XML属性，并在自定义View中解析它们。
     */
    private void testBaseType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 获取布尔值
        boolean booleanValue = ta.getBoolean(R.styleable.AttrTypes_booleanValue, false);
        binding.tvBooleanValue.setText(booleanValue + "");

        // 获取整型值
        int intValue = ta.getInt(R.styleable.AttrTypes_integerValue, 0);
        binding.tvIntegerValue.setText(intValue + "");

        // 获取浮点值
        float floatValue = ta.getFloat(R.styleable.AttrTypes_floatValue, 0.0F);
        binding.tvFloatValue.setText(floatValue + "");

        ta.recycle();
    }

    /**
     * 示例四：文本类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些文本类型的XML属性，并在自定义View中解析它们。
     */
    private void testTextType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 获取文本（忽略格式）
        String textValue1 = ta.getString(R.styleable.AttrTypes_textValue1);
        binding.tvTextValue1.setText(textValue1);

        // 获取文本（解析格式）
        CharSequence textValue2 = ta.getText(R.styleable.AttrTypes_textValue2);
        binding.tvTextValue2.setText(textValue2);

        ta.recycle();
    }

    /**
     * 示例五：尺寸类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些尺寸类型的XML属性，并在自定义View中解析它们。
     */
    private void testDimenType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 解析尺寸为像素值
        Float dimenValue = ta.getDimension(R.styleable.AttrTypes_dimenValue, 1.0F);
        binding.tvDimenValue.setText(dimenValue + "");

        // 解析尺寸为像素值（如果有小数部分，则四舍五入到整数。）
        int dimenValue2 = ta.getDimensionPixelSize(R.styleable.AttrTypes_dimenValue, 1);
        // 解析尺寸为像素值（如果有小数部分，则丢弃并截断到整数。）
        int dimenValue3 = ta.getDimensionPixelOffset(R.styleable.AttrTypes_dimenValue, 1);

        ta.recycle();
    }

    /**
     * 示例六：比例类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些比例类型的XML属性，并在自定义View中解析它们。
     */
    private void testFractionType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 解析比例
        float fractionValue1 = ta.getFraction(R.styleable.AttrTypes_fractionValue1, 500, 1000, 1.0F);
        binding.tvFractionValue1.setText(fractionValue1 + "");

        // 解析比例
        float fractionValue2 = ta.getFraction(R.styleable.AttrTypes_fractionValue2, 500, 1000, 1.0F);
        binding.tvFractionValue2.setText(fractionValue2 + "");

        ta.recycle();
    }

    /**
     * 示例七：颜色类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些颜色类型的XML属性，并在自定义View中解析它们。
     */
    private void testColorType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 解析颜色属性的值为色值
        @ColorInt
        int colorValue = ta.getColor(R.styleable.AttrTypes_colorValue, Color.BLACK);
        binding.tvColorValue.setTextColor(colorValue);

        ta.recycle();
    }

    /**
     * 示例八：枚举类型属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些枚举类型的XML属性，并在自定义View中解析它们。
     */
    private void testEnumType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 获取枚举对应的整型值
        int enumIndex = ta.getInt(R.styleable.AttrTypes_enumValue, 0);
        binding.tvEnumValue.setText(enumIndex + "");

        ta.recycle();
    }

    /**
     * 示例九：标志位属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些标志位类型的XML属性，并在自定义View中解析它们。
     */
    private void testFlagType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 获取标志位组合对应的整型值
        int flags = ta.getInt(R.styleable.AttrTypes_flagValue, 0);
        binding.tvFlagValue.setText(flags + "");

        ta.recycle();
    }

    /**
     * 示例十：引用属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些引用类型的XML属性，并在自定义View中解析它们。
     */
    private void testRefType(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 将资源解析为Drawable
        Drawable drawable = ta.getDrawable(R.styleable.AttrTypes_refValue1);
        binding.ivRefType1.setImageDrawable(drawable);

        // 将资源解析为ColorStateList
        ColorStateList csl = ta.getColorStateList(R.styleable.AttrTypes_refValue2);
        binding.cbRefType2.setTextColor(csl);

        // 将资源解析为CharSequence数组
        CharSequence[] array = ta.getTextArray(R.styleable.AttrTypes_refValue3);
        binding.tvRefType3.setText(TextUtils.concat(array));

        ta.recycle();
    }

    /**
     * 示例十一：组合属性的使用方法。
     * <p>
     * 在本示例中，我们定义一些支持多种类型的XML属性，并在自定义View中解析它们。
     */
    private void testMultiTypes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AttrTypes);

        // 将资源解析为Drawable
        Drawable drawable = ta.getDrawable(R.styleable.AttrTypes_multiTypeValue);
        binding.ivMultiTypes.setImageDrawable(drawable);

        ta.recycle();
    }
}
