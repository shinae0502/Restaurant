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

public class NewsFragment extends Fragment {

    /**
     * 뉴스 뷰페이저
     */
    ViewPager newsVp;

    /**
     * 뉴스 탭레이아웃
     */
    TabLayout tlNews;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
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
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        newsVp = v.findViewById(R.id.newsVp);
        tlNews = v.findViewById(R.id.tlNews);

        newsVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlNews));
        tlNews.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                newsVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        newsVp.setAdapter(new NewsVpAdt(getFragmentManager()));
        return v;
    }

    private class NewsVpAdt extends FragmentStatePagerAdapter {
        public NewsVpAdt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new NewsListFragment();
        }

        @Override
        public int getCount() {
            return tlNews.getTabCount();
        }
    }
}
