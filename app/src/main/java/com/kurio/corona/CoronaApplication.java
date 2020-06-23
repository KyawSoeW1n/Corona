package com.kurio.corona;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.preference.PreferenceFragment;

import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;

import com.kurio.corona.injection.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class CoronaApplication extends Application implements HasActivityInjector, HasServiceInjector, HasSupportFragmentInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> androidInjector;
    @Inject
    public DispatchingAndroidInjector<Service> serviceInjector;
    @Inject
    public DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject
    public DispatchingAndroidInjector<PreferenceFragment> preferenceFragmentInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return androidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponent.builder().application(this).build().inject(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AndroidInjector<PreferenceFragment> preferenceFragmentInjector() {
        return preferenceFragmentInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}

