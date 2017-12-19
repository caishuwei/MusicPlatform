package com.csw.musicplatform.ui.file_list;

import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.bean.http.File;
import com.csw.musicplatform.ui.base.BasePresenter;
import com.csw.musicplatform.ui.base.BaseView;

import java.util.List;

/**
 * Created by caisw on 2017/12/1.
 */

public interface FileExplorerContact {

    public interface View extends BaseView<Presenter> {

        void showFileListLoading();

        void hideFileListLoading();

        void showServerError(String hint);

        void updateFileList(String name, List<File> fileList);

    }

    public interface Presenter extends BasePresenter {

        void setServerAndCheck(Server server);

        void refreshCurrPath();

        void loadChildFiles(File file);

        boolean toPreFile();

    }
}
