package com.csw.musicplatform.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SP文件读写操作类，封装读取默认值，支持存值方法链式调用
 * Created by caisw on 2017/12/1.
 */

public class SpHelper {
    private static final String TAG = "SPHelper";
    private final SharedPreferences mSharedPreferences;
    private final String spName;
    private final int mode;

    public SpHelper(String spName, int mode, Context context) {
        this.spName = spName;
        this.mode = mode;
        this.mSharedPreferences = context.getSharedPreferences(
                spName, mode);
    }

    /**
     * 打印所有键值对
     */
    public void print() {
        Map<String, ?> map = mSharedPreferences.getAll();
        if (map != null && map.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("print:").append(spName).append("(").append(mode)
                    .append(")").append(" map-->");
            for (Map.Entry<String, ?> set : map.entrySet()) {
                sb.append(set.getKey()).append(" = ")
                        .append(set.getValue()).append("\n");
            }
            LogUtils.i(TAG, sb.toString());
        }
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        mSharedPreferences.edit().clear().commit();
    }

    public SpHelper putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).commit();
        return this;
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String def) {
        return mSharedPreferences.getString(key, def);
    }

    public SpHelper putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).commit();
        return this;
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return mSharedPreferences.getInt(key, def);
    }

    public SpHelper putBoolean(String key, Boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).commit();
        return this;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public SpHelper putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).commit();
        return this;
    }

    public long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public long getLong(String key, long def) {
        return mSharedPreferences.getLong(key, def);
    }

    /**
     * 移除字段
     */
    public void removeString(String key) {
        mSharedPreferences.edit().remove(key).commit();
    }

}
