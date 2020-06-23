package com.kurio.corona.data.remote.service;

import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.data.remote.response.TotalCasesResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CoronaService {
    @GET("all")
    Single<TotalCasesResponse> getTotalCase();

    @GET("countries")
    Single<List<AllCountriesEntity>> getAllCountries();
}
