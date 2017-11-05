package com.ethanhua.data.datasource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public interface HotSearchTagDataStore {
    Single<List<String>> list();
}
