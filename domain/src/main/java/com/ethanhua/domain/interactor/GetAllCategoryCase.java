package com.ethanhua.domain.interactor;

import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.respository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class GetAllCategoryCase {
    private final CategoryRepository mCategoryRepository;
    private final ThreadTransformer mThreadTransformer;

    @Inject
    public GetAllCategoryCase(CategoryRepository categoryRepository,
                              ThreadTransformer threadTransformer) {
        this.mCategoryRepository = categoryRepository;
        this.mThreadTransformer = threadTransformer;
    }

    public Single<ListData<ItemData<Category>>> execute() {
        return mCategoryRepository.list().compose(mThreadTransformer);
    }
}
