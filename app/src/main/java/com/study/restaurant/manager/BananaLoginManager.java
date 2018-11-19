package com.study.restaurant.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.login.FacebookLoginProvider;
import com.study.restaurant.login.KakaoLoginProvider;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.model.User;

public class BananaLoginManager {

    private static BananaLoginManager bananaLoginManager;
    AppCompatActivity appCompatActivity;

    public BananaLoginManager(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public static BananaLoginManager getInstance(AppCompatActivity appCompatActivity) {
        if (bananaLoginManager == null)
            synchronized (BananaLoginManager.class) {
                if (bananaLoginManager == null)
                    bananaLoginManager = new BananaLoginManager(appCompatActivity);
            }
        if (appCompatActivity != null && !bananaLoginManager.appCompatActivity.equals(appCompatActivity))
            bananaLoginManager.appCompatActivity = appCompatActivity;
        return bananaLoginManager;
    }

    public void requestFacebookLogin(LoginProvider.OnResultLoginListener onResultLoginListener) {
        FacebookLoginProvider.getInstance(appCompatActivity).requestLogin(onResultLoginListener);
    }

    public void requestKakaoLogin(LoginProvider.OnResultLoginListener onResultLoginListener) {
        KakaoLoginProvider.getInstance(appCompatActivity).requestLogin(onResultLoginListener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        FacebookLoginProvider.getInstance(appCompatActivity).onActivityResult(requestCode, resultCode, data);
        KakaoLoginProvider.getInstance(appCompatActivity).onActivityResult(requestCode, resultCode, data);
    }

    public void onCreate() {
        FacebookLoginProvider.getInstance(appCompatActivity).onCreate();
        KakaoLoginProvider.getInstance(appCompatActivity).onCreate();
    }

    public void onDestroy() {
        FacebookLoginProvider.getInstance(appCompatActivity).onDestory();
        KakaoLoginProvider.getInstance(appCompatActivity).onDestroy();
    }

    public void logout(LoginProvider.OnResultLogoutListener onResultLogoutListener) {
        if (FacebookLoginProvider.getInstance(appCompatActivity).isLoggedIn()) {
            FacebookLoginProvider.getInstance(appCompatActivity).logout(onResultLogoutListener);
        } else if (KakaoLoginProvider.getInstance(appCompatActivity).isLoggedIn()) {
            KakaoLoginProvider.getInstance(appCompatActivity).logout(onResultLogoutListener);
        } else {
            onResultLogoutListener.onResult(0);
        }
    }

    public void requestLogin() {
        if (FacebookLoginProvider.getInstance(appCompatActivity).isLoggedIn()) {
            FacebookLoginProvider.getInstance(appCompatActivity).requestUser(user -> {

            });
        } else if (KakaoLoginProvider.getInstance(appCompatActivity).isLoggedIn()) {
            KakaoLoginProvider.getInstance(appCompatActivity).requestUser(user -> {

            });
        }
    }
}
