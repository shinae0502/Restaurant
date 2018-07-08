package com.study.restaurant.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.databinding.ActivityMainBinding;
import com.study.restaurant.fragment.FindRestaurantFragment;
import com.study.restaurant.fragment.MangoPickFragment;
import com.study.restaurant.fragment.MyInformationFragment;
import com.study.restaurant.fragment.NewsFragment;
import com.study.restaurant.view.MainActivitytNavigation;
import com.study.restaurant.viewmodel.MainActivityViewModel;


public class MainActivity extends AppCompatActivity implements MainActivitytNavigation {

    ViewPager pager;
    FindRestaurantFragment findRestaurantFragment;
    MyInformationFragment myInformationFragment;
    NewsFragment newsFragment;
    MangoPickFragment mangoPickFragment;
    private MainActivityViewModel mainActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this);
        activityMainBinding.setVm(mainActivityViewModel);
        activityMainBinding.layoutRegister.setVm(mainActivityViewModel);

        BananaPreference.getInstance(this).loadUser();

        pager = findViewById(R.id.pager);
        pager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, MainActivity.class);
        appCompatActivity.startActivity(intent);
    }

    @Override
    public void goFindRestaurant() {
        pager.setCurrentItem(0);
    }

    @Override
    public void goMangoPick() {
        pager.setCurrentItem(1);
    }

    @Override
    public void goRegister() {
    }

    @Override
    public void goNews() {
        pager.setCurrentItem(2);
    }

    @Override
    public void goMyInformation() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (pager.getCurrentItem() == 0) {
            if (findRestaurantFragment != null)
                findRestaurantFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (findRestaurantFragment != null)
            findRestaurantFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((GlobalApplication) getApplication()).findRestaurant = null;
    }

    @Override
    public void onBackPressed() {
        if (mainActivityViewModel.isMenuEanbled()) {
            mainActivityViewModel.setMenu3(false);
        } else if (!isFinish) {
            handler.sendEmptyMessage(0);
        } else {
            super.onBackPressed();
        }
    }

    boolean isFinish = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(MainActivity.this, "뒤로가기 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
                    isFinish = true;
                    sendEmptyMessageDelayed(1, 5000);
                    break;
                case 1:
                    isFinish = false;
                    break;
            }
        }
    };
}
