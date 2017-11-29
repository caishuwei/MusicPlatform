package com.csw.musicplatform.ui.base;

/**
 * view创建接口
 * Created by caisw on 2017/11/29.
 */

public interface IViewCreator {

    /**
     * 获取内容视图布局id
     */
    int getContentViewId();

    /**
     * 视图初始化
     */
    void onViewInit();

    /**
     * 初始化适配器
     */
    void initAdapter();

    /**
     * 初始化监听
     */
    void initListener();

    /**
     * 视图初始化结束
     */
    void onViewInitFinish();
}
