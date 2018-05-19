package com.study.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.study.restaurant.fragment.FindRestaurantFragment;
import com.study.restaurant.fragment.MangoPickFragment;
import com.study.restaurant.fragment.MyInformationFragment;
import com.study.restaurant.fragment.NewsFragment;
import com.study.restaurant.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    BottomNavigationView navigation;
    FindRestaurantFragment findRestaurantFragment;
    MyInformationFragment myInformationFragment;
    NewsFragment newsFragment;
    RegisterFragment registerFragment;
    MangoPickFragment mangoPickFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_register:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_information:
                    pager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_register);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_information);
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
