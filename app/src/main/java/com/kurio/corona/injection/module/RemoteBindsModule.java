package com.kurio.corona.injection.module;

import com.kurio.corona.data.remote.CoronaImpl;
import com.kurio.corona.data.repository.CoronaRemote;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RemoteBindsModule {
    @Binds
    public abstract CoronaRemote bindSessionRemote(CoronaImpl sessionRemote);
}
