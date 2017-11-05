package com.ethanhua.data.datasource;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.remote.CloudHotSearchTagDataStore;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class HotSearchTagDataStoreFactory {

    private final EyepetizerService mEyepetizerService;

    @Inject
    public HotSearchTagDataStoreFactory(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    public CloudHotSearchTagDataStore createCloudDataStore() {
        return new CloudHotSearchTagDataStore(mEyepetizerService);
    }
}
