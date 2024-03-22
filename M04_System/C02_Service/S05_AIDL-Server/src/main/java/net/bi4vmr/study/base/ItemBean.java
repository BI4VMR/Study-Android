package net.bi4vmr.study.base;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Name        : 下载项Bean类
 * <p>
 * Author      : BI4VMR
 * <p>
 * Email       : bi4vmr@outlook.com
 * <p>
 * Date        : 2023-03-20 14:05
 * <p>
 * Description : 下载项Bean类。
 */
public class ItemBean implements Parcelable {

    // 任务ID
    private int id;
    // 下载地址
    private String url;
    // 进度
    private float percent;

    public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
        @Override
        public ItemBean createFromParcel(Parcel in) {
            return new ItemBean(in);
        }

        @Override
        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };

    public ItemBean(){}

    public ItemBean(String url) {
        this.url = url;
    }

    // Parcel构造方法
    protected ItemBean(Parcel in) {
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
        return "ItemBean{" +
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
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeFloat(percent);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        url = in.readString();
        percent = in.readFloat();
    }
}
