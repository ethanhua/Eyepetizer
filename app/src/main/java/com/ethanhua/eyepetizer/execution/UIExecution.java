package com.ethanhua.eyepetizer.execution;

import com.ethanhua.domain.executor.PostExecution;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by ethanhua on 2017/9/16.
 */
@Singleton
public class UIExecution implements PostExecution {
    @Inject
    UIExecution() {

    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
