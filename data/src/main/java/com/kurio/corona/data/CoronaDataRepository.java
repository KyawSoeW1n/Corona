package com.kurio.corona.data;

import com.kurio.corona.data.mapper.AllCountriesMapper;
import com.kurio.corona.data.mapper.TotalCasesMapper;
import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.data.model.TotalCasesEntity;
import com.kurio.corona.data.store.CoronaDataStoreFactory;
import com.kurio.corona.domain.model.AllCountries;
import com.kurio.corona.domain.model.TotalCases;
import com.kurio.corona.domain.repository.CoronaRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class CoronaDataRepository implements CoronaRepository {
    private CoronaDataStoreFactory coronaDataStoreFactory;
    private TotalCasesMapper totalCasesMapper;
    private AllCountriesMapper allCountriesMapper;

    @Inject
    public CoronaDataRepository(TotalCasesMapper totalCasesMapper,
                                AllCountriesMapper allCountriesMapper,
                                CoronaDataStoreFactory coronaDataStoreFactory) {
        this.totalCasesMapper = totalCasesMapper;
        this.allCountriesMapper = allCountriesMapper;
        this.coronaDataStoreFactory = coronaDataStoreFactory;
    }

    @Override
    public Single<TotalCases> getTotalCases() {
        return coronaDataStoreFactory.getRemoteDataStore().getTotalCases().map(new Function<TotalCasesEntity, TotalCases>() {
            @Override
            public TotalCases apply(TotalCasesEntity totalCasesEntity) throws Exception {
                return totalCasesMapper.mapFromEntity(totalCasesEntity);
            }
        });
    }

    @Override
    public Single<List<AllCountries>> getAllCountries() {
        return coronaDataStoreFactory.getRemoteDataStore().getAllCountries()
                .map(new Function<List<AllCountriesEntity>, List<AllCountries>>() {
                    @Override
                    public List<AllCountries> apply(List<AllCountriesEntity> allCountriesEntities) throws Exception {
                        return allCountriesMapper.mapFromEntity(allCountriesEntities);
                    }
                });
    }
}
