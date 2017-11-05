package com.ethanhua.domain.respository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/24.
 */

public interface HotSearchTagRepository {

    Single<List<String>> list();

}
