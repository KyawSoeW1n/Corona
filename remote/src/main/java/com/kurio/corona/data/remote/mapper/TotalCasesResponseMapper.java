package com.kurio.corona.data.remote.mapper;

import com.kurio.corona.data.model.TotalCasesEntity;
import com.kurio.corona.data.remote.response.TotalCasesResponse;

import javax.inject.Inject;

public class TotalCasesResponseMapper implements ResponseMapper<TotalCasesResponse, TotalCasesEntity> {
    @Inject
    public TotalCasesResponseMapper() {

    }

    @Override
    public TotalCasesEntity mapFromResponse(TotalCasesResponse response) {
        return new TotalCasesEntity(response.getCases(), response.getDeaths(), response.getRecovered());
    }
}
