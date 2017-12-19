package com.csw.musicplatform.di.module;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.csw.musicplatform.app.MyApplication;
import com.csw.musicplatform.utils.HintForException;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

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

    @Provides
    @Reusable
    public HintForException provideHintForException(Context context) {
        return new HintForException(context);
    }

    @Provides
    @Reusable
    public Toast provideToast(Context context) {
        return Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }
}
