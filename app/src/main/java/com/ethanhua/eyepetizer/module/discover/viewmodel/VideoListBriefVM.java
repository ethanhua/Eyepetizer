package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/19.
 */

public class VideoListBriefVM extends AbsListViewModel<VideoData, VideoListData> {
    public final ObservableField<String> authorName = new ObservableField<>();
    public final ObservableField<String> authorIntro = new ObservableField<>();
    public final ObservableField<String> authorAvatar = new ObservableField<>();
    private final VideoListData videoListData;

    public VideoListBriefVM(VideoListData videoListData) {
        this.videoListData = videoListData;
        loadData();
    }

    @Override
    protected Single<VideoListData> provideSource(Map map) {
        return Single.just(videoListData);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoData> itemData) {
        return VideoBaseVM.mapFromVideo(itemData);
    }

    public static VideoListBriefVM mapFrom(VideoListData videoListData) {
        VideoListBriefVM briefVM = new VideoListBriefVM(videoListData);
        briefVM.authorAvatar.set(videoListData.header.icon);
        briefVM.authorName.set(videoListData.header.title);
        briefVM.authorIntro.set(videoListData.header.description);
        return briefVM;
    }
}
