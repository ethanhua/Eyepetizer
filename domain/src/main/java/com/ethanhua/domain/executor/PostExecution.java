package com.ethanhua.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by ethanhua on 2017/9/16.
 */

public interface PostExecution {
    Scheduler getScheduler();
}
