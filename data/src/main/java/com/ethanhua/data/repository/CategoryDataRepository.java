package com.ethanhua.data.repository;

import com.ethanhua.data.datasource.CategoryDataStoreFactory;
import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.respository.CategoryRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryDataRepository implements CategoryRepository {

    private final CategoryDataStoreFactory mCategoryDataStoreFactory;

    @Inject
    public CategoryDataRepository(CategoryDataStoreFactory categoryDataStoreFactory) {
        this.mCategoryDataStoreFactory = categoryDataStoreFactory;
    }

    @Override
    public Single<ListData<ItemData<Category>>> list() {
        return mCategoryDataStoreFactory.createCloudDataStore().list();
    }

    @Override
    public Single<CategoryInfoData> get(long id) {
        return mCategoryDataStoreFactory.createCloudDataStore().get(id);
    }
}
