package com.iotarch.retrofittesting.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by JamesHo on 2018/1/27.
 */
@Module
public abstract class AppModule{

    @Binds
    abstract Context provideContext(Application application);
}
