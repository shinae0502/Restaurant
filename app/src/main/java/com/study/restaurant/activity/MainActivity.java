package com.study.restaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.study.restaurant.R;
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.fragment.FindRestaurantFragment;
import com.study.restaurant.fragment.MangoPickFragment;
import com.study.restaurant.fragment.MyInformationFragment;
import com.study.restaurant.fragment.NewsFragment;
import com.study.restaurant.fragment.RegisterFragment;


public class MainActivity extends AppCompatActivity implements FunctionImpl.Main {

    ViewPager pager;
    FindRestaurantFragment findRestaurantFragment;
    MyInformationFragment myInformationFragment;
    NewsFragment newsFragment;
    RegisterFragment registerFragment;
    MangoPickFragment mangoPickFragment;

    LinearLayout r1;
    LinearLayout r2;
    LinearLayout r3;
    LinearLayout r4;
    LinearLayout r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = findViewById(R.id.r1);
        r1.setSelected(true);
        pager = findViewById(R.id.pager);
        pager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(4);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, MainActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void goFindRestaurant(View v) {
        pager.setCurrentItem(0);
    }

    @Override
    public void goMangoPick(View v) {
        pager.setCurrentItem(1);
    }

    @Override
    public void goRegister(View v) {
    }

    @Override
    public void goNews(View v) {
        pager.setCurrentItem(2);
    }

    @Override
    public void goMyInformation(View v) {
        pager.setCurrentItem(3);
    }

    public class MainPageAdapter extends FragmentStatePagerAdapter {

        public MainPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (findRestaurantFragment == null) {
                        findRestaurantFragment = FindRestaurantFragment.newInstance();
                    }
                    return findRestaurantFragment;
                case 1:
                    if (mangoPickFragment == null) {
                        mangoPickFragment = MangoPickFragment.newInstance();
                    }
                    return mangoPickFragment;
                case 2:
                    if (newsFragment == null) {
                        newsFragment = NewsFragment.newInstance();
                    }
                    return newsFragment;
                case 3:
                    if (myInformationFragment == null) {
                        myInformationFragment = MyInformationFragment.newInstance();
                    }
                    return myInformationFragment;
                default:
                    return FindRestaurantFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
