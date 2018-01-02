package com.bicyclerent.feixingbike.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by 刺雒 on 2017/1/17.
 */
public class ScreenSizeUtil {
    public static int getHeight(Context context){
        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);
        return  wm.getDefaultDisplay().getHeight();
    }

    public static int px2dip(Context context,float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue /scale + 0.5f);
    }

    public static int dip2px(Context context,float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale +0.5f);
    }

    public static int getWidth(Context context){
        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }
}
