package com.ethanhua.commonlib.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.text.TextUtils;

/**
 * Created by ethanhua on 2017/11/16.
 */

public class VideoUrlSource implements Parcelable {
    /**
     * 依次分别为：流畅、清晰、高清、超清和1080P
     */
    public static final int MEDIA_QUALITY_LOW = 0;
    public static final int MEDIA_QUALITY_NORMAL = 1;
    public static final int MEDIA_QUALITY_HIGH = 2;
    public static final int MEDIA_QUALITY_SUPER = 3;
    public static final int MEDIA_QUALITY_BD = 4;

    @IntDef({MEDIA_QUALITY_LOW,
            MEDIA_QUALITY_NORMAL,
            MEDIA_QUALITY_HIGH,
            MEDIA_QUALITY_SUPER,
            MEDIA_QUALITY_BD})
    public @interface QualityType {

    }

    public String bdUrl;
    public String supperUrl;
    public String highUrl;
    public String normalUrl;
    public String lowUrl;

    public VideoUrlSource() {

    }

    public VideoUrlSource(Parcel in) {
        bdUrl = in.readString();
        supperUrl = in.readString();
        highUrl = in.readString();
        normalUrl = in.readString();
        lowUrl = in.readString();
    }

    public static final Creator<VideoUrlSource> CREATOR = new Creator<VideoUrlSource>() {
        @Override
        public VideoUrlSource createFromParcel(Parcel in) {
            return new VideoUrlSource(in);
        }

        @Override
        public VideoUrlSource[] newArray(int size) {
            return new VideoUrlSource[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bdUrl);
        dest.writeString(supperUrl);
        dest.writeString(highUrl);
        dest.writeString(normalUrl);
        dest.writeString(lowUrl);
    }

    public String getUrlByQuality(@QualityType int qualityType) {
        if (qualityType == MEDIA_QUALITY_LOW) {
            return lowUrl;
        }
        if (qualityType == MEDIA_QUALITY_NORMAL) {
            return normalUrl;
        }
        if (qualityType == MEDIA_QUALITY_HIGH) {
            return highUrl;
        }
        if (qualityType == MEDIA_QUALITY_SUPER) {
            return supperUrl;
        }
        if (qualityType == MEDIA_QUALITY_BD) {
            return bdUrl;
        }
        return highUrl;
    }

    public boolean isEmptyOrSingle() {
        int count = 0;
        if (!TextUtils.isEmpty(bdUrl)) {
            count++;
        }
        if (!TextUtils.isEmpty(supperUrl)) {
            count++;
        }
        if (!TextUtils.isEmpty(highUrl)) {
            count++;
        }
        if (!TextUtils.isEmpty(normalUrl)) {
            count++;
        }
        if (!TextUtils.isEmpty(lowUrl)) {
            count++;
        }
        return count <= 1;
    }
}
