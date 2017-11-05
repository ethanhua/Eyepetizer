package com.ethanhua.commonlib.widget.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.Arrays;

/**
 * Created by ethanhua on 2017/10/13.
 */

public class LayoutManagerHelper {

    public static int getFirstVisibleItemPosition(LayoutManager layoutManager) {
        int firstVisibleItemPosition = -1;
        if (layoutManager instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findFirstVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            firstVisibleItemPosition = findStaggeredGridItemPosition(
                    (StaggeredGridLayoutManager) layoutManager, true);
        }
        return firstVisibleItemPosition;
    }

    public static int getLastVisibleItemPosition(LayoutManager layoutManager) {
        int lastVisibleItemPosition = -1;
        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                    .findLastVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            lastVisibleItemPosition = findStaggeredGridItemPosition(
                    (StaggeredGridLayoutManager) layoutManager, false);
        }
        return lastVisibleItemPosition;
    }

    private static int findStaggeredGridItemPosition(StaggeredGridLayoutManager layoutManager,
                                                     boolean findFirst) {
        int[] positions = new int[layoutManager.getSpanCount()];
        if (findFirst) {
            layoutManager.findFirstVisibleItemPositions(positions);
            Arrays.sort(positions);
            return positions[0];
        } else {
            layoutManager.findLastVisibleItemPositions(positions);
            Arrays.sort(positions);
            return positions[positions.length - 1];
        }
    }
}
