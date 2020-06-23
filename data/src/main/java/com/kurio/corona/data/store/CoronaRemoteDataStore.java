package com.kurio.corona.data.store;

import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.data.model.TotalCasesEntity;
import com.kurio.corona.data.repository.CoronaDataStore;
import com.kurio.corona.data.repository.CoronaRemote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CoronaRemoteDataStore implements CoronaDataStore {

    private CoronaRemote coronaRemote;

    @Inject
    public CoronaRemoteDataStore(CoronaRemote coronaRemote) {
        this.coronaRemote = coronaRemote;
    }

    @Override
    public Single<TotalCasesEntity> getTotalCases() {
        return coronaRemote.getTotalCases();
    }

    @Override
    public Single<List<AllCountriesEntity>> getAllCountries() {
        return coronaRemote.getAllCountries();
    }
}
