package com.ethanhua.eyepetizer.viewmodel;

import android.databinding.ObservableBoolean;
import android.net.Uri;
import android.text.TextUtils;

import com.ethanhua.commonlib.viewmodel.ViewModel;
import com.ethanhua.commonlib.widget.recyclerview.Pageable;
import com.ethanhua.commonlib.widget.recyclerview.Refreshable;
import com.ethanhua.domain.model.ItemData;
import com.ethanhua.domain.model.ListData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Single;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by ethanhua on 2017/9/15.
 */

public abstract class AbsListViewModel<E, T extends ListData<ItemData<E>>>
        extends LiveViewModel
        implements Refreshable, Pageable {

    protected static final String QUERY_KEY_REFRESH = "lastStartId";
    private int mLastStartId;
    private String mNextPageUrl;
    private final ObservableItems mListData = new ObservableItems();
    private final MultiTypeAdapter mAdapter = new MultiTypeAdapter(mListData);
    private final ObservableBoolean mIsLoading = new ObservableBoolean();

    protected AbsListViewModel() {
        init();
    }

    private void init() {
        mListData.bindToAdapter(mAdapter);
    }

    protected abstract Single<T> provideSource(Map map);

    protected abstract ViewModel convertItem(ItemData<E> itemData);

    public ObservableItems getListData() {
        return mListData;
    }

    public MultiTypeAdapter getAdapter() {
        return mAdapter;
    }

    public ObservableBoolean isLoading() {
        return mIsLoading;
    }

    protected void loadData() {
        provideSource(getPageQueryMap())
                .compose(bindToLifecycle())
                .subscribe(listData -> {
                    mIsLoading.set(false);
                    if (!isNextPageRequest()) {
                        getListData().clear();
                    }
                    for (ItemData itemData : listData.itemList) {
                        ViewModel viewModel = convertItem(itemData);
                        if (viewModel != null) {
                            getListData().add(viewModel);
                        }
                    }
                    mLastStartId = listData.lastStartId;
                    mNextPageUrl = listData.nextPageUrl;
                }, throwable -> {
                    mIsLoading.set(false);
                    throwable.printStackTrace();
                });
    }

    @Override
    public void refresh() {
        mNextPageUrl = "";
        mIsLoading.set(true);
        loadData();
    }

    @Override
    public void nextPage() {
        if (mIsLoading.get() || TextUtils.isEmpty(mNextPageUrl)) {
            return;
        }
        mIsLoading.set(true);
        loadData();
    }

    private Map getPageQueryMap() {
        HashMap map = new HashMap();
        if (isNextPageRequest()) {
            Uri uri = Uri.parse(mNextPageUrl);
            if (uri != null) {
                Set<String> set = uri.getQueryParameterNames();
                for (String item : set) {
                    map.put(item, uri.getQueryParameter(item));
                }
            }
        } else {
            map.put(QUERY_KEY_REFRESH, mLastStartId);
        }
        return map;
    }

    private boolean isNextPageRequest() {
        return !TextUtils.isEmpty(mNextPageUrl);
    }
}
