package com.kurio.corona.domain.interactor;

import com.kurio.corona.domain.executor.PostExecutionThread;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class MaybeUseCase<T, Params> {

    private PostExecutionThread postExecutionThread;

    protected MaybeUseCase(PostExecutionThread postExecutionThread)
    {
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Maybe<T> buildUseCaseObservable(Params params);

    public void execute(MaybeObserver<T> maybeObserver, Params params)
    {
        final Maybe<T> observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.getScheduler());
        observable.subscribeWith(maybeObserver);
    }

}
