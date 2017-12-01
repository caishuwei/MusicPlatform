package com.csw.musicplatform.di.component;

import com.csw.musicplatform.ui.file_list.FileExplorerContact;
import com.csw.musicplatform.ui.file_list.FileExplorerFragment;
import com.csw.musicplatform.ui.main.MainContact;

import dagger.BindsInstance;
import dagger.Subcomponent;

/**
 * Created by caisw on 2017/12/1.
 */
@Subcomponent
public interface FileExplorerComponent {
    void inject(FileExplorerFragment fileExplorerFragment);

    @Subcomponent.Builder
    public interface Builder {
        FileExplorerComponent build();

        @BindsInstance
        Builder setView(FileExplorerContact.View view);
    }

}
