package net.bi4vmr.study.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Name        : IOUtil
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-09 16:10
 * <p>
 * Description : IO工具类。
 */
public class IOUtil {

    // 读取文件内容，返回字符串。
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
            reader.close();
            isReader.close();
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
