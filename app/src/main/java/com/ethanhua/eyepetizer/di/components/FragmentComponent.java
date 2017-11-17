package com.ethanhua.eyepetizer.di.components;

import android.support.v4.app.Fragment;

import com.ethanhua.eyepetizer.BaseFragment;
import com.ethanhua.eyepetizer.di.PerActivity;
import com.ethanhua.eyepetizer.di.modules.FragmentModule;
import com.ethanhua.eyepetizer.module.category.CategoryAlbumFragment;
import com.ethanhua.eyepetizer.module.category.CategoryAllFragment;
import com.ethanhua.eyepetizer.module.category.CategoryAuthorFragment;
import com.ethanhua.eyepetizer.module.category.CategoryHomeFragment;
import com.ethanhua.eyepetizer.module.discover.CategoryFragment;
import com.ethanhua.eyepetizer.module.discover.HotFragment;
import com.ethanhua.eyepetizer.module.home.HomeFragment;
import com.ethanhua.eyepetizer.module.ranklist.RankListFragment;
import com.ethanhua.eyepetizer.module.subscribe.SubscriptionFragment;
import com.ethanhua.eyepetizer.module.video.VideoCommentsFragment;

import dagger.Component;

/**
 * Created by ethanhua on 2017/9/16.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    Fragment getFragment();

    void inject(BaseFragment fragment);

    void inject(VideoCommentsFragment videoCommentsFragment);

    void inject(CategoryHomeFragment categoryHomeFragment);

    void inject(CategoryAllFragment categoryAllFragment);

    void inject(CategoryAuthorFragment categoryAuthorFragment);

    void inject(CategoryAlbumFragment categoryAlbumFragment);

    void inject(RankListFragment rankListFragment);

    void inject(HomeFragment homeFragment);

    void inject(SubscriptionFragment subscriptionFragment);

    void inject(HotFragment hotFragment);

    void inject(CategoryFragment hotFragment);
}
