package com.kurio.corona.domain.interactor;

import com.kurio.corona.domain.executor.PostExecutionThread;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstract class for a Observable Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class SingleUseCase<T, Params> {
    private final PostExecutionThread postExecutionThread;

    protected SingleUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Single<T> buildUseCaseObservable(Params params);

    public void execute(SingleObserver<T> singleObserver, Params params) {
        final Single<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.getScheduler());
        observable.subscribeWith(singleObserver);
    }
}