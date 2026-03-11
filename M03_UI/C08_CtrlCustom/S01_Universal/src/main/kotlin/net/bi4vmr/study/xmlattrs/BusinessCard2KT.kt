package net.bi4vmr.study.xmlattrs

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import net.bi4vmr.study.R
import net.bi4vmr.study.databinding.BusinessCardBinding

/**
 * 自定义控件：名片2。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
@SuppressLint("CustomViewStyleable")
class BusinessCard2KT @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private val TAG: String = "TestApp-${BusinessCard2KT::class.java.simpleName}"
    }

    private val binding: BusinessCardBinding by lazy {
        // 将布局文件渲染生成View实例
        BusinessCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        // 初始化视图
        // 此处留空，因为"binding"变量已经通过"lazy"函数延迟初始化。

        // 如果当前实例不是通过布局文件生成的，则不必解析XML属性。
        if (attrs != null) {
            // 获取自定义属性数组
            val ta: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.BusinessCard2, defStyleAttr, defStyleRes)
            ta.use {
                if (ta.hasValue(R.styleable.BusinessCard2_name)) {
                    val name: String? = ta.getString(R.styleable.BusinessCard2_name)
                    binding.tvName.text = name
                }
                if (ta.hasValue(R.styleable.BusinessCard2_phone)) {
                    val phone: String? = ta.getString(R.styleable.BusinessCard2_phone)
                    binding.tvPhone.text = phone
                }
                if (ta.hasValue(R.styleable.BusinessCard2_avatar)) {
                    val avatar: Drawable? = ta.getDrawable(R.styleable.BusinessCard2_avatar)
                    binding.ivAvatar.setImageDrawable(avatar)
                }
            }
        }
    }

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
