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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.databinding.ActivityMainBinding;
import com.study.restaurant.fragment.FindRestaurantFragment;
import com.study.restaurant.fragment.MangoPickFragment;
import com.study.restaurant.fragment.MyInformationFragment;
import com.study.restaurant.fragment.NewsFragment;
import com.study.restaurant.util.Logger;
import com.study.restaurant.view.MainActivitytNavigation;
import com.study.restaurant.viewmodel.MainActivityViewModel;

/**
 * 메인화면 맛집찾기, 망고픽, 맛집등록, 소식, 내정보 화면을 가지고 있다.
 */
public class MainActivity extends AppCompatActivity implements MainActivitytNavigation {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;
    ViewPager pager;
    // 맛집찾기
    FindRestaurantFragment findRestaurantFragment;
    // 내정보
    MyInformationFragment myInformationFragment;
    // 소식
    NewsFragment newsFragment;
    // 망고픽
    MangoPickFragment mangoPickFragment;
    // 등록
    View layout_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this);
        activityMainBinding.setVm(mainActivityViewModel);
        activityMainBinding.layoutRegister.setVm(mainActivityViewModel);

        BananaPreference.getInstance(this).loadUser();

        pager = findViewById(R.id.pager);
        pager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));
        // 페이지 이동 시 화면 제거되지 않게 설정
        pager.setOffscreenPageLimit(4);

        //식당 리뷰등 등록 메뉴 화면
        layout_register = findViewById(R.id.layout_register);
        //식당 리뷰 버튼 클릭 시 검색화면으로 이동하는 이벤트
        layout_register.findViewById(R.id.review).setOnClickListener
                (view -> SearchRestaurantActivity.go(MainActivity.this));
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
                    // 망고픽 화면 만들기
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
        if (mainActivityViewModel.isMenu3()) {
            mainActivityViewModel.showRegisterMenu(false);
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

    @Override
    public void registerShowAnimation() {
        registerShowAnimation(0);
    }

    @Override
    public void hideMenu() {
        activityMainBinding.layoutRegister.checkIn.setVisibility(View.INVISIBLE);
        activityMainBinding.layoutRegister.photo.setVisibility(View.INVISIBLE);
        activityMainBinding.layoutRegister.review.setVisibility(View.INVISIBLE);
        activityMainBinding.layoutRegister.restaurant.setVisibility(View.INVISIBLE);
    }

    @Override
    public void rotationMenu(boolean b) {
        if (b) {
            activityMainBinding.imgMenu.startAnimation(AnimationUtils.loadAnimation(this, R.anim.menu_rotation));
            activityMainBinding.imgMenu.setRotation(45);
        } else {
            activityMainBinding.imgMenu.startAnimation(AnimationUtils.loadAnimation(this, R.anim.menu_rotation_1));
            activityMainBinding.imgMenu.setRotation(0);
        }
    }

    private void registerShowAnimation(int step) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.menu_fade_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (step == 0)
                    registerShowAnimation(1);
                else if (step == 1)
                    registerShowAnimation(2);
                else if (step == 2)
                    registerShowAnimation(3);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        View v = step == 0 ? activityMainBinding.layoutRegister.checkIn
                : step == 1 ? activityMainBinding.layoutRegister.photo
                : step == 2 ? activityMainBinding.layoutRegister.review
                : step == 3 ? activityMainBinding.layoutRegister.restaurant
                : null;
        v.startAnimation(animation);
        v.setVisibility(View.VISIBLE);
    }

    public void clickRegisterRestaurant(View v) {
        Logger.d("clickRegisterRestaurant");
        RegisterRestaurantActivity.go(this);
    }

    public void alarmNotify(View v) {

    }

}
