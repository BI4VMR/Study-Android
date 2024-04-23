package net.bi4vmr.study.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import net.bi4vmr.study.databinding.BusinessCardBinding

/**
 * Name        : BusinessCard
 *
 * Author      : BI4VMR
 *
 * Email       : bi4vmr@outlook.com
 *
 * Date        : 2024-04-22 17:22
 *
 * Description : 自定义控件示例 - 名片。
 */
class BusinessCardKT : FrameLayout {

    private val binding: BusinessCardBinding by lazy {
        // 将布局文件渲染生成View实例
        BusinessCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * Name        : 构造方法1
     * <p>
     * Description : 该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param context 上下文环境。
     *
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     */
    constructor(context: Context) : super(context)

    /**
     * Name        : 构造方法2
     * <p>
     * Description : 如果我们需要在布局文件中引用控件，该方法是必选的，否则系统解析XML时将会报错。
     *
     * @param context 上下文环境。
     *
     *                后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs   XML属性，可能为空值。
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * Name        : 构造方法3
     * <p>
     * Description : 可选
     *
     * @param context      上下文环境。
     *
     *                     后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs        XML属性，可能为空值。
     * @param defStyleAttr <>。
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * Name        : 构造方法4
     * <p>
     * Description : 可选
     *
     * @param context      上下文环境。
     *
     *                     后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param attrs        XML属性，可能为空值。
     * @param defStyleAttr <>。
     * @param defStyleRes  <>。
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    /**
     * Name        : 更新文本与图像资源
     *
     * Description : 无。
     *
     * @param name        姓名。
     * @param phone       电话号码。
     * @param avatarResID 头像资源ID。
     */
    fun updateInfo(name: String, phone: String, @DrawableRes avatarResID: Int) {
        with(binding) {
            tvName.text = name
            tvPhone.text = phone
            ivAvatar.setImageResource(avatarResID)
        }
    }
}
