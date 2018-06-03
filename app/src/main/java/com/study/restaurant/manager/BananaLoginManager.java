package com.study.restaurant.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.study.restaurant.login.FacebookLoginProvider;
import com.study.restaurant.login.LoginProvider;

public class BananaLoginManager {

    AppCompatActivity appCompatActivity;

    public BananaLoginManager(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void setCallbackListener(LoginProvider.CallBack callbackListener) {
        FacebookLoginProvider.getInstance(appCompatActivity).setCallBack(callbackListener);
    }

    public void requestFacebookLogin() {
        FacebookLoginProvider.getInstance(appCompatActivity).requestLogin();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookLoginProvider.getInstance(appCompatActivity).onActivityResult(requestCode, resultCode, data);
    }

    public void onCreate() {
        FacebookLoginProvider.getInstance(appCompatActivity).onCreate();
    }

    public boolean isLogin() {
        return true;
    }

    public void onDestroy() {
        FacebookLoginProvider.getInstance(appCompatActivity).onDestroy();
    }
}
