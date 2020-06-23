package com.kurio.corona.injection.module;

import com.kurio.corona.data.CoronaDataRepository;
import com.kurio.corona.domain.repository.CoronaRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {
    @Binds
    public abstract CoronaRepository bindCoronaRepository(CoronaDataRepository coronaDataRepository);
}
