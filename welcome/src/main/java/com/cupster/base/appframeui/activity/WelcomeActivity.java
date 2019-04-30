package com.cupster.base.appframeui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cupster.base.appframeui.R;
import com.cupster.base.appframeui.utils.ActivityManager;
import com.cupster.base.appframeui.utils.SpUtil;
import com.cupster.base.appframeui.utils.ToastShow;
import com.cupster.base.appframeui.widgets.adapter.WelcomePageAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private long mExitTime;//计算返回键 连按退出
    private MyHandler handler;

    private Button btn_into;
    //实例化图片资源
    int[] images_welcome = new int[]{R.drawable.test_yard, R.drawable.test_walk};

    private  int SDK_PERMISSION_REQUEST = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler(this);
        logicOperate();
        getPersimmions();
    }



    /**
     * 逻辑处理
     */
    private void logicOperate() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        btn_into = findViewById(R.id.welcome_btn_skip);
        btn_into.setOnClickListener(this);
        //安装后第一次打开，viewpager展示欢迎使用
        if (SpUtil.isFirstInstore()) {
            initViewPager();
            SpUtil.setFirstInstore(false);
        } else {//展示AD界面，1000毫秒后跳转
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActivityManager.startActivity(WelcomeActivity.this, MainActivity.class);
                    finish();
                }
            }, 1000);
        }
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {//TODO 动画添加

        ArrayList<View> viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = images_welcome.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(images_welcome[i]);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }
        //View集合初始化好后，设置Adapter
        ViewPager viewPager = findViewById(R.id.welcome_viewpager);
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setAdapter(new WelcomePageAdapter(viewList));
        //设置滑动监听
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastShow.onLine(getResources().getString(R.string.toast_keyback_exit));
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_btn_skip:
                SpUtil.setFirstInstore(false);
                ActivityManager.startActivity(this,MainActivity.class, Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                break;
            case R.id.welcome_btn_detail:
                //WebView 查看广告详情
                break;
            case R.id.welcome_image:
                //WebView 查看广告详情
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则点击进入
        if (position == images_welcome.length - 1) {
            btn_into.setText(R.string.btn_name_into);
        } else {
            btn_into.setText(R.string.btn_name_skip);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限、打电话为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            //打电话
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CALL_PHONE);
            }
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_DENIED) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            //相机
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                permissions.add(Manifest.permission.CAMERA);
            }

            /*
             * 读写权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
             */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }


            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }


    private static class MyHandler extends Handler {
        private WeakReference<WelcomeActivity> activityWeakReference;

        public MyHandler(WelcomeActivity activity) {
            activityWeakReference = new WeakReference<WelcomeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            WelcomeActivity activity = activityWeakReference.get();
            if (activity != null) {

            }
        }
    }

}
