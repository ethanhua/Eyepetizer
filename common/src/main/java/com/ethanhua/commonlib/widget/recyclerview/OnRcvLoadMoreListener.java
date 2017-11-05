package com.ethanhua.commonlib.widget.recyclerview;

import android.support.v7.widget.RecyclerView;

import static com.ethanhua.commonlib.widget.recyclerview.LayoutManagerHelper.getLastVisibleItemPosition;

/**
 * Created by ethanhua on 2017/10/13.
 */

public class OnRcvLoadMoreListener extends RecyclerView.OnScrollListener {

    private static final int mAheadLoadMoreNum = 4;//默认提前4条开始加载更多

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (recyclerView != null
                && recyclerView.getAdapter() != null
                && recyclerView.getLayoutManager() != null) {
            int totalItemCount = recyclerView.getAdapter().getItemCount();
            int lastVisibleItemPosition = getLastVisibleItemPosition(recyclerView.getLayoutManager());
            //1. !isLoadingMore 不是正在加载中
            //2. dy > 0 手指向上滑动
            //3. totalItemCount > 0 总条目数大于0
            //4. lastVisibleItemPosition + mAheadLoadMoreNum
            //    >= totalItemCount - 1 最后可见条目 + 提前加载数目 >= 总条目
            if (dy > 0
                    && totalItemCount > 0
                    && lastVisibleItemPosition + mAheadLoadMoreNum >= totalItemCount - 1) {
                onLoadMore();
            }
        }

    }

    public void onLoadMore() {

    }

}
