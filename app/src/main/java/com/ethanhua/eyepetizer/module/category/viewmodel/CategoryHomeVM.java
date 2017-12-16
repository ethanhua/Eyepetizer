package com.ethanhua.eyepetizer.module.category.viewmodel;

import android.text.TextUtils;

import com.ethanhua.domain.interactor.GetCategoryHomeVideosCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.eyepetizer.module.discover.viewmodel.AuthorItemVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.TextVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListHzScrollCardVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/10/4.
 */

public class CategoryHomeVM extends
        AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {

    private final GetCategoryHomeVideosCase mGetCategoryHomeVideosCase;
    private long categoryId;

    @Inject
    public CategoryHomeVM(GetCategoryHomeVideosCase getCategoryHomeVideosCase) {
        this.mGetCategoryHomeVideosCase = getCategoryHomeVideosCase;
    }


    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mGetCategoryHomeVideosCase.execute(categoryId);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (ItemData.TYPE_FOLLOW_CARD.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideoList(itemData);
        }
        if (ItemData.TYPE_BANNER_COLLECTION.equals(itemData.type)) {
            return VideoListHzScrollCardVM.mapFrom(itemData.data);
        }
        if (ItemData.TYPE_SQUARE_CARD_COLLECTION.equals(itemData.type)) {
            return SquareListVM.mapFrom(itemData.data);
        }
        if (ItemData.TYPE_AUTHOR.equals(itemData.type)) {
            return AuthorItemVM.mapFrom(itemData.data);
        }
        if (ItemData.TYPE_TEXT.equals(itemData.type)
                && !TextUtils.isEmpty(itemData.data.text)) {
            return new TextVM(itemData.data.text);
        }
        return null;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
