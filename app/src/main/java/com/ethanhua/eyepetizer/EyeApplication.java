package com.ethanhua.eyepetizer;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.ethanhua.eyepetizer.di.components.AppComponent;
import com.ethanhua.eyepetizer.di.components.DaggerAppComponent;
import com.ethanhua.eyepetizer.di.modules.AppModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ethanhua on 2017/9/13.
 */

public class EyeApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        DataBindingUtil.setDefaultComponent(mAppComponent.getDefaultDataBindingComponent());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initInjector() {
        this.mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }
}
