package com.csw.musicplatform.ui.main;

import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.csw.musicplatform.R;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.ui.base.BaseActivity;

import java.util.List;

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
    private List<Server> serverList;
    private ServerListAdapter serverListAdapter;
    private View addView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewInit() {
        super.onViewInit();
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addView = LayoutInflater.from(this).inflate(R.layout.item_add, rl_host_list, false);
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        rl_host_list.setAdapter(serverListAdapter = new ServerListAdapter(serverList));
        serverListAdapter.addFooterView(addView);
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
        serverListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Server server = (Server) adapter.getItem(position);
            }
        });
        serverListAdapter.enableSwipeItem();
        serverListAdapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
