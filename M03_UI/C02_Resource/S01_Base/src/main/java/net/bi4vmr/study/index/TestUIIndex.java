package net.bi4vmr.study.index;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiResourceIndexBinding;

/**
 * 测试界面：资源索引。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestUIIndex extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIIndex.class.getSimpleName();

    private TestuiResourceIndexBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiResourceIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnGetResource.setOnClickListener(v -> testGetResource());
        binding.btnGetResourceInfo.setOnClickListener(v -> testGetResourceInfo());
    }

    private void testGetResource() {
        Log.i(TAG, "----- 获取资源 -----");
        appendLog("\n----- 获取资源 -----");

        // 在代码中获取颜色资源
        int colorValue = getResources().getColor(R.color.common_text, getTheme());

        /*
         * Context也提供了快捷方式，等同于 `Context.getResources().getColor()` ，将会自动使用当前Theme。
         *
         * 如果想要获取Android内置的资源，需要添加前缀 `android.` 。
         */
        int colorValue2 = getColor(android.R.color.holo_blue_light);

        // 使用色值
        binding.tvLog.setTextColor(colorValue2);
    }

    private void testGetResourceInfo() {
        Log.i(TAG, "----- 获取资源信息 -----");
        appendLog("\n----- 获取资源信息 -----");

        // 获取完整名称
        String fullName = getResources().getResourceName(R.string.app_name);
        // 获取资源名称
        String name = getResources().getResourceEntryName(R.string.app_name);
        // 获取资源类型
        String type = getResources().getResourceTypeName(R.string.app_name);
        // 获取资源所在包名
        String pkgName = getResources().getResourcePackageName(R.string.app_name);

        Log.i(TAG, "完整名称：" + fullName);
        Log.i(TAG, "资源名称：" + name);
        Log.i(TAG, "资源类型：" + type);
        Log.i(TAG, "所在包名：" + pkgName);

        appendLog("完整名称：" + fullName);
        appendLog("资源名称：" + name);
        appendLog("资源类型：" + type);
        appendLog("所在包名：" + pkgName);
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(Object text) {
        binding.tvLog.post(() -> binding.tvLog.append("\n" + text.toString()));
        binding.tvLog.post(() -> {
            try {
                int offset = binding.tvLog.getLayout().getLineTop(binding.tvLog.getLineCount()) - binding.tvLog.getHeight();
                if (offset > 0) {
                    binding.tvLog.scrollTo(0, offset);
                }
            } catch (Exception e) {
                Log.w(TAG, "TextView scroll failed!", e);
            }
        });
    }
}
