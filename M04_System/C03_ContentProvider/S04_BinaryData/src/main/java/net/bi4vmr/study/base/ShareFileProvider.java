package net.bi4vmr.study.base;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.CloseUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Name        : ShareFileProvider
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-06-28 10:29
 * <p>
 * Description : 自定义ContentProvider：共享二进制数据。
 */
public class ShareFileProvider extends ContentProvider {

    private static final String TAG = "TestApp-Server-" + ShareFileProvider.class.getSimpleName();
    private static final String AUTHORITY = "net.bi4vmr.provider.sharefile";

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
     * 该值是无意义的，不影响任何功能。
     */
    @Override
    public boolean onCreate() {
        Log.i(TAG, "Create.");
        /* 初始化URI匹配器 */
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 查询服务端支持的所有图片类型
        uriMatcher.addURI(AUTHORITY, "query_image_types", 100);
        // 文件读取测试（纯文本）
        uriMatcher.addURI(AUTHORITY, "textfile", 101);
        // 文件写入测试（JSON文本）
        uriMatcher.addURI(AUTHORITY, "configfile", 102);

        /* 创建测试文件 */
        File testFile = new File(getContext().getFilesDir(), "text.txt");
        if (!testFile.exists()) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(testFile));
                writer.write("GNU is not unix");
                writer.newLine();
                writer.write("WINE is not an emulator");
                writer.newLine();
                writer.write("我能吞下玻璃而不伤身体。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                CloseUtils.closeIOQuietly(writer);
            }
        }

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    /**
     * Name        : 查询服务端所支持的MIME类型
     * <p>
     * Description : 查询服务端所支持的MIME类型。
     *
     * @param uri            请求URI
     * @param mimeTypeFilter MIME类型过滤器
     * @return MIME字符串数组，若查询参数不满足要求，应当返回空值。
     */
    @Nullable
    @Override
    public String[] getStreamTypes(@NonNull Uri uri, @NonNull String mimeTypeFilter) {
        Log.i(TAG, "GetStreamTypes. URI:" + uri + ", Filter:" + mimeTypeFilter);
        // 定义当前应用程序的所有MIME类型
        final String[] allMIMEs = new String[]{
                "image/jpeg", "image/png", "image/gif", "image/webp",
                "audio/wav", "audio/mp3", "text/plain", "text/html"};

        // 判断MimeTypeFilter参数是否符合格式要求
        if (!mimeTypeFilter.endsWith("*")) {
            Log.w(TAG, "Filter is invalid!");
            return null;
        }

        // 移除通配符
        String mimePrefix = mimeTypeFilter.substring(0, mimeTypeFilter.length() - 1);
        // 根据客户端的筛选要求，返回对应的MIME类型。
        List<String> results = new ArrayList<>();
        for (String mime : allMIMEs) {
            if (mime.startsWith(mimePrefix)) {
                results.add(mime);
            }
        }

        return results.toArray(new String[0]);
    }

    /**
     * Name        : 查询指定资源的类型
     * <p>
     * Description : 查询指定资源的类型。
     *
     * @param uri 请求URI
     * @return MIME字符串
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.i(TAG, "GetType. URI:" + uri);
        String mime = null;

        int code = uriMatcher.match(uri);
        switch (code) {
            /* "textfile"请求，对应纯文本文件。 */
            case 101:
                mime = "text/plain";
                break;
            /* "configfile"请求，对应JSON配置文件。 */
            case 102:
                mime = "application/json";
                break;
        }

        return mime;
    }

    /**
     * Name        : 打开文件
     * <p>
     * Description : 回调方法，当客户端请求文件时触发。
     *
     * @param uri  客户端请求资源的URI
     * @param mode 操作模式（读写权限、是否为追加写入等。）
     * @return 文件描述符。
     */
    @Nullable
    @Override
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        Log.i(TAG, "OpenFile. URI:" + uri);
        ParcelFileDescriptor parcelFileDescriptor = null;
        // 将客户端的请求URI传入匹配器
        int code = uriMatcher.match(uri);
        switch (code) {
            /* "textfile"读取请求，对应纯文本文件。 */
            case 101: {
                Log.i(TAG, "匹配到textfile请求。");
                try {
                    // 判断测试文件是否存在
                    File textFile = new File(getContext().getFilesDir(), "text.txt");
                    if (textFile.exists()) {
                        // 文件存在，则返回它的文件描述符给客户端，模式为只读。
                        parcelFileDescriptor =
                                ParcelFileDescriptor.open(textFile, ParcelFileDescriptor.MODE_READ_ONLY);
                    } else {
                        Log.w(TAG, "File not found.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            /* "configfile"写入请求，对应JSON配置文件。 */
            case 102: {
                Log.i(TAG, "匹配到configfile请求。");
                try {
                    File jsonFile = new File(getContext().getFilesDir(), "config.json");
                    // 返回它的文件描述符给客户端，模式为只写、自动创建。
                    int fdMode = ParcelFileDescriptor.MODE_WRITE_ONLY | ParcelFileDescriptor.MODE_CREATE;
                    parcelFileDescriptor = ParcelFileDescriptor.open(jsonFile, fdMode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
        }

        return parcelFileDescriptor;
    }
}
