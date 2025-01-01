package net.bi4vmr.study.private_external;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.databinding.TestuiPrivateExternalBinding;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class TestUIPrivateExternal extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIPrivateExternal.class.getSimpleName();

    private TestuiPrivateExternalBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiPrivateExternalBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnGetPath.setOnClickListener(v -> testGetPath());
        binding.btnGetAllPath.setOnClickListener(v -> testGetAllPath());
        binding.btnWrite.setOnClickListener(v -> testWrite());
        binding.btnRead.setOnClickListener(v -> testRead());
    }

    // 获取虚拟存储卡上的私有数据目录路径
    private void testGetPath() {
        binding.tvLog.append("\n--- 获取虚拟存储卡上的私有数据目录路径 ---\n");
        Log.i(TAG, "--- 获取虚拟存储卡上的私有数据目录路径 ---");

        // 获取所有外置存储器上的私有缓存目录路径
        File externalCacheDir = getExternalCacheDir();
        if (externalCacheDir != null) {
            String cachePath = externalCacheDir.getAbsolutePath();
            binding.tvLog.append("\n缓存目录: " + cachePath);
            Log.i(TAG, "缓存目录: " + cachePath);
        }

        /*
         * 获取所有外置存储器上的私有数据目录路径。
         *
         * 此处的参数用于控制返回的目录类型，"null"表示返回顶层目录，其他值的含义见“访问共享数据”示例。
         */
        File externalFileDir = getExternalFilesDir(null);
        if (externalFileDir != null) {
            String dataPath = externalFileDir.getAbsolutePath();
            binding.tvLog.append("\n数据目录: " + dataPath);
            Log.i(TAG, "数据目录: " + dataPath);
        }
    }

    // 获取所有外置存储器上的私有数据目录路径
    private void testGetAllPath() {
        binding.tvLog.append("\n--- 获取所有外置存储器上的私有数据目录路径 ---\n");
        Log.i(TAG, "--- 获取所有外置存储器上的私有数据目录路径 ---");

        /*
         * 获取所有外部存储区域上的缓存目录。
         *
         * 包括虚拟存储卡与物理存储卡。
         */
        File[] externalCacheDirs = getExternalCacheDirs();
        for (File path : externalCacheDirs) {
            binding.tvLog.append("\n缓存目录: " + path);
            Log.i(TAG, "缓存目录: " + path);
        }

        /*
         * 获取所有外部存储区域上的数据目录。
         *
         * 包括虚拟存储卡与物理存储卡。
         * 此处的参数用于控制返回的目录类型，"null"表示返回顶层目录，其他值的含义见“访问共享数据”示例。
         */
        File[] externalFilesDirs = getExternalFilesDirs(null);
        for (File path : externalFilesDirs) {
            binding.tvLog.append("\n数据目录: " + path);
            Log.i(TAG, "数据目录: " + path);
        }
    }

    // 写入文件
    private void testWrite() {
        binding.tvLog.append("\n--- 写入文件---\n");
        Log.i(TAG, "--- 写入文件 ---");

        FileOutputStream stream = null;
        try {
            // 获取虚拟存储卡上的数据目录
            File externalFileDir = getApplicationContext().getExternalFilesDir(null);
            // 创建目标文件路径
            File dstFile = new File(externalFileDir, "test.txt");
            // 使用流的方式写入数据
            stream = new FileOutputStream(dstFile);
            stream.write("我能吞下玻璃而不伤身体。".getBytes(StandardCharsets.UTF_8));
            binding.tvLog.append("\n写入test.txt成功");
            Log.i(TAG, "写入test.txt成功");
        } catch (Exception e) {
            binding.tvLog.append("\n出现异常！");
            Log.e(TAG, "出现异常！");
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
            // 获取虚拟存储卡上的数据目录
            File externalFileDir = getApplicationContext().getExternalFilesDir(null);
            // 创建目标文件路径
            File dstFile = new File(externalFileDir, "test.txt");
            // 使用流的方式读取数据
            stream = new FileInputStream(dstFile);
            String content = IOUtil.readFile(stream);
            binding.tvLog.append("\n读取test.txt的内容：\n" + content);
            Log.i(TAG, "读取test.txt的内容： " + content);
        } catch (Exception e) {
            binding.tvLog.append("\n出现异常，请检查文件是否存在！");
            Log.e(TAG, "出现异常，请检查文件是否存在！");
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(stream);
        }
    }
}
