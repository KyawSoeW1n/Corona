package com.kurio.corona.data.store;

import javax.inject.Inject;

public class CoronaDataStoreFactory {

    private CoronaRemoteDataStore drinkListRemoteDataStore;

    @Inject
    public CoronaDataStoreFactory(CoronaRemoteDataStore drinkListRemoteDataStore) {
        this.drinkListRemoteDataStore = drinkListRemoteDataStore;
    }

    public CoronaRemoteDataStore getRemoteDataStore() {
        return drinkListRemoteDataStore;
    }
}
