package com.ethanhua.eyepetizer.di.modules;

import android.app.Activity;

import com.ethanhua.commonlib.media.IMediaController;
import com.ethanhua.commonlib.media.MediaController;
import com.ethanhua.eyepetizer.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ethanhua on 2017/9/16.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @PerActivity
    @Provides
    IMediaController provideMediaController() {
        return new MediaController(mActivity);
    }
}
