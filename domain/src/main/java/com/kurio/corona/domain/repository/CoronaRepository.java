package com.kurio.corona.domain.repository;

import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.domain.model.TotalCases;

import java.util.List;

import io.reactivex.Single;

public interface CoronaRepository {
    Single<TotalCases> getTotalCases();

    Single<List<AllCountries>> getAllCountries();
}
