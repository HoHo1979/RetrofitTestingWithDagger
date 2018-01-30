package com.iotarch.retrofittesting;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import okhttp3.OkHttpClient;

/**
 * Created by JamesHo on 2018/1/27.
 */

@Module
public abstract class MainActivityModule {

    @Provides
    static MainActivity provideMainView(MainActivity mainActivity){
        return mainActivity;
    };

    @Provides
    static User provideUser() {
        return new User();
    }




}
