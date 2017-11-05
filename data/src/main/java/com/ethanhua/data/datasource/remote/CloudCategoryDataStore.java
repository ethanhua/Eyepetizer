package com.ethanhua.data.datasource.remote;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.CategoryDataStore;
import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CloudCategoryDataStore implements CategoryDataStore {
    private EyepetizerService mEyepetizerService;

    public CloudCategoryDataStore(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    @Override
    public Single<ListData<ItemData<Category>>> list() {
        return mEyepetizerService.listAllCategory();
    }

    @Override
    public Single<CategoryInfoData> get(long id) {
        return mEyepetizerService.getCategoryInfo(id);
    }
}
