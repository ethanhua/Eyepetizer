package com.ethanhua.eyepetizer.viewmodel;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.ethanhua.eyepetizer.module.search.SearchActivity;
import com.ethanhua.eyepetizer.module.video.VideoDetailsActivity;
import com.ethanhua.eyepetizer.module.video.viewmodel.VideoBaseVM;

/**
 * Created by ethanhua on 2017/9/21.
 */

public class NavigationViewModel {

    private static final String ACTION_VIEW = "com.ethanhua.eyepetizer.action.VIEW";

    public static void start(View view, String actionUrl) {
        Intent intent = new Intent(ACTION_VIEW, Uri.parse(actionUrl));
        try {
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("exception", "not found" + actionUrl + "activity");
        }
    }

    public static void startVideoDetailsOnAnim(View view, VideoBaseVM videoBaseVM) {
        VideoDetailsActivity.animActionStart(view, videoBaseVM);
    }

    public static void startVideoDetails(View view, VideoBaseVM videoBaseVM) {
        VideoDetailsActivity.actionStart(view.getContext(), videoBaseVM);
    }

    public static void startSearch(View view) {
        Intent intent = new Intent(view.getContext(), SearchActivity.class);
        view.getContext().startActivity(intent);
    }

}
