package com.ethanhua.eyepetizer.module.home.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/18.
 */

public class VideoListVM extends AbsListViewModel<VideoData, VideoListData> {
    private final VideoListData videoListData;
    public String actionUrl;
    public final ObservableField<String> coverUrl = new ObservableField<>();

    public VideoListVM(VideoListData videoListData) {
        this.videoListData = videoListData;
        if (videoListData.header != null) {
            coverUrl.set(videoListData.header.cover);
            actionUrl = videoListData.header.actionUrl;
        }
        loadData();
    }

    @Override
    protected Single<VideoListData> provideSource(Map map) {
        return Single.just(videoListData);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoData> itemData) {
        if (ItemData.TYPE_VIDEO.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideo(itemData);
        }
        if (ItemData.TYPE_ACTION_CARD.equals(itemData.type)) {
            return ActionCardVM.mapFrom(itemData.data);
        }
        return null;
    }
}
