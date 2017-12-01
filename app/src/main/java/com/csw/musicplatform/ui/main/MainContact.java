package com.csw.musicplatform.ui.main;

import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.ui.base.BasePresenter;
import com.csw.musicplatform.ui.base.BaseView;

import java.util.List;

/**
 * Created by caisw on 2017/12/1.
 */

public interface MainContact {

    public interface View extends BaseView<Presenter> {

        void updateServerList(List<Server> serverList);
    }

    public interface Presenter extends BasePresenter {

        void loadServerList();

        void addNewServer(Server server);

        void removeServer(int position);

        void updateServerInfo(int position, String name, String address);

    }

}
