package com.study.restaurant.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.restaurant.R;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.fragment.SelectRegionFragment;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.util.LOG;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import static android.support.design.widget.TabLayout.*;

public class SelectRegionPopupActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager regionViewPager;
    RegionPagerAdapter regionPagerAdapter;

    HashMap<String, City> cityHashMap = new HashMap<>();
    HashMap<City, ArrayList<Region>> cityRegionMap = new HashMap<>();
    private ArrayList<City> cityArrayList;


    class RegionPagerAdapter extends FragmentStatePagerAdapter {

        public RegionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            City city = (City) tabLayout.getTabAt(position).getTag();
            SelectRegionFragment  selectRegionFragment = new SelectRegionFragment();
            selectRegionFragment.setRegionList(cityRegionMap.get(city));
            return selectRegionFragment;
        }

        @Override
        public int getCount() {
            int count = 0;
            if (tabLayout != null)
                count = tabLayout.getTabCount();
            return count;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_retion_popup);
        regionViewPager = findViewById(R.id.regionViewPager);
        tabLayout = findViewById(R.id.tabLayout);

        findViewById(R.id.dim).setOnClickListener(view -> finishWithAnimation());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //지역 불러오기
        ApiManager.getInstance().getCity(new ApiManager.CallbackListener() {
            @Override
            public void callback(String result) {
                LOG.d(result);
                Type listType = new TypeToken<ArrayList<City>>() {
                }.getType();
                cityArrayList = new Gson().fromJson(result, listType);
                for (City city : cityArrayList) {
                    cityRegionMap.put(city, new ArrayList<Region>());
                    cityHashMap.put(city.getCity_id(), city);
                }

                //지역 불러오기
                ApiManager.getInstance().getRegion(null, new ApiManager.CallbackListener() {
                    @Override
                    public void callback(String result) {
                        LOG.d(result);
                        Type type = new TypeToken<ArrayList<Region>>(){}.getType();
                        ArrayList<Region> regionArrayList = new Gson().fromJson(result, type);

                        for(Region region : regionArrayList)
                        {
                            cityRegionMap.get(cityHashMap.get(region.getCity_id())).add(region);
                        }

                        initRegionTabAndPager();

                    }

                    @Override
                    public void failed(String msg) {

                    }
                });

            }

            @Override
            public void failed(String msg) {

            }
        });
    }

    private void initRegionTabAndPager() {

        //최근지역
        tabLayout.addTab(tabLayout.newTab().setText("최근지역"));
        //내주변
        tabLayout.addTab(tabLayout.newTab().setText("내주변"));
        for(City city : cityArrayList)
        {
            Tab tab = tabLayout.newTab().setText(city.getCity_name());
            tabLayout.addTab(tab);
            tab.setTag(city);
        }

        //텍스트 뒤집기
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) (((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(i)).getChildAt(1));
            tv.setScaleY(-1);
        }

        regionViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        regionPagerAdapter = new RegionPagerAdapter(getSupportFragmentManager());
        regionViewPager.setAdapter(regionPagerAdapter);
        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                regionViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });
    }

    public static void show(AppCompatActivity appCompatActivity) {
        appCompatActivity.startActivity(new Intent(appCompatActivity, SelectRegionPopupActivity.class));
        appCompatActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
    }

    @Override
    public void onBackPressed() {
        finishWithAnimation();
    }

    public void finishWithAnimation() {
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }
}
