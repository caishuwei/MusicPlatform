package com.csw.musicplatform.ui.file_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.csw.musicplatform.R;
import com.csw.musicplatform.app.MyApplication;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.ui.base.BaseFragment;

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
    public void onViewInitFinish() {
        super.onViewInitFinish();
        Bundle data = getArguments();
        Server server = (Server) data.get("server");
        presenter.setServer(server);
//        presenter.loadFileList(null);
    }

    @Override
    public void setPresenter(FileExplorerContact.Presenter presenter) {
        this.presenter = presenter;
    }
}
