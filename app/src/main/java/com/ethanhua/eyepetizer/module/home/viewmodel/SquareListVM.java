package com.ethanhua.eyepetizer.module.home.viewmodel;

import android.databinding.ObservableField;

import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.VideoData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;
import com.ethanhua.commonlib.viewmodel.ViewModel;

import java.util.Map;

import io.reactivex.Single;

/**
 * Creed by ethanhua on 2017/9/18.
 */

public class SquareListVM extends
        AbsListViewModel<VideoData, VideoListData> {
    public String actionUrl;
    private final VideoListData listData;
    public final ObservableField<String> title = new ObservableField<>();

    public SquareListVM(VideoListData listData) {
        this.listData = listData;
        loadData();
    }

    @Override
    protected Single<VideoListData> provideSource(Map map) {
        return Single.just(listData);
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoData> itemData) {
        if (ItemData.TYPE_SQUARE_CARD.equals(itemData.type)) {
            SquareItemVM squareItemVM = new SquareItemVM();
            squareItemVM.imageUrl.set(itemData.data.image);
            squareItemVM.title.set(itemData.data.title);
            squareItemVM.actionUrl = itemData.data.actionUrl;
            return squareItemVM;
        }
        if (ItemData.TYPE_ACTION_CARD.equals(itemData.type)) {
            ActionCardVM actionCardVM = new ActionCardVM();
            actionCardVM.text.set(itemData.data.text);
            actionCardVM.actionUrl = itemData.data.actionUrl;
            return actionCardVM;
        }
        return null;
    }

    public static SquareListVM mapFrom(VideoListData listData) {
        SquareListVM squareListVM = new SquareListVM(listData);
        if (listData.header != null) {
            squareListVM.title.set(listData.header.title);
            squareListVM.actionUrl = listData.header.actionUrl;
        }
        return squareListVM;
    }
}
