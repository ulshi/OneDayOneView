package com.cupster.base.appframeui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cupster.base.appframeui.utils.ActivityManager;

import java.lang.ref.WeakReference;

public abstract class BaseActivity extends AppCompatActivity {

//    abstract void pausedSave(Bundle saveInstance);
//    abstract void restoreData(Bundle restoreInstance);

    protected WeakReference<Activity> weakThis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakThis = new WeakReference<Activity>(this);
        ActivityManager.addActivity(weakThis);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        restoreData(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        pausedSave(outState);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.removeActivity(weakThis);
        super.onDestroy();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return super.onSupportNavigateUp();
//    }
}
