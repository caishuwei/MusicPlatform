package com.csw.musicplatform.bean.http;

/**
 * 文件对象
 * Created by caisw on 2017/12/5.
 */

public class Drive extends File {

    /**
     * {
     * "path":"C:\",
     * "name":"本地磁盘 (C:)",
     * "maxSize":136343187456,
     * "freeSize":102108315648
     * }
     */
    private String path;
    private String name;
    private long maxSize;
    private long freeSize;

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public String getFilePath() {
        return path;
    }

    @Override
    public long getFileSize() {
        return maxSize - freeSize;
    }

}
