package net.bi4vmr.study.publicdir;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class DemoPublicUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_public);

        /*
         * 共享数据目录，Android 11及以下版本需要申请读写权限。
         */
        // 获取共享目录的根路径
        File shareDir = Environment.getExternalStorageDirectory();
        Log.i("myapp", "共享存储-根目录: " + shareDir.toString());
        Log.i("myapp", "共享存储-根目录: 读-" + shareDir.canRead() + " 写-" + shareDir.canWrite());

        // 获取指定类型的目录，参数类型同"getExternalCacheDir()"、"getExternalFilesDir()"方法。
        File shareDLDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        Log.i("myapp", "共享存储-Doc: " + shareDLDir.toString());
        Log.i("myapp", "共享存储-Doc: 读-" + shareDLDir.canRead() + " 写-" + shareDLDir.canWrite());

        // 文件读写
        try {
            // 构造File对象
            File file = new File(shareDLDir, "share.txt");
            // 写入文件
            FileOutputStream os = new FileOutputStream(file);
            os.write("Shared".getBytes(StandardCharsets.UTF_8));
            Log.i("myapp", "写入share.txt成功：Shared");
            os.close();

            // 读取文件
            FileInputStream is = new FileInputStream(file);
            String content = IOUtil.getInstance().readFile(is);
            Log.i("myapp", "读取share.txt内容：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
