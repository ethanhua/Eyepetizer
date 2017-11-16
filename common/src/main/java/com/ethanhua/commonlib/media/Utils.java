package com.ethanhua.commonlib.media;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;


/**
 * Created by ethanhua on 2017/11/8.
 */

public class Utils {

    public static boolean isFullScreen(Activity activity) {
        int orientation = activity.getResources().getConfiguration().orientation;
        return orientation == ORIENTATION_LANDSCAPE;

    }

    public static void toggleFullScreen(Activity activity) {
        switchFullScreen(activity, !isFullScreen(activity));
    }

    public static void switchFullScreen(Activity activity, boolean fullscreen) {
        activity.setRequestedOrientation(fullscreen ?
                SCREEN_ORIENTATION_LANDSCAPE
                : SCREEN_ORIENTATION_PORTRAIT);
        switchFullScreenFlag(activity, fullscreen);
        showActionBar(activity, !fullscreen);
    }

    /**
     * 隐藏/显示 ActionBar
     *
     * @param show
     */
    public static void showActionBar(Context context, boolean show) {
        if (context instanceof AppCompatActivity) {
            ActionBar supportActionBar = ((AppCompatActivity) context).getSupportActionBar();
            if (supportActionBar != null) {
                if (show) {
                    supportActionBar.show();
                } else {
                    supportActionBar.hide();
                }
            }
        }
    }

    public static void switchFullScreenFlag(Activity activity, boolean fullScreen) {
        if (fullScreen) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
