package net.bi4vmr.study.media_demo_musicplayer.manager;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import net.bi4vmr.study.media_demo_musicplayer.common.MusicApp;
import net.bi4vmr.study.media_demo_musicplayer.model.MusicVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Name          : 音乐资源管理器
 * Author        : 詹屹罡
 * Email         : bi4vmr@outlook.com.cn
 * Date          : 2022-04-18 15:03
 * Description   : 管理音乐媒体文件
 */
public class MusicDataManager {

    private static final String TAG_APP = MusicApp.TAG;

    private static MusicDataManager instance;
    private final Context mContext;


    // 单例模式，对外部禁用构造方法。
    private MusicDataManager(Context context) {
        // 获取应用级别的Context，其它实例可能会导致内存泄漏。
        mContext = context.getApplicationContext();
    }

    // 获取MusicManager实例
    public static MusicDataManager getInstance(Context context) {
        if (instance == null) {
            instance = new MusicDataManager(context);
        }
        return instance;
    }

    public List<MusicVO> getAudioData() {
        List<MusicVO> datas = new ArrayList<>();
        // 查询音频媒体信息
        final Uri audioURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(audioURI, null, null, null);

        // 游标为空时，退出当前方法。
        if (cursor == null) {
            Log.e(TAG_APP, "出现未知错误");
            return null;
        }

        // 获取游标内容计数
        int reportedCount = cursor.getCount();
        Log.d(TAG_APP, "系统报告的曲目数量：" + reportedCount);
        // 没有数据时，退出当前方法。
        if (reportedCount == 0) {
            Log.i(TAG_APP, "没有找到媒体文件");
            // 关闭游标释放资源
            cursor.close();
            return null;
        }

        // 获取各属性对应的列名
        // SQLite的ID
        int iSQLID = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
        // 标题
        int iTitle = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        // 艺术家
        int iArtist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        // 专辑
        int iAlbum = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
        // 发行年份
        int iYear = cursor.getColumnIndex(MediaStore.Audio.Media.YEAR);
        // 持续时长
        int iDuration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        // 文件名
        int iFileName = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
        // 文件大小
        int iFileSize = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
        // MIME类型
        int iMIME = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);

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

            // 过滤录音文件（魅族手机）
            if (artist.equals("Meizu Recorder")) {
                continue;
            }

            // 构造文件的URI
            Uri itemURIObj = ContentUris.withAppendedId(audioURI, id);
            String itemURI = itemURIObj.toString();

            // 获取专辑封面
            Bitmap bmp = getMusicCover(itemURI);

            MusicVO music = new MusicVO();
            music.setId(String.valueOf(id));
            music.setTitle(title);
            music.setArtist(artist);
            music.setUri(itemURI);
            music.setCoverBMP(bmp);
            datas.add(music);
        } while (cursor.moveToNext());

        // 关闭游标释放资源
        cursor.close();

        return datas;
    }

    /**
     * Name        : 获取音乐封面
     * Author      : BI4VMR
     * Date        : 2022/4/19 下午3:32
     * Description : 获取音乐的专辑封面。
     *
     * @param uri 音乐文件的URI
     * @return 音乐的专辑封面BMP对象
     */
    public Bitmap getMusicCover(String uri) {
        if (1 < 2) {
            return null;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(mContext, Uri.parse(uri));
        byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();
        if (picture != null) {
            return BitmapFactory.decodeByteArray(picture, 0, picture.length);
        } else {
            return null;
        }
    }
}
