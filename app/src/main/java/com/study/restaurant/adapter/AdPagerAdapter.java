package com.study.restaurant.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.restaurant.fragment.AdFragment;

public class AdPagerAdapter extends FragmentStatePagerAdapter {

    public AdPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return AdFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 10;
    }
}