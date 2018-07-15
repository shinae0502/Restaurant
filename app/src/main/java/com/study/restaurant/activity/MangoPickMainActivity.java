package com.study.restaurant.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.study.restaurant.R;
import com.study.restaurant.adapter.TabPagerAdapter;

public class MangoPickMainActivity extends AppCompatActivity {   // 바나나픽 Main

    private TabLayout tabLayout_main;
    private ViewPager viewPager_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mangopick_activity_main);

        // Toolbar 추가
        Toolbar toolbar_main =  (Toolbar)findViewById(R.id.toolbar_main);
        toolbar_main.setTitle("= Toolbar Title =");
        setSupportActionBar(toolbar_main);

        // TabLayout 셋팅
        tabLayout_main = (TabLayout) findViewById(R.id.tablayout_main);
        tabLayout_main.addTab(tabLayout_main.newTab().setText("스토리"));
        tabLayout_main.addTab(tabLayout_main.newTab().setText("TOP리스트"));
        tabLayout_main.setTabGravity(TabLayout.GRAVITY_FILL);

        // ViewPager 참조
        viewPager_main = (ViewPager)findViewById(R.id.viewpager_main);

        // adapter(TabPagerAdapter) 생성
        TabPagerAdapter pagerAdapter
                = new TabPagerAdapter(getSupportFragmentManager(), tabLayout_main.getTabCount());
        viewPager_main.setAdapter(pagerAdapter);
        viewPager_main.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_main));

        // TabSelectedListner 셋팅
        tabLayout_main.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager_main.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });  // ----------------------------------------------- addOnTabSelectedListener()

    } // ------------------------------------ onCreate()

} // ====================================================== MangoPickMainActivity



    /*
        TabLayout - 스토리, TOP
        구현순서
        1. gradle 추가 :
        2. Layout 구성
            - Toolbar : 탭메뉴
            - TabLayout : 탭 구현 레이아웃 - "스토리", "TOP리스트"
            - ViewPager : 화면전환
        3. Fragment 추가
        4. TabPagerAdapter 추가
        5. 메인 액티비티 구성
        6. 기존 툴바 삭제 : values의 styles.xml 수정
     */
