package com.cupster.base.appframeui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ActivityManager {

    public static List<WeakReference<Activity>> activities = new ArrayList<>();

    public static void addActivity(WeakReference<Activity> activity){
        if (activity.get()==null){
            return;
        }
        activities.add(activity);
    }

    /**
     * 移出队列
     * @param activity
     */
    public static void removeActivity(WeakReference<Activity> activity){
        if (activity.get()==null){
            return;
        }
        activities.remove(activity);
    }

    /**
     * 关闭其他activity
     * @param activity
     */
    public static void finishOthers(WeakReference<Activity> activity){
        removeActivity(activity);
        finishAll();
        addActivity(activity);
//        for (Activity other :activities){
//            if (other != activity){
//                other.finish();
//            }
//        }
    }

    /**
     * 退出所有Activity
     */
    public static void finishAll(){
        for (WeakReference<Activity> activity : activities){
            if (activity.get()==null){
                continue;
            }
            activity.get().finish();
        }
        activities.clear();
    }

    /**
     * 完全退出APP
     */
    public static void ExitEntirely(){
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * startActivity without flag
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls ){
        Intent intent = new Intent(context,cls);
        context.startActivity(intent);
    }

    /**
     * startActivity with int flag
     * @param context
     * @param cls
     * @param flag
     */
    public static void startActivity(Context context ,Class cls ,int flag){
        Intent intent = new Intent(context,cls);
        intent.addFlags(flag);
        context.startActivity(intent);
    }

    /**
     * startActivity with int array flag
     * @param context
     * @param cls
     * @param flag
     */
    public static void startActivity(Context context,Class cls,int[] flag ){
        Intent intent = new Intent(context,cls);
        for (int f :flag){
            intent.addFlags(f);
        }
        context.startActivity(intent);
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commitNow();
    }

}
