package com.kurio.corona.data.remote;

import com.google.gson.JsonParser;
import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.data.model.TotalCasesEntity;
import com.kurio.corona.data.remote.mapper.AllCountriesResponseMapper;
import com.kurio.corona.data.remote.mapper.TotalCasesResponseMapper;
import com.kurio.corona.data.remote.response.TotalCasesResponse;
import com.kurio.corona.data.remote.service.CoronaService;
import com.kurio.corona.data.repository.CoronaRemote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class CoronaImpl implements CoronaRemote {
    private CoronaService coronaService;
    private TotalCasesResponseMapper totalCasesResponseMapper;
    private AllCountriesResponseMapper allCountriesResponseMapper;

    @Inject
    CoronaImpl(CoronaService coronaService,
               AllCountriesResponseMapper allCountriesResponseMapper,
               TotalCasesResponseMapper totalCasesResponseMapper) {
        this.coronaService = coronaService;
        this.allCountriesResponseMapper = allCountriesResponseMapper;
        this.totalCasesResponseMapper = totalCasesResponseMapper;
    }

    @Override
    public Single<TotalCasesEntity> getTotalCases() {
        return coronaService.getTotalCase()
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends TotalCasesResponse>>() {
                    @Override
                    public SingleSource<? extends TotalCasesResponse> apply(Throwable throwable) throws Exception {
                        HttpException exception = (HttpException) throwable;
                        ResponseBody responseBody = exception.response().errorBody();
                        if (responseBody != null) {
                            String errorBody = responseBody.string();
                            try {
                                JsonParser jsonParser = new JsonParser();
                                jsonParser.parse(errorBody).getAsJsonObject();
                            } catch (Exception e) {
                                return Single.error(new Exception(e.getMessage()));
                            }
                        }
                        return Single.error(throwable);
                    }
                })
                .map(new Function<TotalCasesResponse, TotalCasesEntity>() {
                    @Override
                    public TotalCasesEntity apply(TotalCasesResponse totalCasesResponse) throws Exception {
                        return totalCasesResponseMapper.mapFromResponse(totalCasesResponse);
                    }
                });
    }

    @Override
    public Single<List<AllCountriesEntity>> getAllCountries() {
        return coronaService.getAllCountries()
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends List<AllCountriesEntity>>>() {
                    @Override
                    public SingleSource<? extends List<AllCountriesEntity>> apply(Throwable throwable) throws Exception {
                        HttpException exception = (HttpException) throwable;
                        ResponseBody responseBody = exception.response().errorBody();
                        if (responseBody != null) {
                            String errorBody = responseBody.string();
                            try {
                                JsonParser jsonParser = new JsonParser();
                                jsonParser.parse(errorBody).getAsJsonObject();
                            } catch (Exception e) {
                                return Single.error(new Exception(e.getMessage()));
                            }
                        }
                        return Single.error(throwable);
                    }
                })
                .map(new Function<List<AllCountriesEntity>, List<AllCountriesEntity>>() {
                    @Override
                    public List<AllCountriesEntity> apply(List<AllCountriesEntity> allCountriesEntities) throws Exception {
                        return allCountriesResponseMapper.mapFromResponse(allCountriesEntities);
                    }
                });
    }
}