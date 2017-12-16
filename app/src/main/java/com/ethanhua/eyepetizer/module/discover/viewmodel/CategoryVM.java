package com.ethanhua.eyepetizer.module.discover.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetVideoListGroupByCategoryCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO_COLLECTION_HZ_CARD;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class CategoryVM extends AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {
    private final GetVideoListGroupByCategoryCase mGetVideoListGroupByCategoryCase;

    @Inject
    public CategoryVM(GetVideoListGroupByCategoryCase getVideoListGroupByCategoryCase) {
        this.mGetVideoListGroupByCategoryCase = getVideoListGroupByCategoryCase;
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (TYPE_VIDEO_COLLECTION_HZ_CARD.equals(itemData.type) && itemData.data != null) {
            return VideoListHzScrollCardVM.mapFrom(itemData.data);
        }
        return null;
    }

    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mGetVideoListGroupByCategoryCase.execute(map);
    }
}
