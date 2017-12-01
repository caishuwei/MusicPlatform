package com.csw.musicplatform.ui.file_list;

import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.ui.base.BasePresenter;
import com.csw.musicplatform.ui.base.BaseView;

/**
 * Created by caisw on 2017/12/1.
 */

public interface FileExplorerContact {

    public interface View extends BaseView<Presenter> {
        
    }

    public interface Presenter extends BasePresenter {

        void setServer(Server server);
    }
}
