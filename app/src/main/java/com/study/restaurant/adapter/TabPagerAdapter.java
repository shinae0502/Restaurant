package com.study.restaurant.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.restaurant.fragment.StoryFragment;
import com.study.restaurant.fragment.TopListFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    /*
        생성자, 추상 Method (ctrl+I) 생성
        탭을 추가하고, 탭을 관리하는 adapter를 추가
        탭이 변할 때 마다 position을 받아 Fragment를 전환
     */

    private int tabCount;  // 탭 수

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) { // 현재 Tab을 Return
        switch (position){
            case 0 :
                StoryFragment storyFragment =  new StoryFragment(); // case 전환
                return storyFragment;
            case 1 :
                TopListFragment topListFragment =  new TopListFragment();
                return topListFragment;
            default:
                return null;
        }
    } // --------------------------------- getItem()

    @Override
    public int getCount() {
        return tabCount;
    }

} // =============================================================  class TabPagerAdapter
