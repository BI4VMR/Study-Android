package net.bi4vmr.study.uri;

import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiUriBinding;

/**
 * 测试界面：URI。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIURI extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIURI.class.getSimpleName();

    private TestuiUriBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiUriBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnURI.setOnClickListener(v -> testURI());
        binding.btnURIMatcher.setOnClickListener(v -> testURIMatcher());
    }

    // 解析URI
    private void testURI() {
        binding.tvLog.append("\n--- 解析URI ---\n");
        Log.i(TAG, "--- 解析URI ---");

        String input = "content://net.bi4vmr.study.provider/user?name=admin";
        binding.tvLog.append("输入URI:[" + input + "]\n");
        Log.i(TAG, "输入URI:[" + input + "]");

        // 转换URI文本为Uri对象
        Uri uri = Uri.parse("content://net.bi4vmr.study.provider/user?name=admin");
        // 获取协议
        binding.tvLog.append("Scheme:[" + uri.getScheme() + "]\n");
        Log.i(TAG, "Scheme:[" + uri.getScheme() + "]");
        // 获取授权记录
        binding.tvLog.append("Authority:[" + uri.getAuthority() + "]\n");
        Log.i(TAG, "Authority:[" + uri.getAuthority() + "]");
        // 获取请求路径
        binding.tvLog.append("Path:[" + uri.getPath() + "]\n");
        Log.i(TAG, "Path:[" + uri.getPath() + "]");
        // 获取查询参数
        binding.tvLog.append("Query:[" + uri.getQuery() + "]\n");
        Log.i(TAG, "Query:[" + uri.getQuery() + "]");
    }

    // 匹配URI
    private void testURIMatcher() {
        binding.tvLog.append("\n--- 匹配URI ---\n");
        Log.i(TAG, "--- 匹配URI ---");

        // 创建UriMatcher对象
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 添加匹配条目
        matcher.addURI("net.bi4vmr.study.provider", "student", 101);
        matcher.addURI("net.bi4vmr.study.provider", "course", 102);

        // 匹配测试
        Uri uri = Uri.parse("content://net.bi4vmr.study.provider/student?name=admin");
        int code = matcher.match(uri);
        switch (code) {
            case 101:
                binding.tvLog.append("匹配到student请求。\n");
                Log.i(TAG, "匹配到student请求。");
                break;
            case 102:
                binding.tvLog.append("匹配到course请求。\n");
                Log.i(TAG, "匹配到course请求。");
                break;
            default:
                binding.tvLog.append("未知路径。 code:[" + code + "]\n");
                Log.w(TAG, "未知路径。 code:[" + code + "]");
                break;
        }
    }
}
