package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.WatchRecord;
import com.ethanhua.domain.respository.VideoRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by ethanhua on 2017/12/17.
 */

public class InsertVideoWatchRecordCase {

    private final VideoRepository mVideoRepository;
    private final ThreadTransformer threadTransformer;

    @Inject
    public InsertVideoWatchRecordCase(VideoRepository videoRepository, ThreadTransformer threadTransformer) {
        this.mVideoRepository = videoRepository;
        this.threadTransformer = threadTransformer;
    }

    public Completable execute(WatchRecord watchRecord) {
        return mVideoRepository.insert(watchRecord).compose(threadTransformer);
    }
}
