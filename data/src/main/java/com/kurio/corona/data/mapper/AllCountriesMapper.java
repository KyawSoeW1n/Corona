package com.kurio.corona.data.mapper;

import com.kurio.corona.data.model.AllCountriesEntity;
import com.kurio.corona.domain.model.AllCountries;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AllCountriesMapper implements EntityMapper<List<AllCountriesEntity>, List<AllCountries>> {
    @Inject
    public AllCountriesMapper() {
    }

    @Override
    public List<AllCountries> mapFromEntity(List<AllCountriesEntity> entity) {
        List<AllCountries> allCountries = new ArrayList<>();
        for (AllCountriesEntity allCountriesEntity : entity) {
            allCountries.add(new AllCountries(allCountriesEntity.getCountry(),
                    allCountriesEntity.getCases(),
                    allCountriesEntity.getTodayCases(),
                    allCountriesEntity.getDeaths(),
                    allCountriesEntity.getTodayDeaths(),
                    allCountriesEntity.getRecovered(),
                    allCountriesEntity.getCasesPerOneMillion(),
                    allCountriesEntity.getDeathsPerOneMillion(),
                    allCountriesEntity.getCountryInfo().getFlag()));
        }
        return allCountries;
    }

    @Override
    public List<AllCountriesEntity> mapToEntity(List<AllCountries> domainModel) {
        return null;
    }
}
