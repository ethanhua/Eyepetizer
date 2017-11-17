package com.ethanhua.eyepetizer.di.modules;

import android.app.Activity;

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

}
