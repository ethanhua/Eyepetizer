package com.ethanhua.eyepetizer.viewmodel;

import org.reactivestreams.Publisher;

import java.util.concurrent.CancellationException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by ethanhua on 2017/10/31.
 */

public class LifecycleTransformer<T>  implements
        ObservableTransformer<T, T>,
        FlowableTransformer<T, T>,
        MaybeTransformer<T, T>,
        SingleTransformer<T, T>,
        CompletableTransformer {

    private final Observable<?> observable;

    public LifecycleTransformer(Observable<?> observable) {
        this.observable = observable;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.takeUntil(observable);
    }

    @Override
    public Publisher<T> apply(@NonNull Flowable<T> upstream) {
        return upstream.takeUntil(observable.toFlowable(BackpressureStrategy.LATEST));
    }

    @Override
    public MaybeSource<T> apply(@NonNull Maybe<T> upstream) {
        return upstream.takeUntil(observable.firstElement());
    }

    @Override
    public SingleSource<T> apply(@NonNull Single<T> upstream) {
        return upstream.takeUntil(observable.firstOrError());
    }

    @Override
    public CompletableSource apply(@NonNull Completable upstream) {
        return Completable.ambArray(
                upstream,
                observable.flatMapCompletable((Function<Object, CompletableSource>) o ->
                        Completable.error(new CancellationException())));
    }
}
