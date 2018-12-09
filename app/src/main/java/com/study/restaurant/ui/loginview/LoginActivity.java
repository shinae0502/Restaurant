package com.study.restaurant.ui.loginview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.study.restaurant.R;
import com.study.restaurant.common.BananaBaseActivity;
import com.study.restaurant.ui.mainview.MainActivity;
import com.study.restaurant.ui.OAuthSampleActivity;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.common.ProgressDialog;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;
import com.study.restaurant.navigation.BananaNavigation;
import com.study.restaurant.presenter.LoginPresenter;
import com.study.restaurant.ui.splashview.SplashFragment;
import com.study.restaurant.util.Logger;

public class LoginActivity extends BananaBaseActivity {

    LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loginFragment = LoginFragment.newInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, loginFragment)
                    .commitNow();
        }
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, LoginActivity.class);
        appCompatActivity.startActivity(intent);
        appCompatActivity.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BananaLoginManager.getInstance(this).onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);

    }
}
