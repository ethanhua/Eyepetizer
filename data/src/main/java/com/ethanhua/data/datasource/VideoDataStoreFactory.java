package com.ethanhua.data.datasource;

import android.content.Context;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.local.LocalVideoDataStore;
import com.ethanhua.data.datasource.remote.CloudVideoDataStore;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/9/27.
 */

public class VideoDataStoreFactory {

    private final EyepetizerService eyepetizerService;
    private final Context context;

    @Inject
    public VideoDataStoreFactory(EyepetizerService eyepetizerService, Context context) {
        this.eyepetizerService = eyepetizerService;
        this.context = context;
    }

    public CloudVideoDataStore createCloudDataStore() {
        return new CloudVideoDataStore(eyepetizerService);
    }

    public LocalVideoDataStore createLocalDataStore() {
        return new LocalVideoDataStore(context);
    }
}
