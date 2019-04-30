package com.cupster.base.appframeui.utils;

import android.content.SharedPreferences;

import com.cupster.base.appframeui.MyApplication;

public class SpUtil {

    private static final String SP_SETTING = "setting";//sp文件setting.xml
    private static final String SP_DATA = "data";//sp文件data.xml

    private static final String FIRST_OPEN = "first_open";//是否  首次安装打开APP，用于控制引导页显示


    /**
     * 获取sp
     * @param spName
     * @return
     */
    public static SharedPreferences getSp(String spName){
        return MyApplication.getMyAppContext().getSharedPreferences(spName,0);
    }

    /**
     * 获取SharedPreference.Editor
     * @param spName
     * @return
     */
    public static SharedPreferences.Editor getSpEditor(String spName){
        return getSp(spName).edit();
    }

    /**
     * 读取是否是首次安装打开
     * @return
     */
    public static boolean isFirstInstore(){
        return getSp(SP_SETTING).getBoolean(FIRST_OPEN,true);
    }

    /**
     * 设置首次打开值
     * @param flag
     */
    public static void setFirstInstore(boolean flag){
        getSpEditor(SP_SETTING).putBoolean(FIRST_OPEN,flag).apply();
    }

    public static void setSettingParam(String key ,String value){
        getSpEditor(SP_SETTING).putString(key,value);
    }

    public static void setKeyValue(String spName ,String key ,String value){
        getSpEditor(spName).putString(key,value).apply();
    }

}
