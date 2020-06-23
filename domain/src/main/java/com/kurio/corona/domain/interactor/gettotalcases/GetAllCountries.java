package com.kurio.corona.domain.interactor.gettotalcases;

import com.kurio.corona.domain.executor.PostExecutionThread;
import com.kurio.corona.domain.interactor.SingleUseCase;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.domain.model.TotalCases;
import com.kurio.corona.domain.repository.CoronaRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetAllCountries extends SingleUseCase<List<AllCountries>, Void> {
    private final CoronaRepository coronaRepository;

    @Inject
    GetAllCountries(CoronaRepository coronaRepository, PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coronaRepository = coronaRepository;
    }


    @Override
    protected Single<List<AllCountries>> buildUseCaseObservable(Void aVoid) {
        return coronaRepository.getAllCountries();
    }
}
