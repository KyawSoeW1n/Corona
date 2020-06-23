package com.kurio.corona.data.remote.mapper;

import com.kurio.corona.data.model.AllCountriesEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AllCountriesResponseMapper implements ResponseMapper<List<AllCountriesEntity>, List<AllCountriesEntity>> {
    @Inject
    public AllCountriesResponseMapper() {
    }

    @Override
    public List<AllCountriesEntity> mapFromResponse(List<AllCountriesEntity> response) {
        List<AllCountriesEntity> allCountriesEntityList = new ArrayList<>();

        for (AllCountriesEntity allCountriesEntity : response) {
            allCountriesEntityList.add(new AllCountriesEntity(allCountriesEntity.getCountry(),
                    allCountriesEntity.getCases(),
                    allCountriesEntity.getTodayCases(),
                    allCountriesEntity.getDeaths(),
                    allCountriesEntity.getTodayDeaths(),
                    allCountriesEntity.getRecovered(),
                    allCountriesEntity.getCasesPerOneMillion(),
                    allCountriesEntity.getDeathsPerOneMillion(),
                    allCountriesEntity.getCountryInfo()));
        }
        return allCountriesEntityList;
    }
}
