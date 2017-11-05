package com.ethanhua.eyepetizer.di.modules;

import android.app.Application;
import android.databinding.DataBindingComponent;
import android.os.Build;

import com.danikula.videocache.HttpProxyCacheServer;
import com.ethanhua.data.api.EyepetizerService;
import com.ethanhua.data.repository.CategoryDataRepository;
import com.ethanhua.data.repository.CommentDataRepository;
import com.ethanhua.data.repository.HotSearchTagDataRepository;
import com.ethanhua.data.repository.VideoDataRepository;
import com.ethanhua.domain.ThreadTransformer;
import com.ethanhua.domain.respository.CategoryRepository;
import com.ethanhua.domain.respository.CommentRepository;
import com.ethanhua.domain.respository.HotSearchTagRepository;
import com.ethanhua.domain.respository.VideoRepository;
import com.ethanhua.eyepetizer.EyeApplication;
import com.ethanhua.eyepetizer.binding.DefaultBindingComponent;
import com.ethanhua.eyepetizer.execution.IOExecution;
import com.ethanhua.eyepetizer.execution.UIExecution;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ethanhua on 2017/9/16.
 */
@Module
public class ApplicationModule {

    private final EyeApplication application;

    public ApplicationModule(EyeApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    VideoRepository provideVideoRepository(VideoDataRepository videoDataRepository) {
        return videoDataRepository;
    }

    @Provides
    @Singleton
    CommentRepository provideCommentRepository(CommentDataRepository commentDataRepository) {
        return commentDataRepository;
    }

    @Provides
    @Singleton
    CategoryRepository provideCategoryRepository(CategoryDataRepository categoryDataRepository) {
        return categoryDataRepository;
    }

    @Provides
    @Singleton
    HotSearchTagRepository provideHotSearchTagRepository(
            HotSearchTagDataRepository hotSearchTagDataRepository) {
        return hotSearchTagDataRepository;
    }

    @Provides
    @Singleton
    ThreadTransformer provideThreadTransformer(IOExecution ioExecutionThread,
                                               UIExecution uiExecutionThread
    ) {
        return new ThreadTransformer(ioExecutionThread, uiExecutionThread);
    }

    @Provides
    @Singleton
    HttpProxyCacheServer provideHttpProxyCacheServer() {
        return new HttpProxyCacheServer(application.getApplicationContext());
    }

    @Provides
    @Singleton
    DataBindingComponent provideDefaultDataBindingComponent() {
        return new DefaultBindingComponent();
    }

    @Provides
    @Singleton
    EyepetizerService provideEyepetizerService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("udid", "9bd9bfce224443059bb80c7af281cfed4c57df3a")
                    .addQueryParameter("vc", "220")
                    .addQueryParameter("vn", "3.10.0")
                    .addQueryParameter("deviceModel", Build.MODEL)
                    .addQueryParameter("system_version_code",String.valueOf(Build.VERSION.SDK_INT))
                    .build();
            return chain.proceed(chain.request().newBuilder().url(url).build());
        }).addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://baobab.kaiyanapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(EyepetizerService.class);
    }
}
