package com.ethanhua.eyepetizer;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.ethanhua.eyepetizer.di.components.ApplicationComponent;
import com.ethanhua.eyepetizer.di.components.DaggerApplicationComponent;
import com.ethanhua.eyepetizer.di.modules.ApplicationModule;

/**
 * Created by ethanhua on 2017/9/13.
 */

public class EyeApplication extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        DataBindingUtil.setDefaultComponent(mAppComponent.getDefaultDataBindingComponent());
    }

    private void initInjector() {
        this.mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mAppComponent;
    }
}
