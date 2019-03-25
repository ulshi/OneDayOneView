package com.cupster.swipeback;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        //
        BGASwipeBackHelper.init(this,null);
    }




}
