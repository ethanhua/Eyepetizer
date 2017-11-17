package com.ethanhua.eyepetizer.di.components;

import android.app.Activity;

import com.ethanhua.eyepetizer.di.PerActivity;
import com.ethanhua.eyepetizer.di.modules.ActivityModule;
import com.ethanhua.eyepetizer.module.category.CategoryActivity;
import com.ethanhua.eyepetizer.module.category.CategoryListActivity;
import com.ethanhua.eyepetizer.module.search.SearchActivity;
import com.ethanhua.eyepetizer.module.video.VideoDetailsActivity;

import dagger.Component;

/**
 * Created by ethanhua on 2017/9/16.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(VideoDetailsActivity videoDetailsActivity);

    void inject(CategoryListActivity categoryListActivity);

    void inject(CategoryActivity categoryActivity);

    void inject(SearchActivity searchActivity);

}
