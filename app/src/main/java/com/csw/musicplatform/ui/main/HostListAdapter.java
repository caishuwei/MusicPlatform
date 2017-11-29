package com.csw.musicplatform.ui.main;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.csw.musicplatform.R;
import com.csw.musicplatform.bean.BaseItemBean;

import java.util.List;

/**
 * Created by caisw on 2017/11/29.
 */

class HostListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public HostListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(BaseItemBean.ITEM_HOST, R.layout.item_host);
        addItemType(BaseItemBean.ITEM_ADD, R.layout.item_add);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }
}
