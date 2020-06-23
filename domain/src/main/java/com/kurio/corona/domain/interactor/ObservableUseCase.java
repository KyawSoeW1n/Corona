package com.kurio.corona.domain.interactor;

import com.kurio.corona.domain.executor.PostExecutionThread;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
public abstract class ObservableUseCase<T, Params> {
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    protected ObservableUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    protected abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(@NonNull DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(@NonNull Disposable disposable) {
        disposables.add(disposable);
    }
}