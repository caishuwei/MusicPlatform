package com.csw.musicplatform.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by caisw on 2017/11/29.
 */

public class BaseItemBean implements MultiItemEntity, Serializable {
    public static final int ITEM_HOST = 0;
    public static final int ITEM_ADD = 1;

    private int itemType;

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
