package com.cupster.base.appframeui.utils;

import android.widget.Toast;

import com.cupster.base.appframeui.MyApplication;

public class ToastShow {

    private static final boolean ISDEBUG = true;


    public static void onLine(String text){
        Toast.makeText(MyApplication.getMyAppContext(),text,Toast.LENGTH_SHORT).show();
    }

    public static void debug(String text){
        if (!ISDEBUG){
            return;
        }
        Toast.makeText(MyApplication.getMyAppContext(),text,Toast.LENGTH_SHORT).show();
    }
}
