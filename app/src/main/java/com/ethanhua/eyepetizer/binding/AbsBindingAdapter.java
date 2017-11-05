package com.ethanhua.eyepetizer.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ethanhua.commonlib.media.IjkVideoViewEx;
import com.ethanhua.commonlib.widget.recyclerview.OnRcvLoadMoreListener;
import com.ethanhua.commonlib.widget.recyclerview.Pageable;
import com.ethanhua.commonlib.widget.recyclerview.Refreshable;

import static android.databinding.adapters.SearchViewBindingAdapter.OnQueryTextChange;
import static android.databinding.adapters.SearchViewBindingAdapter.OnQueryTextSubmit;
import static android.support.v7.widget.SearchView.OnQueryTextListener;

/**
 * Created by ethanhua on 2017/10/25.
 */
public abstract class AbsBindingAdapter {

    @BindingAdapter("imageUrl")
    public abstract void bindImage(ImageView imageView,
                                   String url);

    @BindingAdapter("background")
    public abstract void bindBackground(View view,
                                   String url);


    @BindingAdapter("coverImageUrl")
    public abstract void bindVideoCoverImage(IjkVideoViewEx ijkVideoViewEx,
                                             String url);

    @BindingAdapter("android:text")
    public void setText(TextView textView,
                        int value) {
        textView.setText(Integer.toString(value));
    }

    @BindingAdapter({"onRefresh"})
    public void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout,
                                 final Refreshable refreshable) {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (refreshable != null) {
                refreshable.refresh();
            }
        });
    }

    @BindingAdapter({"onLoadMore"})
    public void onLoadMoreCommand(RecyclerView recyclerView,
                                  final Pageable pageable) {
        recyclerView.addOnScrollListener(new OnRcvLoadMoreListener() {
            @Override
            public void onLoadMore() {
                super.onLoadMore();
                pageable.nextPage();
            }
        });
    }

    @BindingAdapter("query")
    public void setSearchText(SearchView searchView, CharSequence text) {
        final CharSequence oldText = searchView.getQuery();
        if (text == oldText || (text == null && oldText.length() == 0)) {
            return;
        }
        searchView.setQuery(text, false);
    }


    @InverseBindingAdapter(attribute = "query")
    public static String getSearchText(SearchView searchView) {
        return searchView.getQuery().toString();
    }

    @BindingAdapter(value = {"onQueryTextSubmit",
            "onQueryTextChange",
            "queryAttrChanged"},
            requireAll = false)
    public void setQueryListener(SearchView searchView,
                                 OnQueryTextSubmit onQueryTextSubmit,
                                 OnQueryTextChange onQueryTextChange,
                                 final InverseBindingListener queryAttrChanged) {

        if (onQueryTextChange == null && onQueryTextSubmit == null) {
            searchView.setOnQueryTextListener(null);
            return;
        }
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (queryAttrChanged != null) {
                    queryAttrChanged.onChange();
                }
                return onQueryTextSubmit != null && onQueryTextSubmit.onQueryTextSubmit(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (queryAttrChanged != null) {
                    queryAttrChanged.onChange();
                }
                return onQueryTextChange != null && onQueryTextChange.onQueryTextChange(newText);
            }
        });

    }
}
