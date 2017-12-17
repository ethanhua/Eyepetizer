package com.ethanhua.eyepetizer.module.video.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.ethanhua.commonlib.media.VideoUrlSource;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.PlayInfo;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.domain.model.WatchRecord;

import java.util.List;

/**
 * Created by ethanhua on 2017/9/15.
 */

public class VideoBaseVM extends ViewModel implements Parcelable {
    public long id;
    public String actionUrl;
    public final ObservableField<Uri> uri = new ObservableField<>();
    public final ObservableField<Uri> thumbPlayUri = new ObservableField<>();
    public final ObservableField<String> coverUrl = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<String> blurredUrl = new ObservableField<>();
    public final ObservableField<String> iconUrl = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> slogan = new ObservableField<>();
    public final ObservableField<String> subTitle = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableInt collectionCount = new ObservableInt();
    public final ObservableInt shareCount = new ObservableInt();
    public final ObservableInt replyCount = new ObservableInt();
    public final ObservableField<String> authorName = new ObservableField<>();
    public final ObservableField<String> authorIntro = new ObservableField<>();
    public final ObservableField<String> authorAvatar = new ObservableField<>();
    public final ObservableField<VideoUrlSource> videoUrlSource = new ObservableField<>();
    public final ObservableInt seekPosition = new ObservableInt(0);

    public VideoBaseVM() {

    }

    public VideoBaseVM(Parcel in) {
        id = in.readLong();
        actionUrl = in.readString();
        uri.set(in.readParcelable(Uri.class.getClassLoader()));
        thumbPlayUri.set(in.readParcelable(Uri.class.getClassLoader()));
        coverUrl.set(in.readString());
        imageUrl.set(in.readString());
        blurredUrl.set(in.readString());
        iconUrl.set(in.readString());
        title.set(in.readString());
        slogan.set(in.readString());
        subTitle.set(in.readString());
        description.set(in.readString());
        authorName.set(in.readString());
        authorIntro.set(in.readString());
        authorAvatar.set(in.readString());
        collectionCount.set(in.readInt());
        shareCount.set(in.readInt());
        replyCount.set(in.readInt());
        videoUrlSource.set(in.readParcelable(VideoUrlSource.class.getClassLoader()));
    }

    public static final Creator<VideoBaseVM> CREATOR = new Creator<VideoBaseVM>() {
        @Override
        public VideoBaseVM createFromParcel(Parcel in) {
            return new VideoBaseVM(in);
        }

        @Override
        public VideoBaseVM[] newArray(int size) {
            return new VideoBaseVM[size];
        }
    };

    public static VideoBaseVM mapFromVideoList(ItemData<VideoListData> homeItem) {
        VideoBaseVM videoBaseVM = new VideoBaseVM();
        if (homeItem == null || homeItem.data == null) {
            return videoBaseVM;
        }
        if (homeItem.data.content != null && homeItem.data.content.data != null) {
            mapFrom(videoBaseVM, homeItem.data.content);
        } else {
            mapFrom(videoBaseVM, homeItem.data);
        }
        if (homeItem.data.header != null) {
            videoBaseVM.description.set(homeItem.data.header.description);
            videoBaseVM.title.set(homeItem.data.header.title);
            videoBaseVM.iconUrl.set(homeItem.data.header.icon);
        }
        return videoBaseVM;
    }

    public static VideoBaseVM mapFromVideo(ItemData<VideoData> itemDataContent) {
        return mapFrom(new VideoBaseVM(), itemDataContent);
    }

    public static VideoBaseVM mapFromWatchRecord(WatchRecord watchRecord) {
        VideoBaseVM videoBaseVM = new VideoBaseVM();
        if (watchRecord != null) {
            videoBaseVM.id = watchRecord.videoId;
            videoBaseVM.actionUrl = watchRecord.actionUrl;
            videoBaseVM.uri.set(Uri.parse(watchRecord.playUrl));
            videoBaseVM.coverUrl.set(watchRecord.coverUrl);
            videoBaseVM.blurredUrl.set(watchRecord.blurredUrl);
            videoBaseVM.title.set(watchRecord.title);
            videoBaseVM.slogan.set(watchRecord.slogan);
            videoBaseVM.subTitle.set(watchRecord.subTitle);
            videoBaseVM.description.set(watchRecord.description);
            videoBaseVM.collectionCount.set(watchRecord.collectionCount);
            videoBaseVM.shareCount.set(watchRecord.shareCount);
            videoBaseVM.replyCount.set(watchRecord.replyCount);
            videoBaseVM.authorName.set(watchRecord.authorName);
            videoBaseVM.authorAvatar.set(watchRecord.authorAvatar);
            videoBaseVM.authorIntro.set(watchRecord.authorIntro);
            VideoUrlSource videoUrlSource = new VideoUrlSource();
            videoUrlSource.bdUrl = watchRecord.bdPlayUrl;
            videoUrlSource.supperUrl = watchRecord.supperPlayUrl;
            videoUrlSource.highUrl = watchRecord.highPlayUrl;
            videoUrlSource.normalUrl = watchRecord.normalPlayUrl;
            videoUrlSource.lowUrl = watchRecord.lowPlayUrl;
            videoBaseVM.videoUrlSource.set(videoUrlSource);
        }
        return videoBaseVM;
    }

    public WatchRecord mapToWatchRecord() {
        WatchRecord watchRecord = new WatchRecord();
        watchRecord.videoId = id;
        watchRecord.actionUrl = actionUrl;
        watchRecord.playUrl = uri.get().toString();
        watchRecord.coverUrl = coverUrl.get();
        watchRecord.blurredUrl = blurredUrl.get();
        watchRecord.title = title.get();
        watchRecord.slogan = slogan.get();
        watchRecord.subTitle = subTitle.get();
        watchRecord.description = description.get();
        watchRecord.collectionCount = collectionCount.get();
        watchRecord.shareCount = shareCount.get();
        watchRecord.replyCount = replyCount.get();
        watchRecord.authorName = authorName.get();
        watchRecord.authorIntro = authorIntro.get();
        watchRecord.authorAvatar = authorAvatar.get();
        Log.e("event", "vm map:" + watchRecord.coverUrl + "-" + imageUrl.get());
        if (videoUrlSource.get() != null) {
            watchRecord.bdPlayUrl = videoUrlSource.get().bdUrl;
            watchRecord.supperPlayUrl = videoUrlSource.get().supperUrl;
            watchRecord.highPlayUrl = videoUrlSource.get().highUrl;
            watchRecord.normalPlayUrl = videoUrlSource.get().normalUrl;
            watchRecord.lowPlayUrl = videoUrlSource.get().lowUrl;
        }
        watchRecord.updateTime = System.currentTimeMillis();
        return watchRecord;
    }

    private static VideoBaseVM mapFrom(VideoBaseVM videoBaseVM, VideoListData videoListData) {
        if (videoListData == null) {
            return videoBaseVM;
        }
        videoBaseVM.id = videoListData.id;
        if (videoListData.cover != null) {
            videoBaseVM.coverUrl.set(videoListData.cover.feed);
            videoBaseVM.blurredUrl.set(videoListData.cover.blurred);
        }
        videoBaseVM.imageUrl.set(videoListData.image);
        videoBaseVM.actionUrl = videoListData.actionUrl;
        videoBaseVM.slogan.set(videoListData.slogan);
        videoBaseVM.subTitle.set("#"
                + videoListData.category
                + " / "
                + videoListData.duration / 60
                + "' "
                + videoListData.duration % 60
                + '"');
        videoBaseVM.title.set(videoListData.title);
        if (!TextUtils.isEmpty(videoListData.playUrl)) {
            videoBaseVM.uri.set(Uri.parse(videoListData.playUrl));
        }
        if (!TextUtils.isEmpty(videoListData.thumbPlayUrl)) {
            videoBaseVM.thumbPlayUri.set(Uri.parse(videoListData.thumbPlayUrl));
        }
        if (videoListData.consumption != null) {
            videoBaseVM.collectionCount.set(videoListData.consumption.collectionCount);
            videoBaseVM.shareCount.set(videoListData.consumption.shareCount);
            videoBaseVM.replyCount.set(videoListData.consumption.replyCount);
        }
        if (videoListData.author != null) {
            videoBaseVM.authorAvatar.set(videoListData.author.icon);
            videoBaseVM.authorName.set(videoListData.author.name);
            videoBaseVM.authorIntro.set(videoListData.author.description);
        }
        videoBaseVM.videoUrlSource.set(mapPlayInfoToUrlSource(videoListData.playInfo));
        return videoBaseVM;
    }

    private static VideoBaseVM mapFrom(VideoBaseVM videoBaseVM,
                                       ItemData<VideoData> itemDataContent) {
        if (itemDataContent == null || itemDataContent.data == null) {
            return videoBaseVM;
        }
        videoBaseVM.id = itemDataContent.data.id;
        if (itemDataContent.data.cover != null) {
            videoBaseVM.coverUrl.set(itemDataContent.data.cover.feed);
            videoBaseVM.blurredUrl.set(itemDataContent.data.cover.blurred);
        }
        videoBaseVM.videoUrlSource.set(mapPlayInfoToUrlSource(itemDataContent.data.playInfo));
        videoBaseVM.imageUrl.set(itemDataContent.data.image);
        videoBaseVM.actionUrl = itemDataContent.data.actionUrl;
        videoBaseVM.slogan.set(itemDataContent.data.slogan);
        videoBaseVM.subTitle.set("#"
                + itemDataContent.data.category
                + " / "
                + itemDataContent.data.duration / 60
                + "' "
                + itemDataContent.data.duration % 60
                + '"');
        videoBaseVM.title.set(itemDataContent.data.title);
        if (!TextUtils.isEmpty(itemDataContent.data.playUrl)) {
            videoBaseVM.uri.set(Uri.parse(itemDataContent.data.playUrl));
        }
        if (!TextUtils.isEmpty(itemDataContent.data.thumbPlayUrl)) {
            videoBaseVM.thumbPlayUri.set(Uri.parse(itemDataContent.data.thumbPlayUrl));
        }
        if (itemDataContent.data.consumption != null) {
            videoBaseVM.collectionCount.set(itemDataContent.data.consumption.collectionCount);
            videoBaseVM.shareCount.set(itemDataContent.data.consumption.shareCount);
            videoBaseVM.replyCount.set(itemDataContent.data.consumption.replyCount);
        }
        if (itemDataContent.data.author != null) {
            videoBaseVM.authorAvatar.set(itemDataContent.data.author.icon);
            videoBaseVM.authorName.set(itemDataContent.data.author.name);
            videoBaseVM.authorIntro.set(itemDataContent.data.author.description);
        }
        return videoBaseVM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(actionUrl);
        parcel.writeParcelable(uri.get(), i);
        parcel.writeParcelable(thumbPlayUri.get(), i);
        parcel.writeString(coverUrl.get());
        parcel.writeString(imageUrl.get());
        parcel.writeString(blurredUrl.get());
        parcel.writeString(iconUrl.get());
        parcel.writeString(title.get());
        parcel.writeString(slogan.get());
        parcel.writeString(subTitle.get());
        parcel.writeString(description.get());
        parcel.writeString(authorName.get());
        parcel.writeString(authorIntro.get());
        parcel.writeString(authorAvatar.get());
        parcel.writeInt(collectionCount.get());
        parcel.writeInt(shareCount.get());
        parcel.writeInt(replyCount.get());
        parcel.writeParcelable(videoUrlSource.get(), i);
    }


    private static VideoUrlSource mapPlayInfoToUrlSource(List<PlayInfo> playInfoList) {
        VideoUrlSource videoUrlSource = new VideoUrlSource();
        if (playInfoList != null && playInfoList.size() > 0) {
            for (int i = 0; i < playInfoList.size(); i++) {
                if (PlayInfo.TYPE_HIGH.equals(playInfoList.get(i).type)) {
                    videoUrlSource.highUrl = playInfoList.get(i).url;
                    continue;
                }
                if (PlayInfo.TYPE_NORMAL.equals(playInfoList.get(i).type)) {
                    videoUrlSource.normalUrl = playInfoList.get(i).url;
                    continue;
                }
                if (PlayInfo.TYPE_LOW.equals(playInfoList.get(i).type)) {
                    videoUrlSource.lowUrl = playInfoList.get(i).url;
                }
            }
        }
        return videoUrlSource;
    }
}
