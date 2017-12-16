package com.ethanhua.eyepetizer.di.components;

import android.app.Application;
import android.content.Context;
import android.databinding.DataBindingComponent;

import com.danikula.videocache.HttpProxyCacheServer;
import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.respository.CategoryRepository;
import com.ethanhua.domain.respository.CommentRepository;
import com.ethanhua.domain.respository.HotSearchTagRepository;
import com.ethanhua.domain.respository.VideoRepository;
import com.ethanhua.eyepetizer.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ethanhua on 2017/9/16.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Application getApplication();

    Context getContext();

    VideoRepository getVideoRepository();

    CommentRepository getCommentRepository();

    CategoryRepository getCategoryRepository();

    HotSearchTagRepository getHotSearchTagRepository();

    ThreadTransformer getThreadTransformer();

    HttpProxyCacheServer getHttpProxyCacheServer();

    DataBindingComponent getDefaultDataBindingComponent();
}
