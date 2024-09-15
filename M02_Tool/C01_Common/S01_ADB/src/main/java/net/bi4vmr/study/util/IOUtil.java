package net.bi4vmr.study.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * I/O相关工具。
 */
public class IOUtil {

    // 读取文件内容，返回字符串，并关闭流。
    public static String readFile(InputStream is) {
        InputStreamReader isReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();

        try {
            // 循环读取数据
            do {
                String line = reader.readLine();
                // 如果读不到内容，则退出循环。
                if (line == null) {
                    break;
                } else {
                    sb.append(line);
                }
            } while (true);

            // 释放资源
            closeQuietly(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 删除换行符
        String content = sb.toString().replace("\n", "");
        content = content.replace("\r", "");

        return content;
    }

    // 关闭流
    public static void closeQuietly(Closeable is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
