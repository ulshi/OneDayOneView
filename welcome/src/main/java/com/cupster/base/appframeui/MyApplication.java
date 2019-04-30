package com.cupster.base.appframeui;

import android.app.Application;
import android.content.Context;

import com.cupster.base.appframeui.utils.LogUtil;

public class MyApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        appContext = getApplicationContext();
    }

    public static Context getMyAppContext(){
        return appContext;
    }





}
