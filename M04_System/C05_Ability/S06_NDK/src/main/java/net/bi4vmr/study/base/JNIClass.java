package net.bi4vmr.study.base;

/**
 * Name        : JNIClass
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2024-02-17 22:03
 * <p>
 * Description : JNI测试类。
 */
public class JNIClass {

    static {
        // 加载"libjnitest.so"
        System.loadLibrary("jnitest");
    }

    // 本地方法：传递基本数据类型参数
    native void passBasicTypes(boolean b, int i, double d);

    // 本地方法：传递字符串参数
    native void passString(String str);

    // 本地方法：传递字符串数组参数
    native void passStringArray(String[] array);
}
