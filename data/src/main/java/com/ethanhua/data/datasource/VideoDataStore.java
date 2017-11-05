package com.ethanhua.data.datasource;

import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;

import java.util.Map;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/27.
 */

public interface VideoDataStore {

    Single<HomeData> listHome(Map queryMap);

    Single<ListData<ItemData<VideoData>>> listRelated(long videoId);

    Single<ListData<ItemData<VideoListData>>> listCategoryHome(long categoryId);

    Single<ListData<ItemData<VideoListData>>> listCategoryAll(long categoryId);

    Single<ListData<ItemData<VideoListData>>> listCategoryAuthor(long categoryId);

    Single<ListData<ItemData<VideoListData>>> listCategoryAlbum(long categoryId);

    Single<ListData<ItemData<VideoListData>>> listHot(Map queryMap);

    Single<ListData<ItemData<VideoListData>>> listGroupByCategory(Map queryMap);

    Single<ListData<ItemData<VideoListData>>> listSubscription(Map queryMap);

    Single<ListData<ItemData<VideoListData>>> listBySearch(String queryStr, Map pageQueryMap);

    Single<ListData<ItemData<VideoData>>> listRank(String rankStrategy, Map pageQueryMap);
}
