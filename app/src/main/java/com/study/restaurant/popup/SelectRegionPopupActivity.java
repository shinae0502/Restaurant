package com.study.restaurant.popup;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.study.restaurant.common.FunctionImpl;
import com.study.restaurant.databinding.ActivitySelectRetionPopupBinding;
import com.study.restaurant.fragment.SelectRegionFragment;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.presenter.SelectRegionPopupPresenter;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.SelectRegionPopupView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static android.support.design.widget.TabLayout.*;

public class SelectRegionPopupActivity extends AppCompatActivity implements SelectRegionPopupView {

    TabLayout tabLayout;
    ViewPager regionViewPager;
    RegionPagerAdapter regionPagerAdapter;
    SelectRegionPopupPresenter selectRegionPopupPresenter;

    @Override
    public void validateButton(boolean isValiate) {
        LOG.d(isValiate);
        activitySelectRetionPopupBinding.adapt.setEnabled(isValiate);
    }


    class RegionPagerAdapter extends FragmentStatePagerAdapter {

        public RegionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            SelectRegionFragment  selectRegionFragment = new SelectRegionFragment();
            selectRegionFragment.setCityName(tabLayout.getTabAt(position).getText().toString());
            selectRegionFragment.setPresenter(selectRegionPopupPresenter);
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

    ActivitySelectRetionPopupBinding activitySelectRetionPopupBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySelectRetionPopupBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_retion_popup);

        //presenter 초기화
        selectRegionPopupPresenter = new SelectRegionPopupPresenter(this);

        regionViewPager = findViewById(R.id.regionViewPager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //팝업 딤처리 부분 클릭 시 종료
        findViewById(R.id.dim).setOnClickListener(view -> finishWithAnimation());


        //도시 불러오기
        selectRegionPopupPresenter.requestCity(cityArrayList -> {
            selectRegionPopupPresenter.setCities(cityArrayList);

            //지역 불러오기
            selectRegionPopupPresenter.requestRegion(regionArrayList -> {
                selectRegionPopupPresenter.getCities().setRegions(regionArrayList);
                initRegionTabAndPager(selectRegionPopupPresenter.getCities());
            });
        });
    }

    private void initRegionTabAndPager(Cities cities) {

        //최근지역
        tabLayout.addTab(tabLayout.newTab().setText("최근지역"));
        //내주변
        tabLayout.addTab(tabLayout.newTab().setText("내주변"));
        for(City city : cities.getCities())
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
