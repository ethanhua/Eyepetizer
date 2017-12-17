package com.ethanhua.domain;

import com.ethanhua.domain.executor.PostExecution;
import com.ethanhua.domain.executor.WorkExecution;

import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

/**
 * Created by ethanhua on 2017/9/16.
 */

@Singleton
public class ThreadTransformer implements SingleTransformer, FlowableTransformer, CompletableTransformer {
    protected final WorkExecution workExecution;
    protected final PostExecution postExecution;

    @Inject
    public ThreadTransformer(WorkExecution workExecution,
                             PostExecution postExecution) {
        this.workExecution = workExecution;
        this.postExecution = postExecution;
    }


    @Override
    public SingleSource apply(@NonNull Single upstream) {
        return upstream.subscribeOn(workExecution.getScheduler())
                .observeOn(postExecution.getScheduler());
    }

    @Override
    public Publisher apply(@NonNull Flowable upstream) {
        return upstream.subscribeOn(workExecution.getScheduler())
                .observeOn(postExecution.getScheduler());
    }

    @Override
    public CompletableSource apply(@NonNull Completable upstream) {
        return upstream.subscribeOn(workExecution.getScheduler())
                .observeOn(postExecution.getScheduler());
    }
}