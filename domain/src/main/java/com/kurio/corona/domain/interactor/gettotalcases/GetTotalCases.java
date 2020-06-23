package com.kurio.corona.domain.interactor.gettotalcases;

import com.kurio.corona.domain.executor.PostExecutionThread;
import com.kurio.corona.domain.interactor.SingleUseCase;
import com.kurio.corona.domain.model.TotalCases;
import com.kurio.corona.domain.repository.CoronaRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetTotalCases extends SingleUseCase<TotalCases, Void> {
    private final CoronaRepository coronaRepository;

    @Inject
    GetTotalCases(CoronaRepository coronaRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coronaRepository = coronaRepository;
    }


    @Override
    protected Single<TotalCases> buildUseCaseObservable(Void aVoid) {
        return coronaRepository.getTotalCases();
    }
}
