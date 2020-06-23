package com.kurio.corona.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kurio.corona.data.presentation.CoronaViewModel;
import com.kurio.corona.injection.ViewModelFactory;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Binds;
import dagger.MapKey;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PresentationModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(CoronaViewModel.class)
    public abstract ViewModel bindCoronaViewModel(CoronaViewModel coronaViewModel);

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }
}