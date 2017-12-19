package com.csw.musicplatform.ui.main;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.csw.musicplatform.R;
import com.csw.musicplatform.app.MyApplication;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.ui.base.BaseActivity;
import com.csw.musicplatform.ui.file_list.FileExplorerFragment;
import com.csw.musicplatform.utils.ActivityHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 主界面
 * Created by caisw on 2017/11/29.
 */

public class MainActivity extends BaseActivity implements MainContact.View {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_host_list)
    RecyclerView rl_host_list;
    private ServerListAdapter serverListAdapter;
    private android.view.View addView;
    @Inject
    MainContact.Presenter presenter;
    private FileExplorerFragment currFragment;

    public MainActivity() {
        MyApplication.getInstance().getAppComponent().getMainComponentBuilder().setView(this).build().inject(this);
    }

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
        rl_host_list.setLayoutManager(new LinearLayoutManager(this));
        addView = LayoutInflater.from(this).inflate(R.layout.item_add, rl_host_list, false);
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        rl_host_list.setAdapter(serverListAdapter = new ServerListAdapter(null));
        serverListAdapter.addFooterView(addView);
    }

    @Override
    public void initListener() {
        super.initListener();
        toolbar.setNavigationOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        serverListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                ServerEditDialog dialog = new ServerEditDialog(view.getContext()) {
                    @Override
                    protected boolean onServerInfoSave(String name, String address) {
                        presenter.updateServerInfo(position, name, address);
                        return true;
                    }

                    @Override
                    protected void onServerDelete() {
                        presenter.removeServer(position);
                    }
                };
                dialog.setServerInfo(serverListAdapter.getItem(position).getName(), serverListAdapter.getItem(position).getAddress());
                dialog.show();
                return true;
            }
        });
        serverListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, android.view.View view, int position) {
                Server server = (Server) adapter.getItem(position);
                currFragment = ActivityHelper.setFragment(
                        getSupportFragmentManager(),
                        R.id.fl_fragment_container,
                        FileExplorerFragment.class,
                        server.getAddress(),
                        FileExplorerFragment.createBundle(server)
                );
                drawerLayout.closeDrawers();
            }
        });
        addView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                new ServerEditDialog(v.getContext()) {
                    @Override
                    protected boolean onServerInfoSave(String name, String address) {
                        presenter.addNewServer(new Server(name, address));
                        return true;
                    }

                    @Override
                    protected void onServerDelete() {

                    }
                }.show();
            }
        });
    }

    @Override
    public void onViewInitFinish() {
        super.onViewInitFinish();
        presenter.loadServerList();
    }

    @Override
    public void setPresenter(MainContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateServerList(List<Server> serverList) {
        serverListAdapter.setNewData(serverList);
    }

    @Override
    public void onBackPressed() {
        if(currFragment==null||!currFragment.onPreClick()){
            super.onBackPressed();
        }
    }
}
