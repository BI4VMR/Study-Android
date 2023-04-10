package net.bi4vmr.study.privateinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;
import net.bi4vmr.study.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class DemoPrivateInnerUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_privateinner);

        /*
         * 内部存储-应用私有数据目录，无需申请读写权限，其它程序不能访问，通常用户也不能访问。
         */
        // 获取缓存目录（内部存储）
        File internalCacheDir = getCacheDir();
        Log.i("myapp", "内部存储-缓存: " + internalCacheDir.toString());
        // 获取文件目录（内部存储）
        File internalFileDir = getFilesDir();
        Log.i("myapp", "内部存储-文件: " + internalFileDir.toString());
        Log.i("myapp", "内部存储-文件: 读-" + internalFileDir.canRead() + " 写-" + internalFileDir.canWrite());

        // 文件读写
        try {
            /*
             * openFileOutput()方法用于打开文件输出流
             *
             * 参数一：文件名，打开"/data/data/<包名>/files/"目录中的对应文件，若文件不存在将自动创建。
             * 参数二：写入模式，"MODE_PRIVATE"表示清空写入；"MODE_PRIVATE"表示追加写入。
             * 返回值：FileOutputStream对象
             */
            FileOutputStream os = getApplicationContext().openFileOutput("test.txt", MODE_PRIVATE);
            os.write("Hello".getBytes(StandardCharsets.UTF_8));
            Log.i("myapp", "写入test.txt成功：Hello");
            os.close();

            /*
             * openFileInput()方法用于打开文件输入流
             * 参数一：文件名，打开"/data/data/<包名>/files/"目录中的对应文件，若文件不存在返回值为Null。
             * 返回值：FileInputStream对象
             */
            FileInputStream is = getApplicationContext().openFileInput("test.txt");
            String content = IOUtil.getInstance().readFile(is);
            Log.i("myapp", "读取test.txt内容：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
