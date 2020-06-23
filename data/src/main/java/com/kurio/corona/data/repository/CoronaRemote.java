package com.kurio.corona.data.repository;

import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.data.model.TotalCasesEntity;

import java.util.List;

import io.reactivex.Single;

public interface CoronaRemote {
    Single<TotalCasesEntity> getTotalCases();

    Single<List<AllCountriesEntity>> getAllCountries();

}
