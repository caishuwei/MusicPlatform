package com.csw.musicplatform.http;

import com.csw.musicplatform.bean.http.Drive;
import com.csw.musicplatform.bean.http.File;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 文件浏览服务
 * Created by caisw on 2017/12/4.
 */

public interface FileService {

    /**
     * 检测服务是否运行
     */
    @GET("index.jsp")
    Observable<String> serverIsRunning();

    /**
     * 获取盘符列表
     */
    @GET("init")
    Observable<List<Drive>> getDriveList();

    /**
     * 获取盘符列表
     */
    @GET("file")
    Observable<List<File>> getChildFileLiseByPath(@Query("path") String path);


}
