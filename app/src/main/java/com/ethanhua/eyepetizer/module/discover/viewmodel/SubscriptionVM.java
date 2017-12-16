package com.ethanhua.eyepetizer.module.discover.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetSubscriptionDataCase;
import com.ethanhua.domain.model.HomeData;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;


/**
 * Created by ethanhua on 2017/9/18.
 */

public class SubscriptionVM extends AbsListViewModel<VideoListData, HomeData> {
    private final GetSubscriptionDataCase mGetSubscriptionDataCase;


    @Inject
    public SubscriptionVM(GetSubscriptionDataCase getSubscriptionDataCase) {
        super();
        mGetSubscriptionDataCase = getSubscriptionDataCase;
    }

    @Override
    protected Single<HomeData> provideSource(Map map) {
        return mGetSubscriptionDataCase.execute(map);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (ItemData.TYPE_VIDEO_COLLECTION_BRIEF.equals(itemData.type)) {
            return VideoListBriefVM.mapFrom(itemData.data);
        }
        if (ItemData.TYPE_FOLLOW_CARD.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideoList(itemData);
        }
        if (ItemData.TYPE_VIDEO_COLLECTION_HZ_CARD.equals(itemData.type)) {
            return VideoListHzScrollCardVM.mapFrom(itemData.data);
        }
        return null;
    }
}
