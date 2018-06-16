package com.study.restaurant.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.login.FacebookLoginProvider;
import com.study.restaurant.login.KakaoLoginProvider;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.model.User;

public class BananaLoginManager {

    AppCompatActivity appCompatActivity;


    public BananaLoginManager(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setCallbackListener(LoginProvider.CallBack callbackListener) {
        FacebookLoginProvider.getInstance(appCompatActivity).setCallBack(callbackListener);
        KakaoLoginProvider.getInstance(appCompatActivity).setCallBack(callbackListener);
    }

    public void requestFacebookLogin() {
        FacebookLoginProvider.getInstance(appCompatActivity).requestLogin();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookLoginProvider.getInstance(appCompatActivity).onActivityResult(requestCode, resultCode, data);
        KakaoLoginProvider.getInstance(appCompatActivity).onActivityResult(requestCode, resultCode, data);
    }

    public void onCreate() {
        FacebookLoginProvider.getInstance(appCompatActivity).onCreate();
        KakaoLoginProvider.getInstance(appCompatActivity).onCreate();
    }

    public boolean isLogin() {
        return true;
    }

    public void onDestroy() {
        FacebookLoginProvider.getInstance(appCompatActivity).onDestroy();
    }

    public void logout() {
        FacebookLoginProvider.getInstance(appCompatActivity).logout();
        KakaoLoginProvider.getInstance(appCompatActivity).logout();
        BananaPreference.getInstance(appCompatActivity).saveUser(new User());
    }
}
