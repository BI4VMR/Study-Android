package net.bi4vmr.study.apkraw;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.CloseUtils;

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
        binding = TestuiApkrawBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnReadStream.setOnClickListener(v -> testReadStream());
        binding.btnReadURI.setOnClickListener(v -> testReadURI());
    }

    // 读取文件（字节流）
    private void testReadStream() {
        binding.tvLog.append("\n--- 读取文件（字节流） ---\n");
        Log.i(TAG, "--- 读取文件（字节流） ---");

        // 读取"raw/test.txt"
        InputStream is = null;
        try {
            // 传入资源ID，获取输入流。
            is = getResources().openRawResource(R.raw.test);
            // 从输入流读取文本
            String content = IOUtil.readFile(is);
            binding.tvLog.append("test.txt文件的内容为：\n" + content);
            Log.i(TAG, "test.txt文件的内容为：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIOQuietly(is);
        }
    }

    // 读取文件（URI）
    private void testReadURI() {
        binding.tvLog.append("\n--- 读取文件（URI） ---\n");
        Log.i(TAG, "--- 读取文件（URI） ---");

        // 拼接"raw"目录中图片文件的URI
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getPackageName() + "/raw/pic";
        binding.tvLog.append("pic.png文件的URI为：\n" + uri);
        Log.i(TAG, "pic.png文件的URI为：" + uri);

        // 将图片加载至ImageView
        binding.ivTest.setImageURI(Uri.parse(uri));
        binding.tvLog.append("\n图片已加载至ImageView");
        Log.i(TAG, "图片已加载至ImageView");
    }
}
