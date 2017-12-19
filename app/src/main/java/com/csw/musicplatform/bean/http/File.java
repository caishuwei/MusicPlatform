package com.csw.musicplatform.bean.http;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by caisw on 2017/12/19.
 */

public class File {

    /**
     * "fileName":"tomcat-native.tar.gz",
     * "filePath":"D:\tomcat-native.tar.gz",
     * "fileSize":0,
     * "type":2
     */
    private String fileName;
    private String filePath;
    private long fileSize;
    private int type = -1;//1(文件夹)2(文件)

    private String fileSizeStr;
    private List<File> childFiles = new ArrayList<>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getType() {
        if (type == -1) {
            if (getFileName().contains(".")) {
                type = 2;
            } else {
                type = 1;
            }
        }
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileSizeStr() {
        if (TextUtils.isEmpty(fileSizeStr)) {
            fileSizeStr = getSize(getFileSize());
        }
        return fileSizeStr;
    }

    private String getSize(long fileSize) {
        fileSize = Math.max(0, fileSize);
        int b = (int) (fileSize % 1024);
        int k = (int) ((fileSize / 1024) % 1024);
        int m = (int) ((fileSize / 1024 / 1024) % 1024);
        int g = (int) (fileSize / 1024 / 1024 / 1024);
        if (g != 0) {
            return String.format(Locale.getDefault(), "%d.%02dg", g, (int) (m / 1024f * 100));
        } else if (m != 0) {
            return String.format(Locale.getDefault(), "%d.%02dm", m, (int) (k / 1024f * 100));
        } else {
            return String.format(Locale.getDefault(), "%d.%02dk", k, (int) (b / 1024f * 100));
        }
    }

    public List<File> getChildFiles() {
        return childFiles;
    }
}
