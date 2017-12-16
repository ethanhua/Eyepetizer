package com.ethanhua.data.datasource.local;

import android.content.Context;

import com.ethanhua.data.datasource.VideoDataStore;
import com.ethanhua.data.datasource.local.database.AppDataBase;
import com.ethanhua.data.datasource.local.database.WatchHistoryRecord;
import com.ethanhua.data.datasource.local.database.WatchRecordMapper;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.domain.model.WatchRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/12/16.
 */

public class LocalVideoDataStore implements VideoDataStore {
    private Context mContext;

    public LocalVideoDataStore(Context context) {
        mContext = context;
    }

    @Override
    public Single<HomeData> listHome(Map queryMap) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRelated(long videoId) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryHome(long categoryId) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAll(long categoryId) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAuthor(long categoryId) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listCategoryAlbum(long categoryId) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listHot(Map queryMap) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listGroupByCategory(Map queryMap) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listSubscription(Map queryMap) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoListData>>> listBySearch(String queryStr, Map pageQueryMap) {
        return null;
    }

    @Override
    public Single<ListData<ItemData<VideoData>>> listRank(String rankStrategy, Map pageQueryMap) {
        return null;
    }

    @Override
    public Flowable<List<WatchRecord>> listWatchHistory(String userId) {
        return AppDataBase.getInstance(mContext)
                .watchHistoryRecordDao()
                .list(userId)
                .map(watchHistoryRecords -> {
                    List<WatchRecord> watchRecords = new ArrayList<>();
                    for (WatchHistoryRecord record : watchHistoryRecords) {
                        watchRecords.add(WatchRecordMapper.from(record));
                    }
                    return watchRecords;
                });
    }

}
