package com.ethanhua.commonlib.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ethanhua on 2017/10/3.
 */

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public GridSpaceItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
            int position = parent.getChildAdapterPosition(view);
            if (spanSizeLookup.getSpanSize(position) == 1) { //一行多列
                int index = spanSizeLookup.getSpanIndex(position, gridLayoutManager.getSpanCount());
                if (index > 0) { //除每行的第一个元素外
                    outRect.left = mSpace;
                }
            }
            outRect.bottom = mSpace;
        }
    }
}
