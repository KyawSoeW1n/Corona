package com.kurio.corona;


import com.kurio.corona.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class UiThread implements PostExecutionThread {

    @Inject
    public UiThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
