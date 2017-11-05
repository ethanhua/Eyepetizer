package com.ethanhua.eyepetizer.execution;

import com.ethanhua.domain.executor.WorkExecution;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ethanhua on 2017/9/16.
 */
@Singleton
public class IOExecution implements WorkExecution {
    @Inject
    IOExecution() {
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.io();
    }
}
