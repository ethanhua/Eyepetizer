package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.respository.VideoRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/30.
 */

public class GetVideosByRankCase {
    private final VideoRepository mVideoRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetVideosByRankCase(VideoRepository videoRepository,
                               ThreadTransformer threadTransformer) {
        this.mVideoRepository = videoRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<ListData<ItemData<VideoData>>> execute(String strategy,
                                                         Map map) {
        return mVideoRepository.listRank(strategy, map).compose(mThreadTransformer);
    }
}
