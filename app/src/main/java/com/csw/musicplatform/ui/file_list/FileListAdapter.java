package com.csw.musicplatform.ui.file_list;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csw.musicplatform.R;
import com.csw.musicplatform.bean.http.Drive;
import com.csw.musicplatform.bean.http.File;

/**
 * Created by caisw on 2017/12/5.
 */

public class FileListAdapter extends BaseQuickAdapter<File,BaseViewHolder> {
    public FileListAdapter() {
        super(R.layout.item_file, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        helper.setText(R.id.tv_name,item.getFileName());
        helper.setText(R.id.tv_size,item.getFileSizeStr());
    }
}
