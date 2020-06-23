package com.kurio.corona.injection.module;


import com.kurio.corona.BuildConfig;
import com.kurio.corona.Constants;
import com.kurio.corona.data.remote.service.CoronaService;
import com.kurio.corona.data.remote.service.CoronaServiceFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteModule {

    @Singleton
    @Provides
    @Inject
    public CoronaService provideConfigurationService(CoronaServiceFactory coronaServiceFactory) {
        return coronaServiceFactory.doCoronaService(BuildConfig.DEBUG);
    }

    @Provides
    public CoronaServiceFactory provideServiceFactory() {
        return new CoronaServiceFactory(Constants.BASE_URL);
    }
}
