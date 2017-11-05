package com.ethanhua.data.datasource;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.remote.CloudVideoDataStore;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/27.
 */

public class VideoDataStoreFactory {

    private final EyepetizerService eyepetizerService;

    @Inject
    public VideoDataStoreFactory(EyepetizerService eyepetizerService) {
        this.eyepetizerService = eyepetizerService;
    }

    public CloudVideoDataStore createCloudDataStore() {
        return new CloudVideoDataStore(eyepetizerService);
    }
}
