package net.bi4vmr.study.apkraw;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.databinding.TestuiApkrawBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.InputStream;

public class TestUIAPKRaw extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIAPKRaw.class.getSimpleName();

    private TestuiApkrawBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiApkrawBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLog.setMovementMethod(ScrollingMovementMethod.getInstance());

        binding.btnReadStream.setOnClickListener(v -> testReadStream());
        binding.btnReadURI.setOnClickListener(v -> testReadURI());
    }

    private void testReadStream() {
        Log.i(TAG, "--- 读取文件（字节流） ---");
        appendLog("\n--- 读取文件（字节流） ---\n");

        // 读取"raw/test.txt"。
        Resources resources = getApplicationContext().getResources();
        try {
            // 传入资源ID，获取输入流。
            InputStream stream = resources.openRawResource(R.raw.test);
            // 从输入流读取文本。
            String content = IOUtil.readFile(stream);
            binding.tvLog.append("test.txt文件的内容为：\n" + content);
            Log.i(TAG, "test.txt文件的内容为：" + content);
        } catch (Exception e) {
            Log.e(TAG, "读取文件失败！", e);
        }
    }

    // 读取文件（URI）
    private void testReadURI() {
        Log.i(TAG, "--- 读取文件（URI） ---");
        appendLog("\n--- 读取文件（URI） ---\n");

        // 拼接"raw"目录中图片文件的URI
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/pic";
        binding.tvLog.append("pic.png文件的URI为：\n" + uri);
        Log.i(TAG, "pic.png文件的URI为：" + uri);

        // 将图片加载至ImageView
        binding.ivTest.setImageURI(Uri.parse(uri));
        binding.tvLog.append("\n图片已加载至ImageView。");
        Log.i(TAG, "图片已加载至ImageView。");
    }

    // 向文本框中追加日志内容并滚动到最底端
    private void appendLog(CharSequence text) {
        binding.tvLog.append(text);
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
