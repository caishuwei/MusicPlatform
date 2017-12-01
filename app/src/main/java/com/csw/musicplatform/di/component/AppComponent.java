package com.csw.musicplatform.di.component;

import com.csw.musicplatform.app.MyApplication;
import com.csw.musicplatform.di.module.AppModule;
import com.csw.musicplatform.di.module.SpModule;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by caisw on 2017/12/1.
 */
@Component(modules = {AppModule.class, SpModule.class})
public interface AppComponent {

    MainComponent.Builder getMainComponentBuilder();
    FileExplorerComponent.Builder getFileExplorerComponentBuilder();
    @Component.Builder
    public interface Builder {
        AppComponent build();

        @BindsInstance
        Builder setMyApplication(MyApplication myApplication);
    }

}
