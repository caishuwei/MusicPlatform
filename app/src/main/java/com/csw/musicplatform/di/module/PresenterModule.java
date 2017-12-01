package com.csw.musicplatform.di.module;

import com.csw.musicplatform.ui.main.MainContact;
import com.csw.musicplatform.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by caisw on 2017/12/1.
 */
@Module
public class PresenterModule {

    @Provides
    public MainContact.Presenter providerMainPresenter(MainPresenter presenter) {
        return presenter;
    }


}
