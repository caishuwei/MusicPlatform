package com.csw.musicplatform.ui.main;

import com.csw.musicplatform.app.Constant;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.di.qualifier.SettingSp;
import com.csw.musicplatform.utils.GSONUtils;
import com.csw.musicplatform.utils.SpHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by caisw on 2017/12/1.
 */
public class MainPresenter implements MainContact.Presenter {

    private SpHelper spHelper;
    private MainContact.View view;
    private List<Server> serverList = new ArrayList<>();

    @Inject
    public MainPresenter(@SettingSp SpHelper spHelper, MainContact.View view) {
        this.spHelper = spHelper;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void loadServerList() {
        List<Server> temp = GSONUtils.parseArray(spHelper.getString(Constant.SettingSP.SERVER_LIST), Server.class);
        if (temp != null && !temp.isEmpty()) {
            serverList.addAll(temp);
        }
        view.updateServerList(serverList);
    }

    @Override
    public void addNewServer(Server server) {
        serverList.add(server);
        saveAndNoticeChange();
    }

    @Override
    public void removeServer(int position) {
        serverList.remove(position);
        saveAndNoticeChange();
    }

    @Override
    public void updateServerInfo(int position, String name, String address) {
        serverList.get(position).setAddress(address);
        serverList.get(position).setName(name);
        saveAndNoticeChange();
    }

    private void saveAndNoticeChange() {
        spHelper.putString(Constant.SettingSP.SERVER_LIST, GSONUtils.toJSONString(serverList));
        view.updateServerList(serverList);
    }
}
