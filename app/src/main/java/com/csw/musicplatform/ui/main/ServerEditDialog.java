package com.csw.musicplatform.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.csw.musicplatform.R;
import com.csw.musicplatform.ui.base.BaseDialog;

import butterknife.BindView;

/**
 * Created by caisw on 2017/12/1.
 */

public abstract class ServerEditDialog extends BaseDialog {

    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_address)
    EditText edt_address;

    public ServerEditDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getContentViewId() {
        return R.layout.dialog_server_edit;
    }

    @Override
    public void onViewInit() {
        super.onViewInit();
        setCancelable(false);
        setTitle(getContext().getString(R.string.ServerEditDialog_title));
    }

    @Override
    public void initListener() {
        super.initListener();
        addBottomButton(getContext().getString(R.string.Common_save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onServerInfoSave(edt_name.getText().toString().trim(), edt_address.getText().toString().trim())) {
                    dismiss();
                }
            }
        });
        addBottomButton(getContext().getString(R.string.Common_delete), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onServerDelete();
                dismiss();
            }
        });
        addBottomButton(getContext().getString(R.string.Common_cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    public void setServerInfo(String name, String address) {
        edt_name.setText(name);
        edt_address.setText(address);
    }

    protected abstract boolean onServerInfoSave(String name, String address);

    protected abstract void onServerDelete();
}
