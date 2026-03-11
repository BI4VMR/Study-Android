package net.bi4vmr.study.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import net.bi4vmr.study.databinding.BusinessCardBinding

/**
 * 自定义控件：名片（优化版本）。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class BusinessCardSimpleKT @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        private val TAG: String = "TestApp-${BusinessCardSimpleKT::class.java.simpleName}"
    }

    private val binding: BusinessCardBinding by lazy {
        // 将布局文件渲染生成View实例
        BusinessCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        // 在此处放置构造阶段的初始化逻辑...
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
