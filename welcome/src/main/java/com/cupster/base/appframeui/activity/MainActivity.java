package com.cupster.base.appframeui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cupster.base.appframeui.R;
import com.cupster.base.appframeui.base.BaseActivity;
import com.cupster.base.appframeui.fragment.MainFragment;
import com.cupster.base.appframeui.utils.ActivityManager;
import com.cupster.base.appframeui.utils.SpUtil;
import com.cupster.base.appframeui.utils.ToastShow;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    MainFragment mMainFragment;
    private long mCurrentTimeMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBaseView();
//        initContentView();//TODO 主要内容区域
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((DrawerLayout) findViewById(R.id.main_drawer_layout)).openDrawer(GravityCompat.START);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long downTime = System.currentTimeMillis();
            if (downTime - mCurrentTimeMillis > 2000) {
                ToastShow.onLine(" 再按一次返回键将退出");
                mCurrentTimeMillis = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initBaseView() {
        //toolbar 部分
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_person_nav);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //navigationview
        NavigationView navigationView = findViewById(R.id.main_navigation_view);
        setupDrawerContent(navigationView);
        View headView = navigationView.inflateHeaderView(R.layout.activity_main_navheader);
        ImageView headImg = headView.findViewById(R.id.main_navi_img);
        headImg.setImageResource(R.drawable.icon_left_default_head);//todo Glide 裁剪、加载、
        TextView headName = headView.findViewById(R.id.main_navi_tv_name);
        headName.setText("Cupster");
        TextView headPhone = headView.findViewById(R.id.main_navi_tv_phone);
        headPhone.setText("18960227522");
        TextView headCompany = headView.findViewById(R.id.main_navi_tv_company);
        headCompany.setText("德邦");


        ImageView imageView = findViewById(R.id.main_image);
        imageView.setOnClickListener(this);
    }

    private void initContentView() {
        if (null == mMainFragment) {
            mMainFragment = new MainFragment();
        }
        mMainFragment.setArguments(getIntent().getExtras());
        if (!mMainFragment.isAdded()) {
            ActivityManager.addFragmentToActivity(getSupportFragmentManager(), mMainFragment, R.id.main_content, MainFragment.class.getSimpleName());
        }

    }


    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_order:
                                menuItem.setChecked(true);
                                navigationView.getMenu().getItem(1).setChecked(false);
                                navigationView.getMenu().getItem(2).setChecked(false);
                                navigationView.getMenu().getItem(3).setChecked(false);
                                navigationView.getMenu().getItem(7).setChecked(false);
                                navigationView.getMenu().getItem(8).setChecked(false);
//                                    mMainFragment.goToMyOrder();
                                return true;
                            case R.id.menu_fuel_up:
                                menuItem.setChecked(true);
                                navigationView.getMenu().getItem(0).setChecked(false);
                                navigationView.getMenu().getItem(2).setChecked(false);
                                navigationView.getMenu().getItem(3).setChecked(false);
                                navigationView.getMenu().getItem(7).setChecked(false);
                                navigationView.getMenu().getItem(8).setChecked(false);
//                                    mMainFragment.goToFuelUp();
                                return true;
                            case R.id.menu_wallet:
                                menuItem.setChecked(true);
                                navigationView.getMenu().getItem(0).setChecked(false);
                                navigationView.getMenu().getItem(1).setChecked(false);
                                navigationView.getMenu().getItem(3).setChecked(false);
                                navigationView.getMenu().getItem(7).setChecked(false);
                                navigationView.getMenu().getItem(8).setChecked(false);
//                                    mMainFragment.goToWallet();
                                return true;
                            case R.id.menu_setting:
                                menuItem.setChecked(true);
                                navigationView.getMenu().getItem(0).setChecked(false);
                                navigationView.getMenu().getItem(1).setChecked(false);
                                navigationView.getMenu().getItem(2).setChecked(false);
                                navigationView.getMenu().getItem(7).setChecked(false);
                                navigationView.getMenu().getItem(8).setChecked(false);
//                                    mMainFragment.goToSetting();
                                return true;
                            case R.id.menu_phone:
                                menuItem.setChecked(true);
                                navigationView.getMenu().getItem(0).setChecked(false);
                                navigationView.getMenu().getItem(1).setChecked(false);
                                navigationView.getMenu().getItem(2).setChecked(false);
                                navigationView.getMenu().getItem(3).setChecked(false);
                                navigationView.getMenu().getItem(8).setChecked(false);
//                                    mMainFragment.callPhone();
                                return true;
                            default:
                                return true;
                        }
                    }
                    //                        mDrawerLayout.closeDrawers();
                }
         );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_image:
                ToastShow.debug("message");
                break;
            default:
                break;
        }
    }
}
