package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.activity.MainActivity;

public class MangoPickFragment extends Fragment {

    ViewPager viewPager;
    TopListFragment topListFragment;
    StoryFragment storyFragment;
    TabLayout tabLayout;

    public MangoPickFragment() {
        // Required empty public constructor
    }

    public static MangoPickFragment newInstance() {
        MangoPickFragment fragment = new MangoPickFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mango_pick, container, false);
        viewPager = v.findViewById(R.id.viewPager);
        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager()));
        // Creating TabPagerAdapter adapter
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    public class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (storyFragment == null) {
                        storyFragment = StoryFragment.newInstance();
                    }
                    return storyFragment;
                case 1:
                    if (topListFragment == null) {
                        topListFragment = TopListFragment.newInstance();
                    }
                    return topListFragment;
                default:
                    return StoryFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
