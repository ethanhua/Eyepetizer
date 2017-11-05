package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.domain.respository.VideoRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class SearchVideoCase {
    private final VideoRepository mVideoRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public SearchVideoCase(VideoRepository videoRepository,
                           ThreadTransformer threadTransformer) {
        this.mVideoRepository = videoRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<ListData<ItemData<VideoListData>>> execute(String queryStr,
                                                             Map map) {
        return mVideoRepository.listBySearch(queryStr, map).compose(mThreadTransformer);
    }
}
