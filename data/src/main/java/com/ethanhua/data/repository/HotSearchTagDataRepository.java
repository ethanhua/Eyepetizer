package com.ethanhua.data.repository;

import com.ethanhua.data.datasource.HotSearchTagDataStoreFactory;
import com.ethanhua.domain.respository.HotSearchTagRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class HotSearchTagDataRepository implements HotSearchTagRepository {
    private final HotSearchTagDataStoreFactory mHotSearchTagDataStoreFactory;

    @Inject
    public HotSearchTagDataRepository(HotSearchTagDataStoreFactory mHotSearchTagDataStoreFactory) {
        this.mHotSearchTagDataStoreFactory = mHotSearchTagDataStoreFactory;
    }
    @Override
    public Single<List<String>> list() {
        return mHotSearchTagDataStoreFactory.createCloudDataStore().list();
    }
}
