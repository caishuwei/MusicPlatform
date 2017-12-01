package com.csw.musicplatform.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 日志打印工具类
 * Created by csw on 2017/4/2.
 */

public class LogUtils {
    public static final String TAG = "com.csw.musicplatform";//默认Tag
    public static boolean DEBUG = true;//调试状态

    public static void v(String msg) {
        v(null, msg);
    }

    public static void d(String msg) {
        d(null, msg);
    }

    public static void i(String msg) {
        i(null, msg);
    }

    public static void w(String msg) {
        w(null, msg);
    }

    public static void e(String msg) {
        e(null, msg);
    }

    public static void v(String tag, String msg) {
        print(Log.VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        print(Log.DEBUG, tag, msg);
    }

    public static void i(String tag, String msg) {
        print(Log.INFO, tag, msg);
    }

    public static void w(String tag, String msg) {
        print(Log.WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        print(Log.ERROR, tag, msg);
    }

    public static void print(int logType, String tag, String msg) {
        if (DEBUG) {
            if (TextUtils.isEmpty(tag)) {
                tag = TAG;
            }
            switch (logType) {
                case Log.VERBOSE:
                    Log.v(tag, msg);
                    break;
                case Log.DEBUG:
                    Log.d(tag, msg);
                    break;
                case Log.INFO:
                    Log.i(tag, msg);
                    break;
                case Log.WARN:
                    Log.w(tag, msg);
                    break;
                case Log.ERROR:
                    Log.e(tag, msg);
                    break;
                default:
                    break;
            }
        }
    }

}
