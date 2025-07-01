package net.bi4vmr.study.types;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * 下载项实体类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
public class DownloadItem implements Parcelable {

    // 任务ID
    private int id;
    // 下载地址
    private String url;
    // 进度
    private float percent;

    public static final Creator<DownloadItem> CREATOR = new Creator<>() {
        @Override
        public DownloadItem createFromParcel(Parcel in) {
            return new DownloadItem(in);
        }

        @Override
        public DownloadItem[] newArray(int size) {
            return new DownloadItem[size];
        }
    };

    public DownloadItem() {
    }

    public DownloadItem(String url) {
        this.url = url;
    }

    // Parcel构造方法
    protected DownloadItem(Parcel in) {
        // 按属性顺序从Parcel容器中读出属性值
        id = in.readInt();
        url = in.readString();
        percent = in.readFloat();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @NonNull
    @Override
    public String toString() {
        return "DownloadItem{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", percent=" + percent +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 按属性顺序向Parcel容器中写入属性值
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeFloat(percent);
    }

    // 如果该类在AIDL中被标记为 `out` 类型参数，则必须实现此方法。
    public void readFromParcel(Parcel in) {
        id = in.readInt();
        url = in.readString();
        percent = in.readFloat();
    }
}
