package com.cupster.lib.views.viewpager;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    String [] botoom = {"首页","列表","发现","更多"};
    int [] imgs ={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout_main);
//        tabLayout.addTab(tabLayout.newTab().setText(setImageSpan("首页",R.mipmap.ic_launcher)));//此方式无法对齐
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabItemView(R.mipmap.ic_launcher,"首页0")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabItemView(R.mipmap.ic_launcher,"列表0")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabItemView(R.mipmap.ic_launcher,"发现0")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(setTabItemView(R.mipmap.ic_launcher,"更多0")));

        findViewById(R.id.content_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FirstFragment firstFragment = new FirstFragment();
        fragmentTransaction.add(R.id.content_main,firstFragment);
        fragmentTransaction.commit();


//        ViewPager viewPager = findViewById(R.id.viewpager_bottom);
//        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
////        为绑定的tablayout 设置自定义view
//        for (int i = 0; i<adapter.getCount();i++){
//            tabLayout.getTabAt(i).setCustomView(adapter.setCustomView(i));
//        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FirstFragment.newInstance(botoom[position]);
        }

        @Override
        public int getCount() {
            return botoom.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return botoom[position];
        }
        /**
         * 设置自定义view
         * @param position
         * @return
         */
        public View setCustomView(int position) {
            View view = View.inflate(MainActivity.this, R.layout.item_tab_custom_view, null);
            ImageView iv = (ImageView) view.findViewById(R.id.tab_item_img);
            TextView tv = (TextView) view.findViewById(R.id.tab_item_tv);
            iv.setImageResource(imgs[position]);
            tv.setText(botoom[position]);
            return view;
        }
    }

    /**
     * 设置tab item 的自定义view
     * @param drawable
     * @param tabName
     * @return
     */
    private View setTabItemView (int drawable , String tabName ){
        View tab = View.inflate(this,R.layout.item_tab_custom_view,null);
        ImageView img = tab.findViewById(R.id.tab_item_img);
        TextView tv = tab.findViewById(R.id.tab_item_tv);
        img.setImageResource(drawable);
        tv.setText(tabName);
        return tab;
    }


    /**
     * 图文混排
     * @param string
     * @param drawableId
     * @return
     */
    @NonNull
    private SpannableString setImageSpan(String string, int drawableId) {
        SpannableString ss = new SpannableString("  "+string);
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable);
        ss.setSpan(imageSpan,0,1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
