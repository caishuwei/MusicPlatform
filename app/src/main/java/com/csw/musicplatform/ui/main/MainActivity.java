package com.csw.musicplatform.ui.main;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import com.csw.musicplatform.R;
import com.csw.musicplatform.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * 主界面
 * Created by caisw on 2017/11/29.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_host_list)
    RecyclerView rl_host_list;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewInit() {
        super.onViewInit();
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        rl_host_list.setAdapter(new HostListAdapter());
    }

    @Override
    public void initListener() {
        super.initListener();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

}
