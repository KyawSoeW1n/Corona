package com.kurio.corona.domain.interactor;

import com.kurio.corona.domain.executor.PostExecutionThread;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a [DisposableCompletableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 * <p>
 * This use case is to be used when we expect no value to be emitted but rather for an action to be completed.
 *
 * @see Completable
 */
public abstract class CompletableUseCase<Params> {
    private final PostExecutionThread postExecutionThread;

    public CompletableUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;

    }

    protected abstract Completable buildUseCaseObservable(Params params);

    public void execute(CompletableObserver completableObserver, Params parms) {
        final Completable observable = this.buildUseCaseObservable(parms)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.getScheduler());
        observable.subscribeWith(completableObserver);
    }
}
