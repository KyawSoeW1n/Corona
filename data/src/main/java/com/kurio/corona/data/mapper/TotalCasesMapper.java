package com.kurio.corona.data.mapper;

import com.kurio.corona.data.model.TotalCasesEntity;
import com.kurio.corona.domain.model.TotalCases;

import javax.inject.Inject;

public class TotalCasesMapper implements EntityMapper<TotalCasesEntity, TotalCases> {

    @Inject
    public TotalCasesMapper() {
    }

    @Override
    public TotalCases mapFromEntity(TotalCasesEntity entity) {
        return new TotalCases(entity.getCases(), entity.getDeaths(), entity.getRecovered());
    }

    @Override
    public TotalCasesEntity mapToEntity(TotalCases domainModel) {
        return new TotalCasesEntity(domainModel.getCases(), domainModel.getDeaths(), domainModel.getRecovered());
    }
}
