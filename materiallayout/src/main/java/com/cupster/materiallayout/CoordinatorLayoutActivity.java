package com.cupster.materiallayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

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
 *              1.scroll 表示CollapsingToolbarLayout可以滚动（不设置的话头部的ImageView将不能折叠）
 *              2.enterAlways 表示底部的滚动控件只要向下滚动，头部就显示出来
 *              3.enterAlwaysCollapsed 表示当底部滚动控件滚动见顶时，头部显示出来
 *              4.exitUntilCollapsed 表示头部折叠到最小高度时（Toolbar的高度），就不再折叠
 *              5.snap 表示在滑动过程中如果停止滑动，则头部会就近折叠（要么恢复原状，要么折叠成一个Toolbar）
 *
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cordinate);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinate);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        Toolbar toolbar = findViewById(R.id.toolbar);

        RecyclerView rv = findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        RvAdapter adapter = new RvAdapter(this , getDatas());
        rv.setAdapter(adapter);



    }

    private List<String> getDatas() {
        List<String> data = new ArrayList<>();
        for (int i =0 ; i<18 ;i++){
            data.add("==="+i +"===");
        }
        return data;
    }
}
