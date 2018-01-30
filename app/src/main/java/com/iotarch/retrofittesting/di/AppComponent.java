package com.iotarch.retrofittesting.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by JamesHo on 2018/1/27.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuilder.class,AppModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{


    void inject(MyApp app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
