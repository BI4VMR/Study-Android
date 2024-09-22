package net.bi4vmr.study.base;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.CloseUtils;

import net.bi4vmr.study.databinding.TestuiBaseBinding;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class TestUIBase extends AppCompatActivity {

    private static final String TAG = "TestApp-" + TestUIBase.class.getSimpleName();

    private TestuiBaseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = TestuiBaseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.btnGetStreamTypes.setOnClickListener(v -> testGetStreamTypes());
        binding.btnGetFileType.setOnClickListener(v -> testGetFileType());
        binding.btnReadFile.setOnClickListener(v -> testReadFile());
        binding.btnWriteFile.setOnClickListener(v -> testWriteFile());
    }

    // 查询支持的图片文件类型
    private void testGetStreamTypes() {
        binding.tvLog.append("\n--- 查询支持的图片文件类型 ---\n");
        Log.i(TAG, "--- 查询支持的图片文件类型 ---");

        Uri uri = Uri.parse("content://net.bi4vmr.provider.sharefile/query_image_types");
        ContentResolver contentResolver = getContentResolver();
        String[] mimes = contentResolver.getStreamTypes(uri, "image/*");
        binding.tvLog.append("服务端支持的所有图片类型：" + Arrays.toString(mimes) + "\n");
        Log.i(TAG, "服务端支持的所有图片类型：" + Arrays.toString(mimes));
    }

    // 查询文件A和B的类型
    private void testGetFileType() {
        binding.tvLog.append("\n--- 查询文件A和B的类型 ---\n");
        Log.i(TAG, "--- 查询文件A和B的类型 ---");

        Uri uri = Uri.parse("content://net.bi4vmr.provider.sharefile/textfile");
        ContentResolver contentResolver = getContentResolver();
        String mime = contentResolver.getType(uri);
        binding.tvLog.append("'text'文件的类型：" + mime + "\n");
        Log.i(TAG, "'text'文件的类型：" + mime);

        Uri uri2 = Uri.parse("content://net.bi4vmr.provider.sharefile/configfile");
        String mime2 = contentResolver.getType(uri2);
        binding.tvLog.append("'config'文件的类型：" + mime2 + "\n");
        Log.i(TAG, "'config'文件的类型：" + mime2);
    }

    // 读取文本文件A
    private void testReadFile() {
        binding.tvLog.append("\n--- 读取文本文件A ---\n");
        Log.i(TAG, "--- 读取文本文件A ---");

        // 文本文件的URI
        Uri uri = Uri.parse("content://net.bi4vmr.provider.sharefile/textfile");

        ContentResolver contentResolver = getContentResolver();
        InputStream is;
        InputStreamReader reader = null;
        StringBuilder stringBuffer = new StringBuilder();
        try {
            // 打开输入流
            is = contentResolver.openInputStream(uri);
            // 服务端可能返回空值，需要进行判断。
            if (is == null) {
                binding.tvLog.append("打开文件失败，请检查日志。\n");
                Log.w(TAG, "打开文件失败，请检查日志。");
                return;
            }

            reader = new InputStreamReader(is);
            char[] buffer = new char[8];
            // 循环读取文件内容
            while (true) {
                int count = reader.read(buffer);
                if (count == -1) {
                    break;
                }
                String data = new String(buffer, 0, count);
                stringBuffer.append(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIOQuietly(reader);
        }

        binding.tvLog.append(stringBuffer + "\n");
        Log.i(TAG, "文件内容:" + stringBuffer);
    }

    // 写入配置文件B
    private void testWriteFile() {
        binding.tvLog.append("\n--- 写入配置文件B ---\n");
        Log.i(TAG, "--- 写入配置文件B ---");

        // 待写入的数据
        final String jsonData = "{ \"number\" : 100 }";
        // 配置文件的URI
        final Uri uri = Uri.parse("content://net.bi4vmr.provider.sharefile/configfile");

        ContentResolver contentResolver = getContentResolver();
        OutputStream os;
        OutputStreamWriter writer = null;
        try {
            // 打开输出流
            os = contentResolver.openOutputStream(uri);
            // 服务端可能返回空值，需要进行判断。
            if (os == null) {
                binding.tvLog.append("打开文件失败，请检查日志。\n");
                Log.w(TAG, "打开文件失败，请检查日志。");
                return;
            }

            writer = new OutputStreamWriter(os);
            writer.write(jsonData);
            binding.tvLog.append("文件写入已完成，请adb pull检查。\n");
            Log.i(TAG, "文件写入已完成，请adb pull检查。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIOQuietly(writer);
        }
    }
}
