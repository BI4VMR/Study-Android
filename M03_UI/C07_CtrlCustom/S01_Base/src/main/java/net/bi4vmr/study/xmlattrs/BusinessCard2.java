package net.bi4vmr.study.xmlattrs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
 * Description : 自定义控件示例 - 名片（XML属性）。
 */
public class BusinessCard2 extends FrameLayout {

    private static final String TAG = "TestApp-" + BusinessCard2.class.getSimpleName();

    private BusinessCardBinding binding;

    // 构造方法1
    public BusinessCard2(@NonNull Context context) {
        // 调用构造方法2，XML属性集合传入空值，避免重复书写“初始化视图”的逻辑。
        this(context, null);
    }

    // 构造方法2
    public BusinessCard2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 初始化视图
        initView(context);

        // 如果当前实例不是通过布局文件生成的，则不必解析XML属性。
        if (attrs == null) {
            return;
        }

        // 获取自定义属性数组
        TypedArray taBusinessCard = context.obtainStyledAttributes(attrs, R.styleable.BusinessCard2);
        // 获取属性，并设置到子控件上。
        if (taBusinessCard.hasValue(R.styleable.BusinessCard2_name)) {
            String name = taBusinessCard.getString(R.styleable.BusinessCard2_name);
            binding.tvName.setText(name);
        }
        if (taBusinessCard.hasValue(R.styleable.BusinessCard2_phone)) {
            String phone = taBusinessCard.getString(R.styleable.BusinessCard2_phone);
            binding.tvPhone.setText(phone);
        }
        if (taBusinessCard.hasValue(R.styleable.BusinessCard2_avatar)) {
            Drawable avatar = taBusinessCard.getDrawable(R.styleable.BusinessCard2_avatar);
            binding.ivAvatar.setImageDrawable(avatar);
        }

        // 释放TypedArray资源
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
