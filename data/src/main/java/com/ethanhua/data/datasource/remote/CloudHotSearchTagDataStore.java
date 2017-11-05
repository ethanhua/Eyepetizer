package com.ethanhua.data.datasource.remote;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.HotSearchTagDataStore;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class CloudHotSearchTagDataStore implements HotSearchTagDataStore {
    private EyepetizerService mEyepetizerService;

    public CloudHotSearchTagDataStore(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    @Override
    public Single<List<String>> list() {
        return mEyepetizerService.listHotQueryTag();
    }
}
