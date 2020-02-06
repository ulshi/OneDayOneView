package com.cupster.materiallayout;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.cupster.materiallayout.fragment.RecyclerFragment;
import com.cupster.materiallayout.viewpager.ModelPagerAdapter;
import com.cupster.materiallayout.viewpager.PagerManager;

public class ViewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinate);
        AppBarLayout appBarLayout = findViewById(R.id.appbar);

        ImageView img = findViewById(R.id.img);
        //TabLayout 在我们滑动的时候最终会停靠在 最顶部，是因为我们没有设置其layout_scrollFlags，即TabLayout是静态的
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
