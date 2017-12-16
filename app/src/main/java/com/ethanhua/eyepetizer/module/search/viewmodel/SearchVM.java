package com.ethanhua.eyepetizer.module.search.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.domain.interactor.GetHotSearchTagCase;
import com.ethanhua.domain.interactor.SearchVideoCase;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;
import com.ethanhua.domain.model.VideoListData;
import com.ethanhua.eyepetizer.module.home.viewmodel.SquareListVM;
import com.ethanhua.eyepetizer.module.home.viewmodel.VideoListVM;
import com.ethanhua.eyepetizer.module.search.viewadapter.SearchTagAdapter;
import com.ethanhua.eyepetizer.module.discover.viewmodel.VideoListBriefVM;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;
import com.ethanhua.eyepetizer.viewmodel.AbsListViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

import static com.ethanhua.domain.model.ItemData.TYPE_SQUARE_CARD_COLLECTION;
import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO;
import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO_COLLECTION_BRIEF;
import static com.ethanhua.domain.model.ItemData.TYPE_VIDEO_COLLECTION_COVER;

/**
 * Created by ethanhua on 2017/10/24.
 */

public class SearchVM extends
        AbsListViewModel<VideoListData, ListData<ItemData<VideoListData>>> {

    public final ObservableField<String> searchString = new ObservableField<>();
    public final ObservableField<String> resultCountString = new ObservableField<>();
    public final ObservableBoolean showQueryTag = new ObservableBoolean(true);
    private final SearchTagAdapter mSearchTagAdapter;
    private final GetHotSearchTagCase mHotSearchTagCase;
    private final SearchVideoCase mSearchVideoCase;

    @Inject
    public SearchVM(GetHotSearchTagCase hotSearchTagCase,
                    SearchVideoCase searchVideoCase) {
        this.mHotSearchTagCase = hotSearchTagCase;
        this.mSearchVideoCase = searchVideoCase;
        mSearchTagAdapter = new SearchTagAdapter();
        mSearchTagAdapter.setItemClickListener(s -> {
            searchString.set(s);
            search();
        });
    }

    public void loadHotSearchTags() {
        mHotSearchTagCase.execute()
                .compose(bindToLifecycle())
                .subscribe(list -> mSearchTagAdapter.replace(list),
                        Throwable::printStackTrace);
    }

    @Override
    protected Single<ListData<ItemData<VideoListData>>> provideSource(Map map) {
        return mSearchVideoCase.execute(map.get("query") == null ? searchString.get() : "", map)
                .doOnSubscribe(disposable -> showQueryTag.set(false))
                .doOnSuccess(itemDataListData ->
                        resultCountString.set("「 "
                                + searchString.get()
                                + " 」"
                                + "搜索结果共"
                                + itemDataListData.total
                                + "个"));
    }

    @Override
    protected ViewModel convertItem(ItemData<VideoListData> itemData) {
        if (TYPE_VIDEO_COLLECTION_BRIEF.equals(itemData.type)) {
            return VideoListBriefVM.mapFrom(itemData.data);
        }
        if (TYPE_VIDEO.equals(itemData.type)) {
            return VideoBaseVM.mapFromVideoList(itemData);
        }
        if (TYPE_VIDEO_COLLECTION_COVER.equals(itemData.type)) {
            return new VideoListVM(itemData.data);
        }
        if (TYPE_SQUARE_CARD_COLLECTION.equals(itemData.type)) {
            return SquareListVM.mapFrom(itemData.data);
        }
        return null;
    }

    public boolean search() {
        refresh();
        return false;
    }

    public SearchTagAdapter getSearchTagAdapter() {
        return mSearchTagAdapter;
    }
}
