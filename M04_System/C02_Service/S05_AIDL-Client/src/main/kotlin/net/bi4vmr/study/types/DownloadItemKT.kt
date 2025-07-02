package net.bi4vmr.study.types

import android.os.Parcel
import android.os.Parcelable

/**
 * 下载项实体类。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
data class DownloadItemKT(
    var id: Int = 0,
    var url: String,
    var percent: Float = 0.0F
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<DownloadItemKT> {
        override fun createFromParcel(parcel: Parcel): DownloadItemKT {
            return DownloadItemKT(parcel)
        }

        override fun newArray(size: Int): Array<DownloadItemKT?> {
            return arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this(
        // 按属性顺序从Parcel容器中读出属性值
        id = parcel.readInt(),
        url = parcel.readString() ?: "",
        percent = parcel.readFloat()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // 按属性顺序向Parcel容器中写入属性值
        parcel.apply {
            writeInt(id)
            writeString(url)
            writeFloat(percent)
        }
    }

    // 如果该类在AIDL中被标记为 `out` 类型参数，则必须实现此方法。
    fun readFromParcel(parcel: Parcel) {
        id = parcel.readInt()
        url = parcel.readString() ?: ""
        percent = parcel.readFloat()
    }
}
