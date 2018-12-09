package com.study.restaurant.ui.loginview;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaViewModelFragment;
import com.study.restaurant.common.ProgressDialog;
import com.study.restaurant.databinding.FragmentLoginBinding;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.presenter.LoginPresenter;
import com.study.restaurant.ui.mainview.MainActivity;
import com.study.restaurant.util.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BananaViewModelFragment<FragmentLoginBinding, LoginViewModel> implements BananaNavigation.LoginNavigation {

    private int currentBg = R.drawable.img_intro_bg_1;
    private int currentAnim = R.anim.left_to_right;
    private ImageView bg1;
    private ImageView bg2;
    private ImageView bg3;

    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;
    private LoginBackgroundChanger loginBackgroundChanger;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public Class<LoginViewModel> getViewModelClass() {
        return LoginViewModel.class;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.setAppCompatActivity((AppCompatActivity) getActivity());
        mViewModel.setNavigation(this);
        mViewDataBinding.setVm(mViewModel);
        progressDialog = new ProgressDialog(getContext());
        loginPresenter = new LoginPresenter(getContext());

        BananaLoginManager.getInstance((AppCompatActivity) getActivity()).onCreate();

        bg1 = mViewDataBinding.bg;
        bg2 = mViewDataBinding.bg1;
        bg3 = mViewDataBinding.bg2;

        loginBackgroundChanger = new LoginBackgroundChanger();
        loginBackgroundChanger.sendEmptyMessage(0);
    }

    @Override
    public void goMain() {
        MainActivity.go((AppCompatActivity) getActivity());
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void finish() {
        loginBackgroundChanger.removeMessages(0);
        getActivity().finish();
    }

    private class LoginBackgroundChanger extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            currentAnim = currentAnim == R.anim.left_to_right ? R.anim.right_to_left : R.anim.left_to_right;

            switch (currentBg) {
                case R.drawable.img_intro_bg_1:
                    currentBg = R.drawable.img_intro_bg_2;
                    bg1.startAnimation(AnimationUtils.loadAnimation(getContext(), currentAnim));
                    break;

                case R.drawable.img_intro_bg_2:
                    currentBg = R.drawable.img_intro_bg_3;
                    bg2.startAnimation(AnimationUtils.loadAnimation(getContext(), currentAnim));
                    break;

                case R.drawable.img_intro_bg_3:
                    currentBg = R.drawable.img_intro_bg_1;
                    bg3.startAnimation(AnimationUtils.loadAnimation(getContext(), currentAnim));
                    break;
            }
            sendEmptyMessageDelayed(0, 4000);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.v("requestCode:" + requestCode + ", " + "resultCode:" + resultCode);
        BananaLoginManager.getInstance((AppCompatActivity) getActivity()).onActivityResult(requestCode, resultCode, data);
        progressDialog.dismiss();
    }
}
