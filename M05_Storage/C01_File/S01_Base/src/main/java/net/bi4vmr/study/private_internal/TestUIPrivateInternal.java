package net.bi4vmr.study.private_internal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPrivateInternalBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class TestUIPrivateInternal extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPrivateInternal.class.getSimpleName();

    private TestuiPrivateInternalBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPrivateInternalBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnGetPath.setOnClickListener(v -> testGetPath());
        binding.btnWrite.setOnClickListener(v -> testWrite());
        binding.btnRead.setOnClickListener(v -> testRead());
    }

    // 获取私有数据目录路径
    private void testGetPath() {
        binding.tvLog.append("\n--- 获取私有数据目录路径 ---\n");
        Log.i(TAG, "--- 获取私有数据目录路径 ---");

        // 获取缓存目录
        File internalCacheDir = getCacheDir();
        String cachePath = internalCacheDir.getAbsolutePath();
        binding.tvLog.append("\n缓存目录: " + cachePath);
        Log.i(TAG, "缓存目录: " + cachePath);

        // 获取数据目录
        File internalDataDir = getFilesDir();
        String dataPath = internalDataDir.getAbsolutePath();
        binding.tvLog.append("\n数据目录: " + dataPath);
        Log.i(TAG, "数据目录: " + dataPath);
    }

    // 写入文件
    private void testWrite() {
        binding.tvLog.append("\n--- 写入文件---\n");
        Log.i(TAG, "--- 写入文件 ---");

        FileOutputStream stream = null;
        try {
            /*
             * 打开指定文件的输出流。
             *
             * @param name 目标文件名。
             *             打开"/data/data/<包名>/files/"目录中的对应文件，若文件不存在则自动创建。
             * @param mode 写入模式。
             *             "MODE_PRIVATE"表示清空写入；"MODE_APPEND"表示追加写入。
             * @return FileOutputStream实例。
             */
            stream = getApplicationContext().openFileOutput("test.txt", MODE_PRIVATE);
            stream.write("我能吞下玻璃而不伤身体。".getBytes(StandardCharsets.UTF_8));
            binding.tvLog.append("\n写入test.txt成功。");
            Log.i(TAG, "写入test.txt成功。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(stream);
        }
    }

    // 读取文件
    private void testRead() {
        binding.tvLog.append("\n--- 读取文件---\n");
        Log.i(TAG, "--- 读取文件 ---");

        FileInputStream stream = null;
        try {
            /*
             * 打开指定文件的输入流。
             *
             * @param name 文件名。
             *             打开"/data/data/<包名>/files/"目录中的对应文件。
             * @return FileInputStream实例。
             * @exception FileNotFoundException: 目标文件不存在。
             */
            stream = getApplicationContext().openFileInput("test.txt");
            String content = IOUtil.readFile(stream);
            binding.tvLog.append("\n读取test.txt的内容：\n" + content);
            Log.i(TAG, "读取test.txt的内容： " + content);
        } catch (Exception e) {
            binding.tvLog.append("\n文件不存在或出现其他异常！");
            Log.e(TAG, "文件不存在或出现其他异常！");
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(stream);
        }
    }
}
