package com.cupster.materiallayout.viewpager;


import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class PagerManager {
    public static PagerManager begin() {
        return new PagerManager();
    }

    public final static String DATA = "data";

    private List<String> titleList;
    private List<Fragment> fragmentList;

    public PagerManager() {
        titleList = new ArrayList<String>();
        fragmentList = new ArrayList<Fragment>();
    }

    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public int getFragmentCount() {
        return fragmentList.size();
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public boolean hasTitles() {
        return titleList.size() != 0;
    }

    public CharSequence getTitle(int position) {
        return titleList.get(position);
    }

    public PagerManager addFragment(Fragment fragment, String title) {
        titleList.add(title);
        addFragment(fragment);
        return this;
    }

    public PagerManager addFragment(Fragment fragment) {
        fragmentList.add(fragment);
        return this;
    }

    public PagerManager setTitles(List<String> titleList) {
        this.titleList = titleList;
        return this;
    }
}
