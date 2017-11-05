package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.respository.HotSearchTagRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class GetHotSearchTagCase {
    private final HotSearchTagRepository hotSearchTagRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetHotSearchTagCase(HotSearchTagRepository hotSearchTagRepository,
                               ThreadTransformer threadTransformer) {
        this.hotSearchTagRepository = hotSearchTagRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<List<String>> execute() {
        return hotSearchTagRepository.list().compose(mThreadTransformer);
    }
}
