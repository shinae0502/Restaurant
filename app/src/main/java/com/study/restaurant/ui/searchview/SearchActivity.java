package com.study.restaurant.ui.searchview;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.R;
import com.study.restaurant.databinding.ActivitySearchBinding;
import com.study.restaurant.fragment.FindFriendFragment;
import com.study.restaurant.fragment.SearchFragment;
import com.study.restaurant.model.SearchView;

public class SearchActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager searchPager;
    SearchView searchView;

    ActivitySearchBinding activitySearchBinding;

    SearchFragment recommandSearchFragment;
    SearchFragment recentSearchFragment;
    FindFriendFragment findFriendFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchView = new SearchView();
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        activitySearchBinding.setSearchView(searchView);
        tabLayout = activitySearchBinding.tabLayout;
        searchPager = activitySearchBinding.searchPager;
        searchPager.setAdapter(new SearchPagerAdapter(getSupportFragmentManager()));

        searchPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        searchPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                searchView.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                searchPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SearchActivity.class);
        appCompatActivity.startActivity(intent);
    }

    class SearchPagerAdapter extends FragmentStatePagerAdapter {

        public SearchPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                if (recommandSearchFragment == null) {
                    recommandSearchFragment = new SearchFragment();
                    recommandSearchFragment.setType(SearchFragment.Type.RECOMMAND);
                    recommandSearchFragment.setSearchView(searchView);

                }
                return recommandSearchFragment;
            } else if (position == 1) {
                if (recentSearchFragment == null) {
                    recentSearchFragment = new SearchFragment();
                    recentSearchFragment.setType(SearchFragment.Type.RECENT);
                    recentSearchFragment.setSearchView(searchView);
                }
                return recentSearchFragment;
            } else if (position == 2) {
                if (findFriendFragment == null)
                    findFriendFragment = new FindFriendFragment();
                return findFriendFragment;
            }
            return null;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return tabLayout.getTabCount();
        }
    }
}
