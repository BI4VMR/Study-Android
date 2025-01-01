package net.bi4vmr.study.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import net.bi4vmr.study.databinding.BusinessCardBinding

/**
 * 自定义控件：名片。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class BusinessCardKT : FrameLayout {

    companion object {
        private val TAG: String = "TestApp-${BusinessCardKT::class.java.simpleName}"
    }

    private val binding: BusinessCardBinding by lazy {
        // 将布局文件渲染生成View实例
        BusinessCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * 构造方法1。
     *
     * 该方法是必选的，当我们在代码中动态创建BusinessCard实例时，该方法将被调用。
     *
     * @param[context] 上下文环境。
     *
     *                 后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     */
    constructor(context: Context) : super(context)

    /**
     * 构造方法2。
     *
     * 如果我们需要在布局文件中引用控件，该方法是必选的，否则系统解析XML时将会报错。
     *
     * @param[context] 上下文环境。
     *
     *                 后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param[attrs]   XML属性，可能为空值。
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 构造方法3。
     *
     * 可选，与主题有关。
     *
     * @param[context]      上下文环境。
     *
     *                      后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param[attrs]        XML属性，可能为空值。
     * @param[defStyleAttr] 主题属性。
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 构造方法4。
     *
     * 可选，与主题有关。
     *
     * @param[context]      上下文环境。
     *
     *                      后续控件的资源需要基于该实例获取，由于存在深色主题等，因此使用者需要注意区别传入的实例类型。
     * @param[attrs]        XML属性，可能为空值。
     * @param[defStyleAttr] 主题属性。
     * @param[defStyleRes]  样式属性。
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    /**
     * 更新文本与图像资源。
     *
     * @param[name]        姓名。
     * @param[phone]       电话号码。
     * @param[avatarResID] 头像资源ID。
     */
    fun updateInfo(name: String, phone: String, @DrawableRes avatarResID: Int) {
        with(binding) {
            tvName.text = name
            tvPhone.text = phone
            ivAvatar.setImageResource(avatarResID)
        }
    }
}
