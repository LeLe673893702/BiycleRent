package com.bicyclerent.feixingbike.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by 刺雒 on 2017/1/29.
 */
public class SharedPreferencesUtil {
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;

    /*存数据调用*/
    public SharedPreferencesUtil(Context context,String name){
        mEditor = context.getSharedPreferences(name,Context.MODE_APPEND).edit();
        mPreferences = context.getSharedPreferences(name,Context.MODE_APPEND);
    }

    /*存取数据*/
    public void setData(HashMap<String,Object> dataMap){
        Set<String> keys = dataMap.keySet();
        for(String key:keys){
            judgeObjectType(key,dataMap.get(key));
        }
        //数据提交
        mEditor.commit();
    }


    /*读取字符串数据*/
    public String getStringData(String key){
        return mPreferences.getString(key,"");
    }

    /*读取int数据*/
    public Integer getIntData(String key){
        return mPreferences.getInt(key, 0);
    }

    /*读取Float数据*/
    public Float getFloatData(String key){
        return mPreferences.getFloat(key, 0);
    }

    /*读取Boolean数据*/
    public Boolean getBooleanData(String key){
        return mPreferences.getBoolean(key,false);
    }

    /*读取Long数据*/
    public Long getLongData(String key){
        return mPreferences.getLong(key,0);
    }

    /*判断是什么类型的*/
    private void judgeObjectType(String key,Object value){
        if(value instanceof String){
            mEditor.putString(key, (String) value);
            return;
        }
        if(value instanceof Boolean){
            mEditor.putBoolean(key, (Boolean) value);
            return;
        }
        if(value instanceof Float){
            mEditor.putFloat(key, (Float) value);
            return;
        }
        if(value instanceof Integer){
            mEditor.putInt(key, (int) value);
            return;
        }
        if(value instanceof Long){
            mEditor.putLong(key,(Long)value);
            return;
        }
    }


}
