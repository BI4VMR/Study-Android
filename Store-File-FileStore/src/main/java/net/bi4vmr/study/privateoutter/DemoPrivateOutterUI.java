package net.bi4vmr.study.privateoutter;

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

public class DemoPrivateOutterUI extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_demo_privateoutter);

        /*
         * 外部存储-应用私有数据目录，无需申请读写权限，其它程序不能访问，用户可以通过文件管理器访问。
         */
        // 获取缓存目录（外部存储）
        File externalCacheDir = getExternalCacheDir();
        Log.i("myapp", "外部存储-缓存: " + externalCacheDir.toString());
        /*
         * 获取文件目录（外部存储）
         *
         * 参数：文件类型。
         *      传入空值或空字符串时表示"files"目录本身。
         *      传入"Environment.DIRECTORY_MUSIC"等常量值，将返回类似"<包名>/files/Music"的对应目录。
         *      传入自定义的字符串，系统将返回同名目录，目录不存在时将自动创建。
         * 返回值：默认返回虚拟存储卡中的路径，如果未找到任何可用路径，则会返回"Null"。
         */
        File externalFileDir = getExternalFilesDir(null);
        Log.i("myapp", "外部存储-文件: " + externalFileDir.toString());
        Log.i("myapp", "内部存储-文件: 读-" + externalFileDir.canRead() + " 写-" + externalFileDir.canWrite());

        /*
         * 获取所有外部缓存、文件目录（外部存储）
         *
         * 参数与"getExternalCacheDir()"、"getExternalFilesDir()"类似，返回值为数组，包括所有外部存储设备的路径。
         */
        File[] externalCacheDirs = getExternalCacheDirs();
        File[] externalFilesDirs = getExternalFilesDirs(null);
        for (File path : externalCacheDirs) {
            Log.i("myapp", "外部存储-缓存目录: " + path);
        }
        for (File path : externalFilesDirs) {
            Log.i("myapp", "外部存储-文件目录: " + path);
        }

        // 文件读写
        try {
            // 构造File对象
            File file = new File(externalFileDir, "ex.txt");
            // 写入文件
            FileOutputStream os = new FileOutputStream(file);
            os.write("Hello".getBytes(StandardCharsets.UTF_8));
            Log.i("myapp", "写入ex.txt成功：Hello");
            os.close();

            // 读取文件
            FileInputStream is = new FileInputStream(file);
            String content = IOUtil.getInstance().readFile(is);
            Log.i("myapp", "读取ex.txt内容：" + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
