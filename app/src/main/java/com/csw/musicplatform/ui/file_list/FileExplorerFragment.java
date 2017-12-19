package com.csw.musicplatform.ui.file_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.csw.musicplatform.R;
import com.csw.musicplatform.app.MyApplication;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.bean.http.File;
import com.csw.musicplatform.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by caisw on 2017/12/1.
 */

public class FileExplorerFragment extends BaseFragment implements FileExplorerContact.View {
    @BindView(R.id.tv_path)
    TextView tv_path;
    @BindView(R.id.refreshView)
    SwipeRefreshLayout refreshView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    FileExplorerContact.Presenter presenter;
    @Inject
    Toast toast;
    private FileListAdapter fileListAdapter;

    public static Bundle createBundle(Server server) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("server", server);
        return bundle;
    }

    public FileExplorerFragment() {
        MyApplication.getInstance().getAppComponent().getFileExplorerComponentBuilder().setView(this).build().inject(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_file_list;
    }

    @Override
    public void onViewInit() {
        super.onViewInit();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initAdapter() {
        super.initAdapter();
        recyclerView.setAdapter(fileListAdapter = new FileListAdapter());
    }

    @Override
    public void initListener() {
        super.initListener();
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshCurrPath();
            }
        });
        fileListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                File file = fileListAdapter.getItem(position);
                if (file.getType() == 1) {
                    presenter.loadChildFiles(file);
                }
            }
        });
    }

    @Override
    public void onViewInitFinish() {
        super.onViewInitFinish();
        Bundle data = getArguments();
        Server server = (Server) data.get("server");
        presenter.setServerAndCheck(server);
//        presenter.loadFileList(null);
    }

    @Override
    public void setPresenter(FileExplorerContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFileListLoading() {
        if (getView() != null) {
            if (!refreshView.isRefreshing()) {
                refreshView.setRefreshing(true);
            }
        }
    }

    @Override
    public void hideFileListLoading() {
        if (getView() != null) {
            if (refreshView.isRefreshing()) {
                refreshView.setRefreshing(false);
            }
        }
    }

    @Override
    public void showServerError(String hint) {
        toast.setText(hint);
        toast.show();
    }

    @Override
    public void updateFileList(String name, List<File> fileList) {
        if (getView() != null) {
            tv_path.setText(name);
            fileListAdapter.setNewData(fileList);
        }
    }

    public boolean onPreClick() {
        return presenter.toPreFile();
    }
}
