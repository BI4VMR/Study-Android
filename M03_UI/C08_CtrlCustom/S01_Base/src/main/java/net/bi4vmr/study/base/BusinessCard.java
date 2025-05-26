package net.bi4vmr.study.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.bi4vmr.study.R;

/**
 * 自定义控件：名片。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class BusinessCard extends FrameLayout {

    private static final String TAG = "TestApp-" + BusinessCard.class.getSimpleName();

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvPhone;

    /**
     * 构造方法1。
     * <p>
     * 该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param context 上下文环境。
     *                <p>
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     */
    public BusinessCard(@NonNull Context context) {
        super(context);
        /* 初始化操作 ... */
        // 将布局文件渲染生成View实例
        View view = LayoutInflater.from(context).inflate(R.layout.business_card, this, true);
        // 获取View实例中各个子元素的引用
        ivAvatar = view.findViewById(R.id.ivAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
    }

    /**
     * 构造方法2。
     * <p>
     * 如果我们需要在布局文件中引用控件，该方法是必选的，否则系统解析XML时将会报错。
     *
     * @param context 上下文环境。
     *                <p>
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs   XML属性，可能为空值。
     */
    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /* 初始化操作 ... */
    }

    /**
     * 构造方法3。
     * <p>
     * 可选，支持使用主题提供的资源。
     *
     * @param context      上下文环境。
     *                     <p>
     *                     后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs        XML属性，可能为空值。
     * @param defStyleAttr 主题属性。
     */
    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /* 初始化操作 ... */
    }

    /**
     * 构造方法4。
     * <p>
     * 可选，支持使用样式提供的资源。
     *
     * @param context      上下文环境。
     *                     <p>
     *                     后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs        XML属性，可能为空值。
     * @param defStyleAttr 主题属性。
     * @param defStyleRes  样式属性。
     */
    public BusinessCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        /* 初始化操作 ... */
    }

    /**
     * 更新文本与图像资源。
     *
     * @param name        姓名。
     * @param phone       电话号码。
     * @param avatarResID 头像资源ID。
     */
    public void updateInfo(String name, String phone, @DrawableRes int avatarResID) {
        tvName.setText(name);
        tvPhone.setText(phone);
        ivAvatar.setImageResource(avatarResID);
    }
}
