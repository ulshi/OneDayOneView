package com.cupster.materiallayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.amap.api.maps.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  Encoding by Cupster on 2020/1/6 10:17.
 *  int SCROLL_FLAG_ENTER_ALWAYS	W((entering) / (scrolling on screen))下拉的时候，这个View也会跟着滑出。
 *  int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED	另一种enterAlways，但是只显示折叠后的高度。
 *  int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED	((exiting) / (scrolling off screen))上拉的时候，这个View会跟着滑动直到折叠。
 *  int SCROLL_FLAG_SCROLL	这个View将会响应Scroll事件
 *  int SCROLL_FLAG_SNAP	在Scroll滑动事件结束以前 ，如果这个View部分可见，那么这个View会停在最接近当前View的位
 *
 *
 *  ***
 *     AppBarLayout必须作为CoordinatorLayout的直接子View，否则它的大部分功能将不会生效，如layout_scrollFlags等
 *
 */
public class ElemaMap4DetailActivity extends AppCompatActivity {


    MapView mapView;
    NestedScrollView nestedScrollView;
    CoordinatorLayout.LayoutParams layoutParams;

    int mBeginMargin = 17;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elema);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinate);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView tvFloatBar = findViewById(R.id.float_action_bar);
        TextView tvContent = findViewById(R.id.content);
        nestedScrollView = findViewById(R.id.nestScrollView);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        layoutParams = (CoordinatorLayout.LayoutParams) nestedScrollView.getLayoutParams();
        layoutParams.setMargins(mBeginMargin, 0, mBeginMargin, 0);
        nestedScrollView.setLayoutParams(layoutParams);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Log.e("i+++",i+"");
                float a = (float) mBeginMargin / appBarLayout.getTotalScrollRange();
                int side = (int) Math.rint(a * i + mBeginMargin);
                layoutParams.setMargins(side, 0, side, 0);
                nestedScrollView.setLayoutParams(layoutParams);
                if (Math.abs(i) > 0) {
                    //方式一：逐渐不透明
                    float alpha = (float) Math.abs(i) / appBarLayout.getTotalScrollRange();
//                    appBarLayout.setAlpha(alpha);
                    //方式二：到顶再处理
                    if (alpha == 1 ){
                        appBarLayout.setAlpha(1);
                    }else {
                        appBarLayout.setAlpha(0);
                    }
                    nestedScrollView.getBackground().mutate().setAlpha(Math.round(alpha * 255));
                } else {
                    appBarLayout.setAlpha(0);
                    nestedScrollView.getBackground().mutate().setAlpha(0);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    private List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i =0 ; i<18 ;i++){
            data.add("==="+i +"===");
        }
        return data;
    }
}
