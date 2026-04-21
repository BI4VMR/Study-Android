package net.bi4vmr.study;

/**
 * SQL语句相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class SQLUtil {

    /**
     * 绑定SQL语句与参数列表。
     * <p>
     * 处理带有占位符("?")的SQL语句，将参数列表中的元素依次放置在占位符所在位置，返回最终的SQL语句。
     *
     * @param sql  原始SQL语句。
     * @param args 参数列表。
     * @return 合并后的SQL语句。
     */
    public static String bindArgs(String sql, Object... args) {
        // 暂存每轮处理后的语句
        String currentSQL = sql;
        // 占位符索引
        int argIndex = 0;

        // 如果当前语句仍有占位符，则继续执行替换，否则返回结果。
        while (currentSQL.contains("?")) {

            Object param = args[argIndex];
            String paramValue;
            if (param instanceof String) {
                paramValue = "'" + ((String) param).replace("'", "''") + "'";
            } else {
                paramValue = param.toString();
            }

            currentSQL = currentSQL.replaceFirst("\\?", paramValue);
            argIndex++;
        }

        // 如果参数个数大于占位符，发出警告并忽略后续元素，但不阻断处理流程。
        if (argIndex != args.length) {
            System.out.println("Param more than placeholder, please check it.");
        }

        return currentSQL;
    }
}
