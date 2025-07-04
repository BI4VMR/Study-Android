package net.bi4vmr.study.paramsync;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本与引用数据类型参数的区别。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class TestDataTypes {

    public static void main(String[] args) {
        int num = 1;
        List<String> list = new ArrayList<>();
        list.add("a");
        System.out.println("初始情况 - Num：" + num);
        System.out.println("初始情况 - List：" + list);

        // 调用方法修改"num"和"list"的值
        add(num, list);

        // 方法执行后，"num"和"list"的值。
        System.out.println("方法外部 - Num：" + num);
        System.out.println("方法外部 - List：" + list);
    }

    static void add(int num, List<String> argList) {
        // 改变参数的值（向列表中添加一项）
        num += 1;
        // 改变参数的值（向列表中添加一项）
        argList.add("b");

        System.out.println("方法内部 - Num：" + num);
        System.out.println("方法内部 - List：" + argList);
    }
}
