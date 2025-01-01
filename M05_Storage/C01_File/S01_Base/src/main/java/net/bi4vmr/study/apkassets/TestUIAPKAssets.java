package net.bi4vmr.study.apkassets;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiApkassetsBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;
import java.io.InputStream;

public class TestUIAPKAssets extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIAPKAssets.class.getSimpleName();

    private TestuiApkassetsBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiApkassetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnReadStream.setOnClickListener(v -> testReadStream());
    }

    // 读取文件（字节流）
    private void testReadStream() {
        binding.tvLog.append("\n--- 读取文件（字节流） ---\n");
        Log.i(TAG, "--- 读取文件（字节流） ---");

        // 获取AssetManager
        AssetManager am = getApplicationContext().getAssets();
        try {
            // 获取"config"目录下的子项
            String[] filenames = am.list("config");
            if (filenames == null) {
                binding.tvLog.append("\n遍历目录config失败！");
                Log.i(TAG, "遍历目录config失败！");
                return;
            }

            // 依次读取每个文件的内容
            for (String file : filenames) {
                InputStream is = am.open("config" + File.separator + file);
                String content = IOUtil.readFile(is);
                binding.tvLog.append("\n文件名称：" + file);
                Log.i(TAG, "文件名称：" + file);
                binding.tvLog.append("\n文件内容：" + content);
                Log.i(TAG, "文件内容：" + content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭AssetManager后，整个进程都无法再使用它，因此该方法不能由开发者调用。
        // am.close();
    }
}
