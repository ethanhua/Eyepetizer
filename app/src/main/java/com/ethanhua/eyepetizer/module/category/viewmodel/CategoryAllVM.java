package com.ethanhua.eyepetizer.module.category.viewmodel;

import com.ethanhua.domain.interactor.GetCategoryAllVideosCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryAllVM extends
        AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {
    private final GetCategoryAllVideosCase mGetCategoryAllVideosCase;

    private long categoryId;

    @Inject
    public CategoryAllVM(GetCategoryAllVideosCase getCategoryAllVideosCase) {
        this.mGetCategoryAllVideosCase = getCategoryAllVideosCase;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mGetCategoryAllVideosCase.execute(categoryId);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (ItemData.TYPE_FOLLOW_CARD.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideoList(itemData);
        }
        return null;
    }
}
