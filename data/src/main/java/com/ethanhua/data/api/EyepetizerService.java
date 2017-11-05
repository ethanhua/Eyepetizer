package com.ethanhua.data.api;


import com.ethanhua.domain.model.Category;
import com.ethanhua.domain.model.CategoryInfoData;
import com.ethanhua.domain.model.Comment;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ethanhua on 2017/9/14.
 */

public interface EyepetizerService {

    @GET("api/v4/tabs/selected")
    Single<HomeData> listHomeVideo(@QueryMap Map<String, String> queryMap);

    @GET("api/v4/tabs/follow")
    Single<ListData<ItemData<VideoListData>>> listSubscriptionVideos(@QueryMap Map<String, String> queryMap);

    @GET("api/v5/discovery/square")
    Single<ListData<ItemData<VideoListData>>> listHotVideos(@QueryMap Map<String, String> queryMap);

    @GET("api/v4/discovery/category")
    Single<ListData<ItemData<VideoListData>>> listVideosGroupByCategory(@QueryMap Map<String, String> queryMap);

    @GET("api/v4/categories/detail/index")
    Single<ListData<ItemData<VideoListData>>> listCategoryHomeVideos(@Query("id") long categoryId);

    @GET("api/v4/categories/detail/pgcs")
    Single<ListData<ItemData<VideoListData>>> listCategoryAuthorVideos(@Query("id") long categoryId);

    @GET("api/v4/categories/detail/playlist")
    Single<ListData<ItemData<VideoListData>>> listCategoryAlbumVideos(@Query("id") long categoryId);

    @GET("api/v4/categories/videoList")
    Single<ListData<ItemData<VideoListData>>> listCategoryAllVideos(@Query("id") long categoryId);

    @GET("api/v4/video/related")
    Single<ListData<ItemData<VideoData>>> listRelatedVideos(@Query("id") long videoId);

    @GET("api/v2/replies/video")
    Single<ListData<ItemData<Comment>>> listVideoComments(@Query("videoId") long videoId,
                                                          @QueryMap Map<String, String> queryMap);

    @GET("api/v2/replies/video")
    Single<ListData<ItemData<Comment>>> listCommentConversation(@Query("replyId") long commentId);

    @GET("api/v4/categories/all")
    Single<ListData<ItemData<Category>>> listAllCategory();

    @GET("api/v4/categories/detail/tab")
    Single<CategoryInfoData> getCategoryInfo(@Query("id") long categoryId);

    @GET("api/v1/search")
    Single<ListData<ItemData<VideoListData>>> listVideoBySearch(@Query("query") String queryStr,
                                                            @QueryMap Map<String, String> queryMap);

    @GET("api/v3/queries/hot")
    Single<List<String>> listHotQueryTag();

    @GET("api/v4/rankList/videos")
    Single<ListData<ItemData<VideoData>>> listRank(@Query("strategy") String strategy,
                                                   @QueryMap Map<String, String> queryMap);
}
