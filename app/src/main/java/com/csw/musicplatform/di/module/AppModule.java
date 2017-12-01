package com.csw.musicplatform.di.module;

import android.app.Application;
import android.content.Context;

import com.csw.musicplatform.app.MyApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by caisw on 2017/12/1.
 */

@Module
public class AppModule {

    @Provides
    public Application provideApplication(MyApplication myApplication) {
        return myApplication;
    }

    @Provides
    public Context provideContext(Application application) {
        return application;
    }

}
