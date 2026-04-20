package net.bi4vmr.study;

/**
 * SQL语句相关工具。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class SQLUtil {

    public static String bindArgs(String sql, Object... bindArgs) {
        String remainingSql = sql;
        int paramIndex = 0;
        while (remainingSql.contains("?")) {
            if (paramIndex >= bindArgs.length) {
                System.out.println("param more than ?");
                break;
            }

            Object param = bindArgs[paramIndex];
            String paramValue;
            if (param instanceof String) {
                paramValue = "'" + ((String) param).replace("'", "''") + "'";
            } else {
                paramValue = param.toString();
            }

            remainingSql = remainingSql.replaceFirst("\\?", paramValue);
            paramIndex++;
        }

        if (paramIndex != bindArgs.length) {
            System.out.println("param more than ?");
        }

        System.out.println("SQL:[" + remainingSql + "]");
        return remainingSql;
    }
}
