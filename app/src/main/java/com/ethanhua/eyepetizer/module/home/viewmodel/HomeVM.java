package com.ethanhua.eyepetizer.module.home.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetHomeDataCase;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ethanhua.domain.model.ItemData.TYPE_FOLLOW_CARD;
import static com.ethanhua.domain.model.ItemData.TYPE_SQUARE_CARD_COLLECTION;
import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO_COLLECTION_COVER;

/**
 * Created by ethanhua on 2017/9/15.
 */

public class HomeVM extends AbsListViewModel<VideoListData, HomeData> {

    private final GetHomeDataCase mGetHomeDataCase;

    @Inject
    public HomeVM(GetHomeDataCase getHomeDataCase) {
        super();
        this.mGetHomeDataCase = getHomeDataCase;
    }

    @Override
    protected Single<HomeData> provideSource(Map map) {
        return mGetHomeDataCase.execute(map)
                .doAfterSuccess(homeData -> {
                    if (map.get(QUERY_KEY_REFRESH) != null) {
                        getListData().add(0, new HomeTopVM(homeData.topIssue.data));
                    }
                });
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> homeItem) {
        if (TYPE_FOLLOW_CARD.equals(homeItem.type)) {
            return VideoBaseVM.mapFromVideoList(homeItem);
        }
        if (TYPE_VIDEO_COLLECTION_COVER.equals(homeItem.type)) {
            return new VideoListVM(homeItem.data);
        }
        if (TYPE_SQUARE_CARD_COLLECTION.equals(homeItem.type)) {
            return SquareListVM.mapFrom(homeItem.data);
        }
        return null;
    }
}
