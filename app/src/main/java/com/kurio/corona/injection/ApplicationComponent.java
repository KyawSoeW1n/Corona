package com.kurio.corona.injection;

import android.app.Application;

import com.kurio.corona.CoronaApplication;
import com.kurio.corona.injection.module.ApplicationModule;
import com.kurio.corona.injection.module.DataModule;
import com.kurio.corona.injection.module.PresentationModule;
import com.kurio.corona.injection.module.RemoteBindsModule;
import com.kurio.corona.injection.module.RemoteModule;
import com.kurio.corona.injection.module.UiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, UiModule.class, PresentationModule.class, DataModule.class, RemoteModule.class,  RemoteBindsModule.class})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(CoronaApplication app);
}
