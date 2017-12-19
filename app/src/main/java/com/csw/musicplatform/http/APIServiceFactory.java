package com.csw.musicplatform.http;

/**
 * Created by caisw on 2017/12/4.
 */

public class APIServiceFactory {

    public static FileService getFileExplorerService(String host) {
        return RetrofitFactory.getRetrofit(host).create(FileService.class);
    }


}
