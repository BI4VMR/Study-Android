package net.bi4vmr.study.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiBaseBinding
import net.bi4vmr.tool.android.ui.fluidboderlayout.BorderSchema

/**
 * 测试界面：基本应用。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBaseKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBaseKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(LayoutInflater.from(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding) {
            btnAnimPlay.setOnClickListener {
                fluidBorderLayout.resumeAnim()
            }
            btnAnimPause.setOnClickListener {
                fluidBorderLayout.pauseAnim()
            }
            btnAnimSpeedUp.setOnClickListener {
                fluidBorderLayout.mDuration -= 1000L
            }
            btnAnimSpeedDown.setOnClickListener {
                fluidBorderLayout.mDuration += 1000L
            }
            tbtnBorderVisible.setOnCheckedChangeListener { _, isChecked ->
                fluidBorderLayout.mBorderVisible = isChecked
            }
            tbtnChildInsideBorder.setOnCheckedChangeListener { _, isChecked ->
                fluidBorderLayout.mChildInsideBorder = isChecked
            }

            rgType.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    rbtnType1.id -> fluidBorderLayout.usePresetSchema(BorderSchema.parseByOrder(0))
                    rbtnType2.id -> fluidBorderLayout.usePresetSchema(BorderSchema.parseByOrder(1))
                    rbtnType3.id -> fluidBorderLayout.usePresetSchema(BorderSchema.parseByOrder(2))
                    rbtnType4.id -> fluidBorderLayout.usePresetSchema(BorderSchema.parseByOrder(3))
                    rbtnCustom.id -> fluidBorderLayout.usePresetSchema(BorderSchema.parseByOrder(4))
                }
            }
        }
    }
}
