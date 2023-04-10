package net.bi4vmr.study.media_demo_musicplayer.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

/**
 * Name          : MusicUtil
 * Author        : BI4VMR
 * Date          : 2022-04-19 15:40
 * Description   : 音乐文件工具类
 */
public class MusicUtil {

    /**
     * Name        : 获取音乐的专辑封面
     * Author      : BI4VMR
     * Date        : 2022/4/19 15:32
     * Description : 获取音乐的专辑封面图片。
     *
     * @param uri 音乐文件的URI
     * @return 音乐的专辑封面Bitmap对象
     */
    public static Bitmap getMusicCover(String uri, Context mContext) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(mContext, Uri.parse(uri));
        byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();
        return BitmapFactory.decodeByteArray(picture, 0, picture.length);
    }
}
