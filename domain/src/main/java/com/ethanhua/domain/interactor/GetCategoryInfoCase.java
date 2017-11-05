package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.respository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class GetCategoryInfoCase {
    private final CategoryRepository mCategoryRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetCategoryInfoCase(CategoryRepository categoryRepository,
                               ThreadTransformer threadTransformer) {
        this.mCategoryRepository = categoryRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<CategoryInfoData> execute(long categoryId) {
        return mCategoryRepository.get(categoryId).compose(mThreadTransformer);
    }
}
