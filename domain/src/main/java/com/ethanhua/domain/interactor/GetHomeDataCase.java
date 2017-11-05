package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.respository.VideoRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/16.
 */

public class GetHomeDataCase {
    private final VideoRepository mVideoRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetHomeDataCase(VideoRepository videoRepository,
                           ThreadTransformer threadTransformer) {
        this.mVideoRepository = videoRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<HomeData> execute(Map map) {
        return mVideoRepository.listHome(map).compose(mThreadTransformer);
    }
}
