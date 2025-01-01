package net.bi4vmr.study.buildconfig

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.BuildConfig
import net.bi4vmr.study.databinding.TestuiBaseBinding

/**
 * 测试界面：读取BuildConfig中的变量。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIBuildConfigKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIBuildConfigKT::class.java.simpleName}"
    }

    private val binding: TestuiBaseBinding by lazy {
        TestuiBaseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnAccess.setOnClickListener { testAccess() }
        }
    }

    // 访问变量
    private fun testAccess() {
        Log.i(TAG, "--- 访问变量 ---")
        binding.tvLog.append("\n--- 访问变量 ---\n")

        // 访问内置变量
        val debug: Boolean = BuildConfig.DEBUG
        val buildType: String = BuildConfig.BUILD_TYPE

        Log.d(TAG, "是否允许Debug:[$debug]\n构建类型:[$buildType]")
        binding.tvLog.append("\n是否允许Debug:[$debug]\n构建类型:[$buildType]")

        // 访问自定义变量
        Log.d(TAG, "自定义BuildConfig变量:[${BuildConfig.SERVER_NAME}]")
        binding.tvLog.append("\n自定义BuildConfig变量:[${BuildConfig.SERVER_NAME}]");
    }
}
