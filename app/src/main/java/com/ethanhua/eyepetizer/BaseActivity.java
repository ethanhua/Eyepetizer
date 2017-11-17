package com.ethanhua.eyepetizer;

import android.support.v7.app.AppCompatActivity;

import com.ethanhua.eyepetizer.di.components.AppComponent;
import com.ethanhua.eyepetizer.di.modules.ActivityModule;

/**
 * Created by ethanhua on 2017/9/17.
 */

public class BaseActivity extends AppCompatActivity{

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link AppComponent}
     */
    protected AppComponent getAppComponent() {
        return ((EyeApplication) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link com.ethanhua.eyepetizer.di.modules.ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
