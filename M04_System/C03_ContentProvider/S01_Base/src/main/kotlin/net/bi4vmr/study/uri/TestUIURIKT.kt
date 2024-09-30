package net.bi4vmr.study.uri

import android.content.UriMatcher
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bi4vmr.study.databinding.TestuiUriBinding

/**
 * 测试界面：URI。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class TestUIURIKT : AppCompatActivity() {

    companion object {
        private val TAG: String = "TestApp-${TestUIURIKT::class.java.simpleName}"
    }

    private val binding: TestuiUriBinding by lazy {
        TestuiUriBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            btnURI.setOnClickListener { testURI() }
            btnURIMatcher.setOnClickListener { testURIMatcher() }
        }
    }

    // 解析URI
    private fun testURI() {
        Log.i(TAG, "--- 解析URI ---")
        binding.tvLog.append("\n--- 解析URI ---\n")

        val input = "content://net.bi4vmr.study.provider/user?name=admin"
        binding.tvLog.append("输入URI:[$input]\n")
        Log.i(TAG, "输入URI:[$input]")

        // 转换URI文本为Uri对象
        val uri = Uri.parse(input)
        // 获取协议
        binding.tvLog.append("Scheme:[${uri.scheme}]\n")
        Log.i(TAG, "Scheme:[${uri.scheme}]")
        // 获取授权记录
        binding.tvLog.append("Authority:[${uri.authority}]\n")
        Log.i(TAG, "Authority:[${uri.authority}]")
        // 获取请求路径
        binding.tvLog.append("Path:[${uri.path}]\n")
        Log.i(TAG, "Path:[${uri.path}]")
        // 获取查询参数
        binding.tvLog.append("Query:[${uri.query}]\n")
        Log.i(TAG, "Query:[${uri.query}]")
    }

    // 匹配URI
    private fun testURIMatcher() {
        binding.tvLog.append("\n--- 匹配URI ---\n")
        Log.i(TAG, "--- 匹配URI ---")

        // 创建UriMatcher对象
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        // 添加匹配条目
        matcher.addURI("net.bi4vmr.study.provider", "student", 101)
        matcher.addURI("net.bi4vmr.study.provider", "course", 102)

        // 匹配测试
        val uri: Uri = Uri.parse("content://net.bi4vmr.study.provider/student?name=admin")
        val code: Int = matcher.match(uri)
        when (code) {
            101 -> {
                binding.tvLog.append("匹配到student请求。\n")
                Log.i(TAG, "匹配到student请求。")
            }

            102 -> {
                binding.tvLog.append("匹配到course请求。\n")
                Log.i(TAG, "匹配到course请求。")
            }

            else -> {
                binding.tvLog.append("未知路径。 code:[$code]\n")
                Log.w(TAG, "未知路径。 code:[$code]")
            }
        }
    }
}
