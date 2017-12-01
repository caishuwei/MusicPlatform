package com.csw.musicplatform.ui.file_list;

import com.csw.musicplatform.bean.Server;

import javax.inject.Inject;

/**
 * Created by caisw on 2017/12/1.
 */

public class FileExplorerPresenter implements FileExplorerContact.Presenter {

    private FileExplorerContact.View view;
    private Server server;

    @Inject
    public FileExplorerPresenter(FileExplorerContact.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }



}
