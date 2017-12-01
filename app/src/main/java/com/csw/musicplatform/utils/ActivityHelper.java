package com.csw.musicplatform.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by caisw on 2017/12/1.
 */

public class ActivityHelper {

    /**
     * 设置fragment到容器中
     *
     * @param fm            碎片管理器
     * @param containerId   容器id
     * @param fragmentClass 碎片类型
     * @param tag           碎片标识
     * @param data          初始化数据
     * @return fragment
     */
    public static <T extends Fragment> T setFragment(FragmentManager fm, int containerId, Class<T> fragmentClass, String tag, Bundle data) {
        T result = null;
        try {
            Fragment fragment = fm.findFragmentByTag(tag);
            if (fragment != null) {
                result = (T) fragment;
            } else {
                result = (T) fragmentClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            if (result.isAdded()) {
                if (result.isDetached()) {
                    fragmentTransaction.attach(result);
                }
            } else {
                if (data != null) {
                    result.setArguments(data);
                }
                fragmentTransaction.replace(containerId, result, tag);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
        return result;
    }


}
