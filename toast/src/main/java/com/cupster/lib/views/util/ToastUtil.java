package com.cupster.lib.views.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.cupster.lib.views.MyApplication;
import com.cupster.lib.views.R;

public class ToastUtil {

    private static final boolean ISDEBUG = true;

    public static void onLine(String text ){
        message(true,MSG_NORMAL ,text);
    }

    public static void debug(String text){
        if (!ISDEBUG){
            return;
        }
        message(true,MSG_NORMAL ,text);
    }
    public static void onLineRed(String text  ){
        message(true ,MSG_ERROR,text);
    }

    public static void debugRed(String text){
        if (!ISDEBUG){
            return;
        }
        message(true, MSG_ERROR,text);
    }
    public static void onLineOrange(String text  ){
        message(true ,MSG_INFO ,text);
    }

    public static void debugOrange(String text){
        if (!ISDEBUG){
            return;
        }
        message(true ,MSG_INFO ,text);
    }
    public static void onLineGreen(String text  ){
        message(true,MSG_SUCESS ,text);
    }

    public static void debugGreen(String text){
        if (!ISDEBUG){
            return;
        }
        message(true ,MSG_SUCESS,text);
    }


    private static final int MSG_NORMAL = 1;
    private static final int MSG_INFO = 2;
    private static final int MSG_SUCESS = 3;
    private static final int MSG_ERROR = 4;

    private static void message(boolean isLong,int colorMode,String message){
        //加载Toast布局
        View toastRoot = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.toast_text, null);
        //初始化布局控件
        TextView text = (TextView) toastRoot.findViewById(R.id.txt_toast);
        switch (colorMode){
            case MSG_NORMAL:

                break;
            case MSG_INFO:
                text.setCompoundDrawablesWithIntrinsicBounds(MyApplication.getContext().getResources().getDrawable(R.mipmap.icon_info),null,null,null);
                toastRoot.setBackgroundResource(R.drawable.shape_toast_bg_orange);
                break;
            case MSG_SUCESS:
                text.setCompoundDrawablesWithIntrinsicBounds(MyApplication.getContext().getResources().getDrawable(R.mipmap.icon_ok),null,null,null);
                toastRoot.setBackgroundResource(R.drawable.shape_toast_bg_green);
                break;
            case MSG_ERROR:
                text.setCompoundDrawablesWithIntrinsicBounds(MyApplication.getContext().getResources().getDrawable(R.mipmap.icon_err),null,null,null);
                toastRoot.setBackgroundResource(R.drawable.shape_toast_bg_red);
                break;
            default:
                break;
        }
        //为控件设置属性
        text.setText(message);

        //Toast的初始化
        Toast toastStart = new Toast(MyApplication.getContext());
        //获取屏幕高度
        WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的2/3，不会出现不适配的问题
        toastStart.setGravity(Gravity.TOP, 0, height / 3*2);
        toastStart.setDuration(isLong?Toast.LENGTH_LONG:Toast.LENGTH_SHORT);
        toastStart.setView(toastRoot);
        toastStart.show();
    }

}
