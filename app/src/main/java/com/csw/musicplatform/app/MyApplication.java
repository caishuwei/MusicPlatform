package com.csw.musicplatform.app;

import android.app.Application;

import com.csw.musicplatform.di.component.AppComponent;
import com.csw.musicplatform.di.component.DaggerAppComponent;
import com.csw.musicplatform.utils.ScreenInfo;

/**
 * Created by caisw on 2017/11/29.
 */

public class MyApplication extends Application {

    private AppComponent appComponent;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ScreenInfo.init(this);
        appComponent = DaggerAppComponent.builder().setMyApplication(this).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
