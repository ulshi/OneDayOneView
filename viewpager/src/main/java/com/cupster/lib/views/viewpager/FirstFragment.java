package com.cupster.lib.views.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private static final String TAG = "TAG";
    private static final String[] tabNames ={"要闻","热点","厦门","科技","国内","国外","影视","八卦"};
    private static final int[] tabImgs ={R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher
            ,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,};
    TabLayout tabLayout ;
    ViewPager viewPager;

    public static TestFragment newInstance(String tag){
        Bundle bundle = new Bundle();
        bundle.putString(TAG,tag);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragFirst = inflater.inflate(R.layout.fragment_firstpager,container,false);
        tabLayout = fragFirst.findViewById(R.id.tablayout_first);
        viewPager =fragFirst.findViewById(R.id.viewpager_main);
        initView();
        return fragFirst;
    }


    private void initView(){
        setTabs(tabLayout);//会被清除
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //setupWithViewPager()方法，则使用TabLayout.addtab()方法无效，
        // TabLayout会清除之前添加的所有tabs，并将根据Viewpager的页数添加Tab，
        // Tab标题为对应页通过getPageTitle()返回的文本
        tabLayout.setupWithViewPager(viewPager);
        //点击无效是因为布局，ViewPager把tablayout 覆盖，导致点击无效
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }



    private static void setTabs(TabLayout tLayout ){
        for (String s : tabNames){
            tLayout.addTab(tLayout.newTab().setText(s+"00"));
        }
    }

    /**
     * 设置tab item 的自定义view
     *
     * @param position
     * @return
     */
//    private View setTabItemView (int position ){
//        View tab = View.inflate(this,R.layout.item_tab_custom_view,null);
//        ImageView img = tab.findViewById(R.id.tab_item_img);
//        TextView tv = tab.findViewById(R.id.tab_item_tv);
//        img.setImageResource(tabImgs[position]);
//        tv.setText(tabNames[position]);
//        return tab;
//    }


    /**
     * FragmentPagerAdapter
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(tabNames[position]);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }
    }


}
