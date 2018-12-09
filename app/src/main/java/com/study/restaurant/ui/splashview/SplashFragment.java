package com.study.restaurant.ui.splashview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaViewModelFragment;
import com.study.restaurant.databinding.FragmentSplashBinding;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.loginview.LoginActivity;
import com.study.restaurant.ui.mainview.MainActivity;

public class SplashFragment extends BananaViewModelFragment<FragmentSplashBinding, SplashViewModel>
        implements BananaNavigation.SplashNavigation {

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.setSplashNavigation(this);
        //접속 로그
        mViewModel.sendConnectionLog((AppCompatActivity) getActivity());
        mViewDataBinding.setVm(mViewModel);
        BananaLoginManager.getInstance((AppCompatActivity) getActivity()).onCreate();
        //로그인 시도하기
        BananaLoginManager.getInstance((AppCompatActivity) getActivity()).requestLogin();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    public void goLogin() {
        LoginActivity.go((AppCompatActivity) getActivity());
    }

    @Override
    public void goMain() {
        MainActivity.go((AppCompatActivity) getActivity());
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
