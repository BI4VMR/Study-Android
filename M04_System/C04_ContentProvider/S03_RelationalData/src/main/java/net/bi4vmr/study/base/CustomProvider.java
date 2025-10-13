package net.bi4vmr.study.base;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Name        : CustomProvider
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-06-28 10:29
 * <p>
 * Description : 自定义ContentProvider。
 */
public class CustomProvider extends ContentProvider {

    private static final String TAG = "TestApp-" + CustomProvider.class.getSimpleName();

    private DBHelper dbHelper;
    private UriMatcher uriMatcher;

    /**
     * Name        : 生命周期："onCreate()"
     * <p>
     * Description : 回调方法，当系统首次创建ContentProvider实例时触发，通常用来执行初始化UriMatcher、数据源等操作。
     * <p>
     * 当ContentProvider在主进程运行时，不可执行耗时操作，避免阻塞UI线程导致ANR。
     *
     * @return 初始化是否完成。
     * <p>
     * 该值是无意义的，不影响任何功能，通常返回"true"即可。
     */
    @Override
    public boolean onCreate() {
        Log.i(TAG, "OnCreate.");
        // 初始化数据库
        dbHelper = new DBHelper(getContext(), "test.db", null, 1);
        // 初始化URI匹配器
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("net.bi4vmr.provider", "student", 101);
        uriMatcher.addURI("net.bi4vmr.provider", "course", 102);
        return true;
    }

    /**
     * Name        : 查询指定资源的类型
     * <p>
     * Description : 查询指定资源的类型。
     *
     * @param uri 请求URI。
     * @return MIME字符串
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.i(TAG, "GetType. URI:[" + uri + "]");
        return null;
    }

    /**
     * Name        : 查询数据
     * <p>
     * Description : 查询数据。
     *
     * @param uri 客户端发起查询请求的目标URI。
     * @return 结果游标实例。
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.i(TAG, "Query. URI:[" + uri + "]");
        Cursor cursor = null;
        // 将客户端的请求URI传入匹配器
        int code = uriMatcher.match(uri);
        switch (code) {
            case 101:
                Log.i(TAG, "匹配到student请求。");
                cursor = dbHelper.getReadableDatabase()
                        .query("student", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case 102:
                Log.i(TAG, "匹配到course请求。");
                cursor = dbHelper.getReadableDatabase()
                        .query("course", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                Log.i(TAG, "未知路径！ Code:[" + code + "]");
                break;
        }

        return cursor;
    }

    /**
     * Name        : 插入数据
     * <p>
     * Description : 插入数据。
     *
     * @param uri 客户端发起插入请求的目标URI。
     * @return 新数据对应的URI。
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.i(TAG, "Insert. URI:[" + uri + "]");
        // 将客户端的请求URI传入匹配器
        int code = uriMatcher.match(uri);
        switch (code) {
            case 101:
                dbHelper.getWritableDatabase()
                        .insert("student", null, values);
                break;
            case 102:
                dbHelper.getWritableDatabase()
                        .insert("course", null, values);
                break;
        }

        // 返回插入后记录的ID，本示例中不需要该功能，因此返回空值。
        return null;
    }

    /**
     * Name        : 删除数据
     * <p>
     * Description : 删除数据。
     *
     * @param uri 客户端发起删除请求的目标URI。
     * @return 受影响的行数。
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.i(TAG, "Delete. URI:[" + uri + "], Selection:[" + selection + "]");
        int rowCount = -1;

        // 将客户端的请求URI传入匹配器
        int code = uriMatcher.match(uri);
        switch (code) {
            case 101:
                rowCount = dbHelper.getWritableDatabase()
                        .delete("student", selection, selectionArgs);
                break;
            case 102:
                rowCount = dbHelper.getWritableDatabase()
                        .delete("course", selection, selectionArgs);
                break;
        }

        return rowCount;
    }

    /**
     * Name        : 更新数据
     * <p>
     * Description : 更新数据。
     *
     * @param uri    客户端发起更新请求的目标URI。
     * @param values 新的数值。
     * @return 受影响的行数。
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.i(TAG, "Update. URI:[" + uri + "]");
        int rowCount = -1;

        // 将客户端的请求URI传入匹配器
        int code = uriMatcher.match(uri);
        switch (code) {
            case 101:
                rowCount = dbHelper.getWritableDatabase()
                        .update("student", values, selection, selectionArgs);
                break;
            case 102:
                rowCount = dbHelper.getWritableDatabase()
                        .update("course", values, selection, selectionArgs);
                break;
        }

        return rowCount;
    }
}
