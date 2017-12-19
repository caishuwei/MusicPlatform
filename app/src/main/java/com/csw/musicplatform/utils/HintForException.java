package com.csw.musicplatform.utils;

import android.content.Context;

import com.csw.musicplatform.R;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * Created by caisw on 2017/12/4.
 */

public class HintForException {
    private Context context;

    public HintForException(Context context) {
        this.context = context;
    }

    /**
     * 获取异常提示
     */
    public String findHint(Throwable e) {
        String msg = null;
        if (e instanceof SocketTimeoutException) {
            msg = context.getString(R.string.Hint_socket_time_out);
        } else if (e instanceof ConnectException||e instanceof HttpException) {
            msg = context.getString(R.string.Hint_network_connect_error);
        } else if (e instanceof MalformedJsonException || e instanceof JsonParseException) {
            msg = context.getString(R.string.Hint_data_parse_exception);
        } else {
            msg = e.getMessage();
        }
        return msg;
    }
}
