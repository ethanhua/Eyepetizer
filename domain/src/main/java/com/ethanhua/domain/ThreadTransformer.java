package com.ethanhua.domain;

import com.ethanhua.domain.executor.PostExecution;
import com.ethanhua.domain.executor.WorkExecution;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;

/**
 * Created by ethanhua on 2017/9/16.
 */

@Singleton
public class ThreadTransformer implements SingleTransformer {
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
}