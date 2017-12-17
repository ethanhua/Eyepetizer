package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.WatchRecord;
import com.ethanhua.domain.respository.VideoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by ethanhua on 2017/12/17.
 */

public class GetWatchRecordsCase {
    private final VideoRepository mVideoRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetWatchRecordsCase(VideoRepository videoRepository,
                               ThreadTransformer threadTransformer) {
        this.mVideoRepository = videoRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Flowable<List<WatchRecord>> execute(String userId) {
        return mVideoRepository.listWatchHistory(userId).compose(mThreadTransformer);
    }
}
