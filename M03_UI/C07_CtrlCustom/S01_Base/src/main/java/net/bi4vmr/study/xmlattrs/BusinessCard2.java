package net.bi4vmr.study.xmlattrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.BusinessCardBinding;

/**
 * Name        : BusinessCard2
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2024-04-22 17:22
 * <p>
 * Description : 自定义控件示例 - 名片（使用XML属性）。
 */
public class BusinessCard2 extends FrameLayout {

    private static final String TAG = "TestApp-" + BusinessCard2.class.getSimpleName();

    private BusinessCardBinding binding;

    /**
     * Name        : 构造方法1
     * <p>
     * Description : 该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param context 上下文环境。
     *                <p>
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     */
    public BusinessCard2(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Name        : 构造方法2
     * <p>
     * Description : 如果我们需要在布局文件中引用控件，该方法是必选的，否则系统解析XML时将会报错。
     *
     * @param context 上下文环境。
     *                <p>
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs   XML属性，可能为空值。
     */
    public BusinessCard2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 初始化视图
        initView(context);

        // 获取自定义属性数组
        TypedArray taBusinessCard = context.obtainStyledAttributes(attrs, R.styleable.BusinessCard2);
        // 获取属性，并设置到子控件上。
        String name = taBusinessCard.getString(R.styleable.BusinessCard2_name);
        binding.tvName.setText(name);
        String phone = taBusinessCard.getString(R.styleable.BusinessCard2_phone);
        binding.tvPhone.setText(phone);
        taBusinessCard.recycle();
    }

    private void initView(Context context) {
        // 将布局文件渲染生成View实例
        binding = BusinessCardBinding.inflate(LayoutInflater.from(context), this, true);
    }

    /**
     * Name        : 更新文本与图像资源
     * <p>
     * Description : 无。
     *
     * @param name        姓名。
     * @param phone       电话号码。
     * @param avatarResID 头像资源ID。
     */
    public void updateInfo(String name, String phone, @DrawableRes int avatarResID) {
        binding.tvName.setText(name);
        binding.tvPhone.setText(phone);
        binding.ivAvatar.setImageResource(avatarResID);
    }
}
