package com.csw.musicplatform.di.component;

import com.csw.musicplatform.di.module.PresenterModule;
import com.csw.musicplatform.ui.main.MainActivity;
import com.csw.musicplatform.ui.main.MainContact;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by caisw on 2017/12/1.
 */
@Subcomponent(modules = {PresenterModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    public interface Builder {
        MainComponent build();

        @BindsInstance
        Builder setView(MainContact.View view);
    }

}
