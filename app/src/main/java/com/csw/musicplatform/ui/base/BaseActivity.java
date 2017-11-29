package com.csw.musicplatform.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 界面基类
 * Created by caisw on 2017/11/29.
 */

public abstract class BaseActivity extends AppCompatActivity implements IViewCreator {

    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        unBinder = ButterKnife.bind(this);
        onViewInit();
        initAdapter();
        initListener();
        onViewInitFinish();
    }

    @Override
    public void onViewInit() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onViewInitFinish() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBinder.unbind();
    }
}
