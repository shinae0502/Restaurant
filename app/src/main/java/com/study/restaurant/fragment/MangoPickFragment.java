package com.study.restaurant.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;
import com.study.restaurant.model.TopList;

public class MangoPickFragment extends Fragment {

    ViewPager mangoPickViewPager;
    TabLayout mangoPickTabLayout;

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
        mangoPickViewPager = v.findViewById(R.id.mangoPickViewPager);
        mangoPickTabLayout = v.findViewById(R.id.mangoPickTabLayout);

        // 페이지와 탭 레이아웃 연결해주기
        mangoPickTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mangoPickViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mangoPickViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mangoPickTabLayout));

        mangoPickViewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    StoryFragment storyFragment = new StoryFragment();
                    return storyFragment;
                } else {
                    TopListFragment topListFragment = new TopListFragment();
                    return topListFragment;
                }
            }

            @Override
            public int getCount() {
                return mangoPickTabLayout.getTabCount();
            }
        });

        return v;
    }
}
