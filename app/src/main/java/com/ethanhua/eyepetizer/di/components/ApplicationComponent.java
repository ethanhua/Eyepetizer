package com.ethanhua.eyepetizer.di.components;

import android.app.Application;
import android.databinding.DataBindingComponent;

import com.danikula.videocache.HttpProxyCacheServer;
import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.respository.CategoryRepository;
import com.ethanhua.domain.respository.CommentRepository;
import com.ethanhua.domain.respository.HotSearchTagRepository;
import com.ethanhua.domain.respository.VideoRepository;
import com.ethanhua.eyepetizer.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ethanhua on 2017/9/16.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application getApplication();

    VideoRepository getVideoRepository();

    CommentRepository getCommentRepository();

    CategoryRepository getCategoryRepository();

    HotSearchTagRepository getHotSearchTagRepository();

    ThreadTransformer getThreadTransformer();

    HttpProxyCacheServer getHttpProxyCacheServer();

    DataBindingComponent getDefaultDataBindingComponent();
}
