package com.csw.musicplatform.ui.file_list;

import android.content.Context;

import com.csw.musicplatform.R;
import com.csw.musicplatform.bean.Server;
import com.csw.musicplatform.bean.http.Drive;
import com.csw.musicplatform.bean.http.File;
import com.csw.musicplatform.http.APIServiceFactory;
import com.csw.musicplatform.http.FileService;
import com.csw.musicplatform.http.HttpCallback;
import com.csw.musicplatform.utils.HintForException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by caisw on 2017/12/1.
 */

public class FileExplorerPresenter implements FileExplorerContact.Presenter {

    private FileExplorerContact.View view;
    private HintForException hint4Exception;
    private Context context;
    private Server server;
    private String baseUrl;

    private List<File> fileList = new ArrayList<>();
    private File preFile = null;

    @Inject
    public FileExplorerPresenter(FileExplorerContact.View view, HintForException hint4Exception, Context context) {
        this.view = view;
        this.hint4Exception = hint4Exception;
        this.context = context;
        this.view.setPresenter(this);
    }

    @Override
    public void setServerAndCheck(Server server) {
        this.server = server;
        baseUrl = null;
        view.showFileListLoading();
        try {
            URL url = new URL(server.getAddress());
            String path = url.getProtocol() + "://" + url.getAuthority() + url.getPath();
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            final String baseUrl = path;
            FileService service = APIServiceFactory.getFileExplorerService(baseUrl);
            new HttpCallback<String>() {
                @Override
                public void onNext(@NonNull String s) {
                    super.onNext(s);
                    FileExplorerPresenter.this.baseUrl = baseUrl;
                    refreshCurrPath();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                    view.hideFileListLoading();
                    view.showServerError(hint4Exception.findHint(e));
                }
            }.execute(service.serverIsRunning());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            view.showServerError(context.getString(R.string.Hint_service_no_run));
            view.hideFileListLoading();
        }
    }

    @Override
    public void refreshCurrPath() {
        if (baseUrl == null) {
            return;
        }
        if (preFile == null) {
            loadDriveList();
        } else {
            loadChildFiles(preFile);
        }
    }

    @Override
    public void loadChildFiles(File file) {
        if (file.getType() == 1) {
            preFile = file;
            loadChildFileList(preFile.getFilePath());
        }
    }

    @Override
    public boolean toPreFile() {
        if (preFile != null) {
            preFile = findPreFile(null);
            if (preFile != null) {
                loadChildFileList(preFile.getFilePath());
            } else {
                loadDriveList();
            }
            return true;
        }
        return false;
    }

    private File findPreFile(File f) {
        List<File> childs;
        if (f == null) {
            childs = fileList;
        } else {
            childs = f.getChildFiles();
        }
        for (File file : childs) {
            if (file.getFilePath().equals(preFile.getFilePath())) {
                return f;
            } else {
                String path = file.getFilePath().endsWith("\\") ? file.getFilePath() : file.getFilePath() + "\\";
                if (preFile.getFilePath().startsWith(path)) {
                    return findPreFile(file);
                }
            }
        }
        return null;
    }

    private void loadChildFileList(final String path) {
        view.showFileListLoading();
        String httpPath;
        try {
            httpPath = URLEncoder.encode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            httpPath = path;
        }
        new HttpCallback<List<File>>() {
            @Override
            public void onNext(@NonNull List<File> temp) {
                super.onNext(temp);
                view.hideFileListLoading();
                if (preFile != null && preFile.getFilePath().equals(path)) {
                    if (temp != null) {
                        preFile.getChildFiles().clear();
                        if (!temp.isEmpty()) {
                            preFile.getChildFiles().addAll(temp);
                        }
                    }
                    updateCurrListToUI();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
                view.hideFileListLoading();
                view.showServerError(hint4Exception.findHint(e));
            }
        }.execute(APIServiceFactory.getFileExplorerService(baseUrl).getChildFileLiseByPath(httpPath));
    }

    private void loadDriveList() {
        view.showFileListLoading();
        new HttpCallback<List<Drive>>() {
            @Override
            public void onNext(@NonNull List<Drive> temp) {
                super.onNext(temp);
                view.hideFileListLoading();
                fileList.clear();
                preFile = null;
                if (temp != null && !temp.isEmpty()) {
                    fileList.addAll(temp);
                }
                updateCurrListToUI();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
                view.hideFileListLoading();
                view.showServerError(hint4Exception.findHint(e));
            }
        }.execute(APIServiceFactory.getFileExplorerService(baseUrl).getDriveList());
    }

    private void updateCurrListToUI() {
        if (preFile == null) {
            view.updateFileList(server.getAddress(), fileList);
        } else {
            view.updateFileList(preFile.getFilePath(), preFile.getChildFiles());
        }
    }

}
