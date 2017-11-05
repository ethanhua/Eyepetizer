package com.ethanhua.eyepetizer.viewmodel;

import com.ethanhua.commonlib.viewmodel.ViewModel;

import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by ethanhua on 2017/10/31.
 */

public class LiveViewModel extends ViewModel {
    private static final int EVENT_LIFECYCLE_CLEAR = 0;
    private final BehaviorSubject lifecycleSubject = BehaviorSubject.create();

    @Override
    public void clean() {
        super.clean();
        lifecycleSubject.onNext(EVENT_LIFECYCLE_CLEAR);
    }

    protected <T> LifecycleTransformer<T> bindToLifecycle() {
        return new LifecycleTransformer(lifecycleSubject);
    }
}
