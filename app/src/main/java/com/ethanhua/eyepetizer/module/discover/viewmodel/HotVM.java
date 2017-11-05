package com.ethanhua.eyepetizer.module.discover.viewmodel;

import android.text.TextUtils;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetHotDataCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by ethanhua on 2017/9/20.
 */

public class HotVM extends AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {
    private final GetHotDataCase mGetHotDataCase;

    @Inject
    public HotVM(GetHotDataCase getHotDataCase) {
        this.mGetHotDataCase = getHotDataCase;
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (ItemData.TYPE_FOLLOW_CARD.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideoList(itemData);
        }
        if (ItemData.TYPE_BANNER_COLLECTION.equals(itemData.type)) {
            return VideoBannerVM.mapFrom(itemData.data);
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

    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mGetHotDataCase.execute(map);
    }
}
