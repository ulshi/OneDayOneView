package com.cupster.materiallayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.cupster.materiallayout.fragment.RecyclerFragment;
import com.cupster.materiallayout.viewpager.ModelPagerAdapter;
import com.cupster.materiallayout.viewpager.PagerManager;

/**
 *  Encoding by Cupster on 2020/1/6 10:22.
 *
 * CollapsingToolbarLayout是工具栏的包装器,它通常作为AppBarLayout的孩子。主要实现以下功能
 - Collapsing title（可以折叠 的 标题 ）
 - Content scrim（内容装饰），当我们滑动的位置 到达一定阀值的时候，内容 装饰将会被显示或者隐藏
 - Status bar scrim（状态栏布）
 - Parallax scrolling children，滑动的时候孩子呈现视觉特差效果
 - Pinned position children，固定位置的 孩子
 *
 *    int COLLAPSE_MODE_OFF	(这个 View将会 呈现正常的结果，不会表现出折叠效果）
 *    int COLLAPSE_MODE_PARALLAX	（在滑动的时候这个View 会呈现 出 视觉特差效果 ）
 *    int COLLAPSE_MODE_PIN	（当这个View到达 CollapsingToolbarLayout的底部的时候，这个View 将会被放置，即代替整个CollapsingToolbarLayout）
 */

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinate);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Collapsing");
        ImageView img = findViewById(R.id.img);
        TabLayout tabLayout = findViewById(R.id.tablayotu);
        ViewPager viewPager = findViewById(R.id.viewpager);

        PagerManager pagerManager = new PagerManager();

        pagerManager.addFragment(RecyclerFragment.newInstance("1"),"first");
        pagerManager.addFragment(RecyclerFragment.newInstance("2"),"second");
        pagerManager.addFragment(RecyclerFragment.newInstance("3"),"third");

        ModelPagerAdapter adapter= new ModelPagerAdapter(getSupportFragmentManager() ,pagerManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i =0 ;i <tabLayout.getTabCount() ; i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(adapter.getPageTitle(i));
        }


    }
}
