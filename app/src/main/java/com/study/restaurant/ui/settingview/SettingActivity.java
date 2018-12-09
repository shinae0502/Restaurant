package com.study.restaurant.ui.settingview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.restaurant.R;
import com.study.restaurant.common.BananaPreference;
import com.study.restaurant.login.LoginProvider;
import com.study.restaurant.manager.BananaLoginManager;
import com.study.restaurant.model.User;
import com.study.restaurant.ui.splashview.SplashActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle("설정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void go(final AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, SettingActivity.class);
        appCompatActivity.startActivity(intent);
    }

    public void logout(View view) {
        BananaLoginManager.getInstance(this).logout(result -> {
            BananaPreference.getInstance(SettingActivity.this).saveUser(new User());
            SplashActivity.go(SettingActivity.this);
        });
    }
}
