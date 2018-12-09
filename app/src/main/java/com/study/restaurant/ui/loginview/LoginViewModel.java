package com.study.restaurant.ui.loginview;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.ui.OAuthSampleActivity;
import com.study.restaurant.util.Logger;

public class LoginViewModel extends ViewModel {

    private BananaNavigation.LoginNavigation loginNavigation;
    private AppCompatActivity activity;

    public void requestFacebookLogin(View view) {
        loginNavigation.showProgress();
        BananaLoginManager.getInstance(activity).requestFacebookLogin((result, user) -> {
                    Logger.v(result);
                    Logger.v(user.toString());
                    ApiManager.getInstance().requestFacebookLogin(
                            user.accessToken, new ApiManager.CallbackListener() {
                                @Override
                                public void callback(String result) {
                                    Logger.v(result);
                                    User user = new Gson().fromJson(result, User.class);
                                    //사용자 정보 저장하기
                                    BananaPreference.getInstance(activity).saveUser(user);
                                    loginNavigation.goMain();
                                    loginNavigation.finish();
                                }

                                @Override
                                public void failed(String msg) {

                                }
                            });
                }
        );

    }

    public void onClickSignup(View v) {
        loginNavigation.showProgress();
        BananaLoginManager.getInstance(activity).requestKakaoLogin((result, user) -> {
            Logger.d("onSuccessLogin");
            if (result == 0) {
                //로그인 성공
                BananaPreference.getInstance(activity).saveUser(user);
                loginNavigation.goMain();
                loginNavigation.finish();
            } else {
                //로그인 실패
                Logger.e("kakao login failed");
            }
        });
    }


    public void naverLogin(View view) {
        activity.startActivity(new Intent(view.getContext(), OAuthSampleActivity.class));
    }

    public void skip(View view) {
        loginNavigation.goMain();
    }

    public void setNavigation(BananaNavigation.LoginNavigation loginNavigation) {
        this.loginNavigation = loginNavigation;
    }

    public void setAppCompatActivity(AppCompatActivity activity) {
        this.activity = activity;
    }
}
