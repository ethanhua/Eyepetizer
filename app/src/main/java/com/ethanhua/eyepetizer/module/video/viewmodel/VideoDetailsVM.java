package com.ethanhua.eyepetizer.module.video.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetRelatedVideosCase;
import com.ethanhua.domain.interactor.InsertVideoWatchRecordCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.WatchRecord;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/28.
 */

public class VideoDetailsVM extends
        AbsListViewModel<VideoData, ListData<ItemData<VideoData>>> {

    public VideoBaseVM baseVM;
    private final GetRelatedVideosCase mGetRelatedVideosCase;
    private final InsertVideoWatchRecordCase mInsertVideoWatchRecordCase;

    @Inject
    public VideoDetailsVM(GetRelatedVideosCase getRelatedVideosCase,
                          InsertVideoWatchRecordCase insertVideoWatchRecordCase) {
        this.mGetRelatedVideosCase = getRelatedVideosCase;
        this.mInsertVideoWatchRecordCase = insertVideoWatchRecordCase;
    }

    @Override
    protected Single<ListData<ItemData<VideoData>>> provideSource(Map map) {
        return mGetRelatedVideosCase.execute(baseVM.id)
                .doAfterSuccess(itemDataListData -> getListData().add(0, baseVM));
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoData> itemData) {
        if (ItemData.TYPE_VIDEO_SMALL_CARD.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideo(itemData);
        }
        return null;
    }

    public void record(long position) {
        WatchRecord watchRecord = baseVM.mapToWatchRecord();
        watchRecord.position = position;
        watchRecord.userId = "hhy";
        mInsertVideoWatchRecordCase.execute(watchRecord).subscribe();
    }

}
