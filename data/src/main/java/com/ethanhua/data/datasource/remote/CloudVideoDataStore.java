package com.ethanhua.data.datasource.remote;

import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.datasource.VideoDataStore;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.domain.model.WatchRecord;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/27.
 */

public class CloudVideoDataStore implements VideoDataStore {

    private final EyepetizerService mEyepetizerService;

    public CloudVideoDataStore(EyepetizerService eyepetizerService) {
        this.mEyepetizerService = eyepetizerService;
    }

    @Override
    public Single<HomeData> listHome(Map queryMap) {
        return mEyepetizerService.listHomeVideo(queryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRelated(long videoId) {
        return mEyepetizerService.listRelatedVideos(videoId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryHome(long categoryId) {
        return mEyepetizerService.listCategoryHomeVideos(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAll(long categoryId) {
        return mEyepetizerService.listCategoryAllVideos(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAuthor(long categoryId) {
        return mEyepetizerService.listCategoryAuthorVideos(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAlbum(long categoryId) {
        return mEyepetizerService.listCategoryAlbumVideos(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listHot(Map map) {
        return mEyepetizerService.listHotVideos(map);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listGroupByCategory(Map map) {
        return mEyepetizerService.listVideosGroupByCategory(map);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listSubscription(Map queryMap) {
        return mEyepetizerService.listSubscriptionVideos(queryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listBySearch(String queryStr, Map pageQueryMap) {
        return mEyepetizerService.listVideoBySearch(queryStr, pageQueryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRank(String rankStrategy, Map pageQueryMap) {
        return mEyepetizerService.listRank(rankStrategy, pageQueryMap);
    }

    @Override
    public Flowable<List<WatchRecord>> listWatchRecord(String userId) {
        return null;
    }

    @Override
    public Completable insert(WatchRecord watchRecord) {
        return null;
    }
}
