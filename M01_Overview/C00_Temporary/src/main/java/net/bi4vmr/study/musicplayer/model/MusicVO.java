package net.bi4vmr.study.media_demo_musicplayer.model;

import android.graphics.Bitmap;

/**
 * Name          : MusicVO
 * Author        : BI4VMR
 * Date          : 2022-04-18 09:36
 * Description   : 音乐视图模型
 */
public class MusicVO {

    private String id;
    private String title;
    private String artist;
    private String uri;
    private Bitmap coverBMP;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getCoverBMP() {
        return coverBMP;
    }

    public void setCoverBMP(Bitmap coverBMP) {
        this.coverBMP = coverBMP;
    }

    @Override
    public String toString() {
        return "MusicVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
