package net.bi4vmr.study.base;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import net.bi4vmr.study.R;

public class ImageActivity extends AppCompatActivity {

    private ContentResolver resolver;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        resolver = getContentResolver();
        getAudioData();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void getAudioData() {
        // 查询音频媒体信息
        final Uri URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = resolver.query(URI,
                null,
                null,
                null,
                null
        );

        // 游标为空时，退出当前方法。
        if (cursor == null) {
            Log.e("myapp", "出现未知错误");
            return;
        }

        // 获取游标内容计数
        int reportedCount = cursor.getCount();
        Log.d("myapp", "系统报告的曲目数量：" + reportedCount);
        // 没有数据时，退出当前方法。
        if (reportedCount == 0) {
            Log.i("myapp", "没有找到媒体文件");
            // 关闭游标释放资源
            cursor.close();
            return;
        }

        // 获取各属性对应的列名
        // SQLite的ID
        int iSQLID = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        // 标题
        int iTitle = cursor.getColumnIndex(MediaStore.Images.Media.TITLE);
        // 艺术家
        int iArtist = cursor.getColumnIndex(MediaStore.Images.Media.ARTIST);
        // 专辑
        int iAlbum = cursor.getColumnIndex(MediaStore.Images.Media.ALBUM);
        // 发行年份
        int iYear = cursor.getColumnIndex(MediaStore.Images.Media.YEAR);
        // 持续时长
        int iDuration = cursor.getColumnIndex(MediaStore.Images.Media.DURATION);
        // 文件名
        int iFileName = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
        // 文件大小
        int iFileSize = cursor.getColumnIndex(MediaStore.Images.Media.SIZE);
        // MIME类型
        int iMIME = cursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE);
        // 相对路径
        int iPath = cursor.getColumnIndex(MediaStore.Images.Media.RELATIVE_PATH);
        int iData = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

        // 游标移至首项
        cursor.moveToFirst();
        do {
            // 读取音频媒体的属性
            int id = cursor.getInt(iSQLID);
            String title = cursor.getString(iTitle);
            String artist = cursor.getString(iArtist);
            String album = cursor.getString(iAlbum);
            int year = cursor.getInt(iYear);
            int duration = cursor.getInt(iDuration);
            String fileName = cursor.getString(iFileName);
            int fileSize = cursor.getInt(iFileSize);
            String mime = cursor.getString(iMIME);
            // String path = cursor.getString(iPath);
            String data = cursor.getString(iData);
            Uri contentURI = ContentUris.withAppendedId(URI, id);
            Log.i("myapp", "ID: " + id + ", Title: " + title + ", Data: " + data);
            Log.i("myapp", "URI: " + contentURI);
            Bitmap bm = BitmapFactory.decodeFile(data);
            ImageView iv = findViewById(R.id.ivTest);
            iv.setImageDrawable(new BitmapDrawable(bm));
        } while (cursor.moveToNext());

        // 关闭游标释放资源
        cursor.close();
    }
}
