package com.ethanhua.domain.respository;

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

public interface VideoRepository {

    /**
     * 获取主页的视频列表资源
     *
     * @param pageQueryMap
     * @return
     */
    Single<HomeData> listHome(Map pageQueryMap);

    /**
     * 获取与该视频类似的视频资源列表
     *
     * @param videoId 视频id
     * @return
     */
    Single<ListData<ItemData<VideoData>>> listRelated(long videoId);

    /**
     * 获取某个类别下的主页tab视频资源列表
     *
     * @param categoryId 类别id
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listCategoryHome(long categoryId);

    /**
     * 获取某个类别下的全部tab视频资源列表
     *
     * @param categoryId 类别id
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listCategoryAll(long categoryId);

    /**
     * 获取某个类别下的作者tab视频资源列表
     *
     * @param categoryId 类别id
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listCategoryAuthor(long categoryId);

    /**
     * 获取某个类别下的专辑tab视频资源列表
     *
     * @param categoryId 类别id
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listCategoryAlbum(long categoryId);

    /**
     * 获取热门视频资源列表
     *
     * @param pageQueryMap
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listHot(Map pageQueryMap);

    /**
     * 获取按类别分组的视频资源列表
     *
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listGroupByCategory(Map pageQueryMap);

    /**
     * 获取关注订阅的视频资源列表
     *
     * @param pageQueryMap
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listSubscription(Map pageQueryMap);


    /**
     * 搜索带查询字符串的视频资源列表
     *
     * @param queryStr
     * @param pageQueryMap
     * @return
     */
    Single<ListData<ItemData<VideoListData>>> listBySearch(String queryStr, Map pageQueryMap);


    /**
     * 获取指定排行策略的视频资源列表
     *
     * @param rankStrategy 排行策略（weekly/monthly/historical）
     * @param pageQueryMap
     * @return
     */
    Single<ListData<ItemData<VideoData>>> listRank(String rankStrategy, Map pageQueryMap);

    /**
     * 获取该用户的视频观看历史记录
     *
     * @param userId
     * @return
     */
    Flowable<List<WatchRecord>> listWatchHistory(String userId);

    /**
     * 插入一条用户的视频观看记录
     *
     * @param watchRecord
     * @return
     */
    Completable insert(WatchRecord watchRecord);
}
