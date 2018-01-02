package com.bicyclerent.feixingbike.utils;

import android.support.v7.app.ActionBar;

/**
 * Created by 刺雒 on 2017/2/11.
 */
public class ActionBarUtil {
    private static  ActionBarUtil sActionBarUtil = null;
    public static ActionBarUtil getInstance(ActionBar actionBar,String text){
        if(sActionBarUtil == null){
            synchronized (ActionBarUtil.class){
                sActionBarUtil = new ActionBarUtil();
            }
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle(text);
        }
        return sActionBarUtil;
    }

}
