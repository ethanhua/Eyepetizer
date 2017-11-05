package com.ethanhua.data.datasource;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.remote.CloudCommentDataStore;

import javax.inject.Inject;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class CommentDataStoreFactory {
    private final EyepetizerService mEyepetizerService;

    @Inject
    public CommentDataStoreFactory(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    public CloudCommentDataStore createCloudDataStore() {
        return new CloudCommentDataStore(mEyepetizerService);
    }
}
