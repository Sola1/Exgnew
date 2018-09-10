package com.credit.exg.exg.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhangjiaqi on 18/8/28.
 */

public class RepairPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;

    //fragment
    private List<Fragment> fragments;

    public RepairPagerAdapter(FragmentManager fm , List<Fragment> fragments, String[] titles  ) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
