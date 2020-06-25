package com.kurio.corona.injection.module;

import com.kurio.corona.UiThread;
import com.kurio.corona.activity.BaseActivity;
import com.kurio.corona.activity.MainActivity;
import com.kurio.corona.domain.executor.PostExecutionThread;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UiModule {

    @Binds
    public abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);

    @ContributesAndroidInjector
    public abstract MainActivity contributesMainActivity();

    @ContributesAndroidInjector
    public abstract BaseActivity contributesBaseActivity();
}
