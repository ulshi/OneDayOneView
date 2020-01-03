package com.cupster.navigationview;

import android.app.NativeActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NavigationViewActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationview);

         mDrawerLayout= findViewById(R.id.design_drawer_layout);

        NavigationView navView = (NavigationView) findViewById(R.id.design_nav_viewa);

        //在布局文件中添加app:itemIconTint="@color/blue"属性，表示设置图片的颜色全都为蓝色
        //让图片就是显示他本身的颜色
        navView.setItemIconTintList(null);

        View head = navView.getHeaderView(0);//获得头布局
        CircleImageView circleImageView = head.findViewById(R.id.nav_cirI_head);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(v);
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_menu_call:
                        Toast.makeText(NavigationViewActivity.this,"Call",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_friends:
                        Toast.makeText(NavigationViewActivity.this,"Friends",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_loaction:
                        Toast.makeText(NavigationViewActivity.this,"Loaction",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_mail:
                        Toast.makeText(NavigationViewActivity.this,"Mail",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_menu_cloud:
                        Toast.makeText(NavigationViewActivity.this,"Cloud",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        //关闭滑动菜单
                        mDrawerLayout.closeDrawers();
                }
                //选中此项
                //item.setCheckable(true);
                return false;
            }
        });


    }


    void show(View v) {
        Toast.makeText(NavigationViewActivity.this, "" + v.getClass().getSimpleName(), Toast.LENGTH_LONG).show();
    }

}
