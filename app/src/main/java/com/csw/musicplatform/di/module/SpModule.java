package com.csw.musicplatform.di.module;

import android.app.Application;
import android.content.Context;

import com.csw.musicplatform.app.Constant;
import com.csw.musicplatform.di.qualifier.SettingSp;
import com.csw.musicplatform.utils.SpHelper;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Created by caisw on 2017/12/1.
 */
@Module
public class SpModule {

    @Provides
    @SettingSp
    @Reusable
    public SpHelper providerSettingSpHelper(Application application) {
        return new SpHelper(Constant.SettingSP.FILE_NAME, Context.MODE_PRIVATE, application);
    }


}
