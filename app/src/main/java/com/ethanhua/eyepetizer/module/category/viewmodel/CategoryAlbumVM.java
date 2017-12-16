package com.ethanhua.eyepetizer.module.category.viewmodel;

import com.ethanhua.domain.interactor.GetCategoryAlbumVideosCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListBriefVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryAlbumVM extends
        AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {

    private final GetCategoryAlbumVideosCase mGetCategoryAlbumVideosCase;
    private long categoryId;

    @Inject
    public CategoryAlbumVM(GetCategoryAlbumVideosCase getCategoryAlbumVideosCase) {
        this.mGetCategoryAlbumVideosCase = getCategoryAlbumVideosCase;
    }

    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mGetCategoryAlbumVideosCase.execute(categoryId);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (ItemData.TYPE_VIDEO_COLLECTION_BRIEF.equals(itemData.type)) {
            return VideoListBriefVM.mapFrom(itemData.data);
        }
        return null;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
