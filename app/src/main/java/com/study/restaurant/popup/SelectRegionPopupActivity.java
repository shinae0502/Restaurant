package com.study.restaurant.popup;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.activity.GlobalApplication;
import com.study.restaurant.databinding.ActivitySelectRetionPopupBinding;
import com.study.restaurant.databinding.LayoutCustomTabBinding;
import com.study.restaurant.fragment.SelectRegionFragment;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.presenter.SelectRegionPopupPresenter;
import com.study.restaurant.util.CustomTabLayout;
import com.study.restaurant.util.LOG;
import com.study.restaurant.view.SelectRegionPopupView;

import static android.support.design.widget.TabLayout.*;

public class SelectRegionPopupActivity extends BasePopupActivity implements SelectRegionPopupView {

    CustomTabLayout tabLayout;
    ViewPager regionViewPager;
    RegionPagerAdapter regionPagerAdapter;
    SelectRegionPopupPresenter selectRegionPopupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //뷰바인딩 만들기
        activitySelectRetionPopupBinding
                = DataBindingUtil.setContentView(this, R.layout.activity_select_retion_popup);

        //presenter 만들기
        selectRegionPopupPresenter = new SelectRegionPopupPresenter(this);

        //뷰페이저 초기화
        regionViewPager = activitySelectRetionPopupBinding.regionViewPager;

        //탭 레이아웃 초가화
        tabLayout = activitySelectRetionPopupBinding.tabLayout;
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        setDimClickListener();

        //기존 불러온 도시 정보가 있다면 적용하기
        Cities tempCities = null;
        tempCities = ((GlobalApplication) getApplication()).getFindRestaurant().getCities();
        if (tempCities.getCities().size() > 0) {
            try {
                tempCities = tempCities.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            selectRegionPopupPresenter.setCities(tempCities);
            initRegionTabAndPager(tempCities);
        }
        //서버 도시 지역 요청하기
        else {
            selectRegionPopupPresenter.initRegionAncCity(cities ->
            {
                cities.addCity(0, new City.Builder()
                        .setCity_id("-1")
                        .setCity_name("최근지역")
                        .create());
                cities.addCity(1, new City.Builder()
                        .setCity_id("0")
                        .setCity_name("내주변")
                        .create());
                initRegionTabAndPager(cities);
            });
        }

    }

    @Override
    public void validateButton(boolean isValiate) {
        LOG.d(isValiate);
    }

    public void adapt(View view) {
        ((GlobalApplication) getApplication()).getFindRestaurant().setCities(selectRegionPopupPresenter.getCities());
        setResult(Activity.RESULT_OK);
        finishWithAnimation();
    }

    class RegionPagerAdapter extends FragmentStatePagerAdapter {

        public RegionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            SelectRegionFragment selectRegionFragment = new SelectRegionFragment();
            selectRegionFragment.setCityName(selectRegionPopupPresenter.getCities().getCities().get(position).getCity_name());
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


    private void initRegionTabAndPager(Cities cities) {
        for (int i = 0; i < cities.getCities().size(); i++) {
            City city = cities.getCities().get(i);
            LayoutCustomTabBinding layoutCustomTabBinding = LayoutCustomTabBinding.inflate(getLayoutInflater());
            Tab tab = tabLayout.newTab().setCustomView(layoutCustomTabBinding.getRoot());
            layoutCustomTabBinding.setCity(city);
            tab.setTag(layoutCustomTabBinding);
            tabLayout.addTab(tab);
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

        regionViewPager.setOffscreenPageLimit(tabLayout.getTabCount());
        activitySelectRetionPopupBinding.setCities(cities);
    }

    public static void show(AppCompatActivity appCompatActivity) {
        BasePopupActivity.show(appCompatActivity, new Intent(appCompatActivity, SelectRegionPopupActivity.class), 0x01);
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
