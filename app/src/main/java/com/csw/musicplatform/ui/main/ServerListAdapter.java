package com.csw.musicplatform.ui.main;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csw.musicplatform.R;
import com.csw.musicplatform.bean.Server;

import java.util.List;

class ServerListAdapter extends BaseItemDraggableAdapter<Server, BaseViewHolder> {

    public ServerListAdapter(List<Server> data) {
        super(R.layout.item_host, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Server server) {
        helper.setText(R.id.tv_name, server.getName());
        helper.setText(R.id.tv_address, server.getAddress());
    }
}
