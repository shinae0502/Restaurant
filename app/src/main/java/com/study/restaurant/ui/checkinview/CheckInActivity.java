package com.study.restaurant.ui.checkinview;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.ui.selectpcitureview.SelectPictureActivity;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.common.BananaConstants;
import com.study.restaurant.databinding.ActivityCheckInBinding;
import com.study.restaurant.fragment.CheckInContentsFragment;
import com.study.restaurant.fragment.CheckInFragment;
import com.study.restaurant.model.Store;
import com.study.restaurant.navigation.BananaNavigation;

public class CheckInActivity extends BananaBaseActivity implements BananaNavigation.CheckInNavigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ViewDataBinding initDataBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_check_in);
    }

    @Override
    public ViewModel initViewModel() {
        return ViewModelProviders.of(this).get(CheckInViewModel.class);
    }

    @Override
    public void initUI() {
        ((ActivityCheckInBinding) getViewDataBinding()).checkInPager.setAdapter(new CheckInPageAdt(getSupportFragmentManager()));
        ((CheckInViewModel) getViewModel()).setCheckInNavigation(this);
        ((ActivityCheckInBinding) getViewDataBinding()).setVm((CheckInViewModel) getViewModel());
    }


    @Override
    public void initData() {

    }

    public static void go(AppCompatActivity appCompatActivity, Store store) {
        Intent intent = new Intent(appCompatActivity, CheckInActivity.class);
        intent.putExtra("store", store);
        appCompatActivity.startActivity(intent);
    }

    private Store getStore() {
        return getIntent().getParcelableExtra("store");
    }

    @Override
    public void goPicture() {
        SelectPictureActivity.go(this, BananaConstants.PictureUploadMode.CHECK_IN, getStore());
    }

    @Override
    public void goCheckInWrite() {
        ((ActivityCheckInBinding) getViewDataBinding()).checkInPager.setCurrentItem(1);
    }

    @Override
    public BananaConstants.CheckInPage getCurrentPage() {
        if (((ActivityCheckInBinding) getViewDataBinding()).checkInPager.getCurrentItem() == 0) {
            return BananaConstants.CheckInPage.REGISTER;
        } else {
            return BananaConstants.CheckInPage.WRITE;
        }
    }

    @Override
    public void goRegister() {
        ((ActivityCheckInBinding) getViewDataBinding()).checkInPager.setCurrentItem(0);
    }

    private class CheckInPageAdt extends FragmentStatePagerAdapter {
        public CheckInPageAdt(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                CheckInFragment checkInFragment = new CheckInFragment();
                checkInFragment.setVm((CheckInViewModel) getViewModel());
                return checkInFragment;
            } else {
                CheckInContentsFragment checkInContentsFragment = new CheckInContentsFragment();
                checkInContentsFragment.setVm((CheckInViewModel) getViewModel());
                return checkInContentsFragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public void clickContents(View v) {
        goCheckInWrite();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((CheckInViewModel) getViewModel()).onActivityResult(requestCode, resultCode, data);
    }
}
