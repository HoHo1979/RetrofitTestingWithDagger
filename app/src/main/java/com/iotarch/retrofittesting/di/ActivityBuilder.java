package com.iotarch.retrofittesting.di;

import android.app.Activity;

import com.iotarch.retrofittesting.MainActivity;
import com.iotarch.retrofittesting.MainActivityModule;
import com.iotarch.retrofittesting.User;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by JamesHo on 2018/1/22.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();


}
