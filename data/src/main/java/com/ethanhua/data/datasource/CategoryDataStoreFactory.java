package com.ethanhua.data.datasource;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.remote.CloudCategoryDataStore;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CategoryDataStoreFactory {

    private final EyepetizerService mEyepetizerService;

    @Inject
    public CategoryDataStoreFactory(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    public CloudCategoryDataStore createCloudDataStore() {
        return new CloudCategoryDataStore(mEyepetizerService);
    }
}
