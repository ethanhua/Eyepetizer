package com.ethanhua.eyepetizer.module.video.viewmodel;

import android.databinding.ObservableBoolean;

import com.ethanhua.commonlib.util.TimeUtils;
import com.ethanhua.commonlib.widget.recyclerview.Refreshable;
import com.ethanhua.domain.interactor.GetWatchRecordsCase;
import com.ethanhua.domain.model.WatchRecord;
import com.ethanhua.eyepetizer.viewmodel.LiveViewModel;
import com.ethanhua.eyepetizer.viewmodel.ObservableItems;

import javax.inject.Inject;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by ethanhua on 2017/12/17.
 */

public class VideoWatchRecordsVM extends LiveViewModel implements Refreshable {

    private final ObservableItems mListData = new ObservableItems();
    private final MultiTypeAdapter mAdapter = new MultiTypeAdapter(mListData);
    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    private GetWatchRecordsCase getWatchRecordsCase;

    @Inject
    public VideoWatchRecordsVM(GetWatchRecordsCase getWatchRecordsCase) {
        this.getWatchRecordsCase = getWatchRecordsCase;
    }

    @Override
    public void refresh() {
        mIsLoading.set(true);
        loadData();
    }

    protected void loadData() {
        getWatchRecordsCase.execute("hhy")
                .compose(bindToLifecycle())
                .subscribe(watchRecords -> {
                    mIsLoading.set(false);
                    mListData.clear();
                    for (WatchRecord watchRecord : watchRecords) {
                        VideoWatchRecordVM videoWatchRecordVM = new VideoWatchRecordVM();
                        VideoBaseVM videoBaseVM = VideoBaseVM.mapFromWatchRecord(watchRecord);
                        videoWatchRecordVM.videoBaseVM.set(videoBaseVM);
                        videoWatchRecordVM.watchTime.set(watchRecord.updateTime);
                        videoWatchRecordVM.subTitle.set("#" + videoBaseVM.category.get()
                                + " / " + TimeUtils.getFriendlyTimeSpanByNow(watchRecord.updateTime));
                        getListData().add(videoWatchRecordVM);
                    }
                }, throwable -> {
                    mIsLoading.set(false);
                    throwable.printStackTrace();
                });
    }

    public ObservableItems getListData() {
        return mListData;
    }

    public MultiTypeAdapter getAdapter() {
        return mAdapter;
    }

    public ObservableBoolean isLoading() {
        return mIsLoading;
    }
}
