package com.csw.musicplatform.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 碎片基类
 * Created by caisw on 2017/11/29.
 */

public abstract class BaseFragment extends Fragment implements IViewCreator {

    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, view);
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
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
}
