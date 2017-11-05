package com.ethanhua.data.repository;

import com.ethanhua.data.datasource.VideoDataStoreFactory;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.domain.respository.VideoRepository;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/27.
 */

public class VideoDataRepository implements VideoRepository {
    private final VideoDataStoreFactory mVideoDataStoreFactory;

    @Inject
    public VideoDataRepository(VideoDataStoreFactory videoDataStoreFactory) {
        this.mVideoDataStoreFactory = videoDataStoreFactory;
    }

    @Override
    public Single<HomeData> listHome(Map pageQueryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listHome(pageQueryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRelated(long videoId) {
        return mVideoDataStoreFactory.createCloudDataStore().listRelated(videoId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryHome(long categoryId) {
        return mVideoDataStoreFactory.createCloudDataStore().listCategoryHome(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAll(long categoryId) {
        return mVideoDataStoreFactory.createCloudDataStore().listCategoryAll(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAuthor(long categoryId) {
        return mVideoDataStoreFactory.createCloudDataStore().listCategoryAuthor(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAlbum(long categoryId) {
        return mVideoDataStoreFactory.createCloudDataStore().listCategoryAlbum(categoryId);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listHot(Map queryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listHot(queryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listGroupByCategory(Map queryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listGroupByCategory(queryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listSubscription(Map queryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listSubscription(queryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listBySearch(String queryStr, Map pageQueryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listBySearch(queryStr, pageQueryMap);
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRank(String rankStrategy, Map pageQueryMap) {
        return mVideoDataStoreFactory.createCloudDataStore().listRank(rankStrategy, pageQueryMap);
    }
}
